/******************************************************************************************
 * This class contains the light model which computes the light intensity in each voxel
 * considering the lateral voxels surrounding the focal voxel
*******************************************************************************************/
import de.grogra.rgg.Library;

public class MLightLateral implements Runnable {

    private VoxBase[][][] vox;			//pointer to voxels
    private VoxBase[][][] voxCopy;		//pointer to voxels duplicate
    private int colX;				//start x-index of voxels for which this thread is responsible
    private int colXMax;			//end x-index of voxels for which this thread is responsible
    private PG pg;				//global parameters
    private PP pp;				//global parameters
    private MLightNotifier notifier;		//notifier used to manage progress of individual threads
    private int noticeIndex;			//index of thread. allows notifier to trace progress of individual threads.
    private float[] voxDist;			//proportion of light intensity in successively farther voxels that contribute to light in a voxel
    private int corridorVoxLen; 		//size of corridor in terms of grid size
    private float ALMAX;
    private int EdgeConditions;
	
    public MLightLateral(VoxBase[][][] v, int x, int xMax, PG pg, PP pp, MLightNotifier notifier, int noticeIndex, VoxBase[][][] vCopy,float[] voxDist, float ALMAX, int corridorVoxLen, int EdgeConditions){
		this.vox = v;
		this.colX = x;
		this.colXMax = xMax;
		this.pg = pg;
		this.pp = pp;
		this.notifier = notifier;
		this.noticeIndex = noticeIndex;
		this.voxCopy = vCopy;
		this.voxDist = voxDist;
		this.ALMAX = ALMAX;
		this.EdgeConditions = EdgeConditions;
    }
 
    @Override
    public void run() {
        lateralLight();
    }
    
     private synchronized void lateralLight() {
	try{
		float lightIntensityMax = pp.get(PP.Imax);
		int ringIndex=0;
		
		for(int x=colX; x<=colXMax; ++x)
		{
			for(int y=0; y<vox[x].length; ++y)
			{
				for(int z=0; z<vox[x][y].length; ++z)
				{
					vox[x][y][z].light = 0; //reset light value
										
					for(int xx=(x-(voxDist.length-1)); xx<=(x+(voxDist.length-1));xx++)
					{
						for(int yy=(y-(voxDist.length-1)); yy<=(y+(voxDist.length-1));yy++)
						{
							ringIndex=Math.max(Math.abs(x-xx),Math.abs(y-yy));
							accumLightFromLateralVox(x,y,z,xx,yy,voxDist[ringIndex],ringIndex, EdgeConditions);
						}
					}

					if(vox[x][y][z].light>lightIntensityMax) //clamp to max light intensity
						vox[x][y][z].light = lightIntensityMax; 
					
					if(vox[x][y][z].leafArea > ALMAX)
					{
						for(int m=0; m<vox[x][y][z].getBranchCount(); m++)
						{
							SBranch br = vox[x][y][z].getBranch(m);
							vox[x][y][z].setLeafAreaAxis(br.branchId,vox[x][y][z].getLeafAreaAxis(br.branchId)/vox[x][y][z].leafArea * ALMAX);
						}
						
						vox[x][y][z].leafArea = ALMAX;
					}
					
					//precompute maximum GR (growth rate) for later use in growth()
					float time=pp.get(PP.tyear);
					vox[x][y][z].GR = (float)((Math.log(ALMAX/vox[x][y][z].leafArea)/Math.log(2))/time);
				}
			}
		}
	}
	   	catch(Exception ex)
		{
			
		}
		notifier.setComplete(noticeIndex);
     }
  
    /*
    * Computes contribution of lateral vox at position (sideX, sideY, z) modulated by factor.
    * Adds the lateral vox contribution to vox lighting at position (x,y,z).
    * Note that an assumption that the side length of the core area is larger than the corridor is made.
    * @return true if lateral voxel is within bounds, false otherwise.
    */
    private synchronized void accumLightFromLateralVox(int x, int y, int z, int sideX, int sideY, float factor, int ringIndex, int EdgeConditions)
    {

	    //new approach
	    if(EdgeConditions==0)
	    {
		    if(sideX<0)
		    {	
			sideX =pg.gridIndexXMax-(2*(int)pg.get(PG.Corridor))+sideX;
		    }
		    
		    if(sideX>pg.gridIndexXMax)
		    {	
			sideX = (2*(int)pg.get(PG.Corridor))+(pg.gridIndexXMax-sideX);
		    }

		    if(sideY<0)
		    {	
			sideY = pg.gridIndexYMax-(2*(int)pg.get(PG.Corridor))+sideY;
		    }
		    
		    if(sideY>pg.gridIndexYMax)
		    {	
			sideY = (2*(int)pg.get(PG.Corridor))+(pg.gridIndexYMax-sideY);
		    }
	    }

	    if(EdgeConditions==1)
	    {
		    if(sideX<0)
		    {	
			sideX = Math.abs(sideX);
		    }
		    
		    if(sideX>pg.gridIndexXMax)
		    {	
			sideX = pg.gridIndexXMax-(sideX-pg.gridIndexXMax);
		    }

		    if(sideY<0)
		    {	
			sideY = Math.abs(sideY);
		    }
		    
		    if(sideY>pg.gridIndexYMax)
		    {	
			sideY = pg.gridIndexYMax-(sideY-pg.gridIndexYMax);
		    }
	    }
	     vox[x][y][z].light +=  voxCopy[sideX][sideY][z].light*factor;	//accumulate total light in voxel 
     }
}