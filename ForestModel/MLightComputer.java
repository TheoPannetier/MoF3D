/******************************************************************************************
* This class computes the vertical light distribution of voxels in a column.
* Instances of this class runs on different threads.
*******************************************************************************************/
import de.grogra.rgg.Library;

public class MLightComputer implements Runnable {

    private VoxBase[][][] vox;		//pointer to voxels
    private int colX;			//start x-index of voxels for which this thread is responsible
    private int colXMax;		//end x-index of voxels for which this thread is responsible
    private PG pg;			//global parameters
    private PP pp;			//pass parameters	
    private MLightNotifier notifier;	//notifier used to manage progress of individual threads
    private int noticeIndex;		//index of thread. allows notifier to trace progress of individual threads.
 
    public MLightComputer(VoxBase[][][] v, int x, int xMax, PG pg, PP pp, MLightNotifier notifier, int noticeIndex){
        this.vox = v;
		this.colX = x;
		this.colXMax = xMax;
		this.pg = pg;
		this.pp = pp;
		this.notifier = notifier;
		this.noticeIndex = noticeIndex;
    }
 
    @Override
    public void run() {
        computeVoxColumn();
    }
 
    /*
    * This method iterates through each column, from top to bottom.
    * The light condition is computed and assigned to each voxel.
    */
    private synchronized void computeVoxColumn()
	{
		float lightIntensityMax = pp.get(PP.Imax);
		try{
			for(int x=colX; x<=colXMax; ++x)
			{
				for(int y=0; y<vox[x].length; ++y)
				{
					float leafAreaCurr=0; //accumulated leaf area from the top of canopy so far (cm^2)
					
					for(int z=vox[x][y].length-1; z>=0; z--)
					{
						vox[x][y][z].sumLeafArea();
						float leafAreaVox = vox[x][y][z].getLeafArea(); //leaf area in current voxel
							
						leafAreaCurr += leafAreaVox;		     //accumulate leaf area from top (cm^2)
							
						float leafAreaIndex = leafAreaCurr*0.0001f/pg.gridArea;//leaf area converted to m^2, divided by grid area (m^2) to get LAI
						float lightInVox = lightFactor(leafAreaIndex);

						if(lightInVox>lightIntensityMax) //clamp max to lightIntensityMax
							lightInVox=lightIntensityMax;
					
						vox[x][y][z].light=lightInVox * lightIntensityMax;     //light in micro mol m^-2 s^-1
					}
				}
			}
		}catch(Exception ex)
		{
			
		}
		notifier.setComplete(noticeIndex);
    }
	
    /**
    * Light extinction function using leaf area index and light extinction coefficient
    */
    private synchronized float lightFactor(float leafAreaIndex)
    {
		return (float)(Math.exp(-pp.get(PP.kL)*leafAreaIndex));
    }

}