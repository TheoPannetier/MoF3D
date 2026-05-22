/******************************************************************************************
 * This class contains the light model which computes the light intensity in each voxel
*******************************************************************************************/
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import de.grogra.rgg.Library;

/*
* Main method or procedure used to compute light condition in ALL voxels.
* First, light is assumed to penetrate the canopy vertically (in each column); and 
* amount depreciates following an extinction coefficient.
* Second, a copy of the light condition is made.
* Lastly, in order to account for ambient light, light condition in each voxel is
* moderated by neighbouring voxels laterally (or horizontally).
*/
public class MLight
{
    private static ExecutorService threadPool;
    private static MLightNotifier notifier; //notifier used to track progress of threads
    public static VoxBase[][][] voxCopy;    //temporary copy of light conditions.
    
    public static void computeVoxelLightParallel(VoxBase[][][] vox, PG pg, PP pp)
    {
	//1. The light distribution in each voxel when only considering the leaf area above in the single voxel column is computed
	computeSingleColumnLight(vox,pg,pp);
	
	//2. A temporary copy of all voxels is created (needed to calculate the light when considering lateral voxels
	copyVox(vox,pg);
	
	//3. If a periodic boundary is define (EdgeConditions==0), the voxels from the core area are copied to the border
	// Note that all informations stored in the voxels are copied (see VoxBase) 
	if(pp.get(PP.EdgeConditions)==0){//infinite edge
		copyCoreToCorridor(voxCopy,pg);
		copyCoreToCorridor(vox,pg);}
	
	//4. The light intensity in each voxel under consideration of the light conditions in the surrounding voxels is computed
	computeLateralLight(vox,pg,pp);

    }
    
    /*
    * This method contains the first procedure in light computation, i.e.
    * penetration of light vertically in each column.
    * The scene is divided by x-rows and computation work is assigned to separate threads.
    * See method run() in the class MLightComputer for the procedures executed by each thread.
    */
    private static void computeSingleColumnLight(VoxBase[][][] vox, PG pg, PP pp)
    {
	    int ThreadCount=pg.getInt(PG.ThreadCount);
	    
	    ExecutorService threadPool = getThreadPool(ThreadCount);		//access the thread pool for available threads
		notifier.reset();				//reset notifier, which is used to track progress of threads
		int div = vox.length/ThreadCount;		//number of x-rows per thread
		int divR = vox.length%ThreadCount;		//remainder number of rows, assigned to last thread (naive load distribution)
		
		for(int x=0; x<ThreadCount; ++x)
		{
			if(x==ThreadCount-1)
				threadPool.execute(new MLightComputer(vox,x*div,x*div+div-1+divR,pg,pp,notifier,x));
			else
				threadPool.execute(new MLightComputer(vox,x*div,x*div+div-1,pg,pp,notifier,x));
		}
		
		while(!notifier.allComplete())
		{
			Thread.sleep(2);
		}
    }
    
    /*
    * This method copies the light conditions in voxels computed via vertical extinction into
    * a separate array. (This is necessary for the next step, where light conditions are moderated
    * by neighbouring voxels.)
    * The scene is divided by x-rows and computation work is assigned to separate threads.
    * See method run() in the class MLightCopier for the procedures executed by each thread.
    */
    private static void copyVox(VoxBase[][][] voxOriginal, PG pg)
    {
	//Initialize new VoxBases for the copied voxel if they do not exists (this method is just called once when it is first called)    
	if(voxCopy == null)
	{
		voxCopy = new VoxBase[voxOriginal.length][voxOriginal[0].length][voxOriginal[0][0].length];
		for(int i=0; i<voxCopy.length;++i)
		{
			for(int j=0; j<voxCopy[0].length;++j)
			{
				for(int k=0; k<voxCopy[0][0].length;++k)
					voxCopy[i][j][k] = new VoxBase(i,j,k);
			}
		}
	}
	
	//The following method is used to copy the light conditions from the original voxels to the temporary copy voxCopy
	//This method is parallelized and uses multiple threads 
	
	int ThreadCount=pg.getInt(PG.ThreadCount);
	
	ExecutorService threadPool = getThreadPool(ThreadCount);
	notifier.reset();
	int div = voxOriginal.length/ThreadCount;
	int divR = voxOriginal.length%ThreadCount;
	
        for(int x=0; x<ThreadCount; ++x)
		{
			if(x==ThreadCount-1)
				threadPool.execute(new MLightCopier(voxOriginal,x*div,x*div+div-1+divR,pg,notifier,x,voxCopy));
			else
				threadPool.execute(new MLightCopier(voxOriginal,x*div,x*div+div-1,pg,notifier,x,voxCopy));
		}
	
		while(!notifier.allComplete())
		{
			Thread.sleep(2);
		}
    }
    
    /*
    * This method copies the informations stored in the voxels in the core area to those on the corridor
      This method is required if a periodic boundary is define (EdgeConditions==0)
    */
      private static void copyCoreToCorridor(VoxBase[][][] voxels, PG pg)
    {
	    int corridorVoxLen = (int)(pg.get(PG.Corridor)/pg.get(PG.GridSize));
	    int gridIndexCoreXMax = (pg.gridIndexXMax-corridorVoxLen);
	    int gridIndexCoreYMax = (pg.gridIndexYMax-corridorVoxLen);
	    
	    int coreXTotal = (int)(pg.get(PG.MaxX)/pg.get(PG.GridSize));
	    int coreYTotal = (int)(pg.get(PG.MaxY)/pg.get(PG.GridSize));
	    
	    int coreX=0;
	    int coreY=0;
	    
	    for(int x=0; x<voxels.length; ++x){
		    for(int y=0; y<voxels[0].length; ++y){
			    //if in core, skip copying
			    if( ((x>corridorVoxLen)&&(x<gridIndexCoreXMax)) &&
				((y>corridorVoxLen)&&(y<gridIndexCoreYMax))
			    )
			    {
				    continue;
			    }

			    for(int z=0; z<voxels[0][0].length; ++z){
				    	coreX=x;
					coreY=y;
				    //lower left
					if((x < corridorVoxLen) && (y < corridorVoxLen) && (x <= gridIndexCoreXMax) && (y <= gridIndexCoreYMax))
					{
						coreX=x+coreXTotal;
						coreY=y+coreYTotal;
					}
					//left
					else if((x < corridorVoxLen) && (y >= corridorVoxLen) && (x <= gridIndexCoreXMax) && (y <= gridIndexCoreYMax))
					{
						coreX=x+coreXTotal;
									}
					//top left
					else if((x < corridorVoxLen) && (y >= corridorVoxLen) && (x <= gridIndexCoreXMax) && (y > gridIndexCoreYMax))
					{
						coreX=x+coreXTotal;
						coreY=y-coreYTotal;
					}
					//top
					else if((x >= corridorVoxLen) && (y >= corridorVoxLen) && (x <= gridIndexCoreXMax) && (y > gridIndexCoreYMax))
					{
						coreY=y-coreYTotal;
					}
					//top right
					else if((x >= corridorVoxLen) && (y >= corridorVoxLen) && (x > gridIndexCoreXMax) && (y > gridIndexCoreYMax))
					{
						coreX=x-coreXTotal;
						coreY=y-coreYTotal;
					}
					//right
					else if((x >= corridorVoxLen) && (y >= corridorVoxLen) && (x > gridIndexCoreXMax) && (y <= gridIndexCoreYMax))
					{
						coreX=x-coreXTotal;
					}
					//lower right
					else if((x >= corridorVoxLen) && (y < corridorVoxLen) && (x > gridIndexCoreXMax) && (y <= gridIndexCoreYMax))
					{
						coreX=x-coreXTotal;
						coreY=y+coreYTotal;
					}
					//lower
					else if((x >= corridorVoxLen) && (y < corridorVoxLen) && (x <= gridIndexCoreXMax) && (y <= gridIndexCoreYMax))
					{
						coreY=y+coreYTotal;
					}
					voxels[x][y][z]=voxels[coreX][coreY][z];
			    }
		    }
	    }
    }
    
    /*
    * This method moderates the light condition in each voxel by laterally neighbouring voxels.
    * The scene is divided by x-rows and computation work is assigned to separate threads.
    * See method run() in the class MLightLateral for the procedures executed by each thread.
    */
    private static void computeLateralLight(VoxBase[][][] vox, PG pg, PP pp)
    {
	int corridorVoxLen = (int)(pg.get(PG.Corridor)/pg.get(PG.GridSize));
		
	//multithread
	int ThreadCount=pg.getInt(PG.ThreadCount);
	ExecutorService threadPool = getThreadPool(ThreadCount);
	notifier.reset();
	int div = vox.length/ThreadCount;
	int divR = vox.length%ThreadCount;
	
		for(int x=0; x<ThreadCount; ++x)
		{
			if(x==ThreadCount-1)
				threadPool.execute(new MLightLateral(vox,x*div,x*div+div-1+divR,pg,pp,notifier,x,voxCopy,pp.lightDistribution,pp.get(PP.ALMax),corridorVoxLen,(int)pp.get(PP.EdgeConditions)));
			else
				threadPool.execute(new MLightLateral(vox,x*div,x*div+div-1,pg,pp,notifier,x,voxCopy,pp.lightDistribution,pp.get(PP.ALMax),corridorVoxLen,(int)pp.get(PP.EdgeConditions)));
		}
		
		while(!notifier.allComplete())
		{
			Thread.sleep(2);
		}
    }
        
    /*
    * This method is needed for parallization of light computing on multiple threads
    */
    private static ExecutorService getThreadPool(int ThreadCount)
    {
	    	//int ThreadCountNew=pg.getInt(PG.ThreadCount);
	    
		if(threadPool==null)
		{
			threadPool = Executors.newFixedThreadPool(ThreadCount);
			notifier = new MLightNotifier(ThreadCount);
		}
		return threadPool;
    }
}