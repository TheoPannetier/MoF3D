/******************************************************************************************
* This class copies a number of voxel light values to a duplicated voxel in preparation of lateral
* light distribution computations.
* Instances of this class runs on different threads.
*******************************************************************************************/
import de.grogra.rgg.Library;

public class MLightCopier implements Runnable {

    private VoxBase[][][] vox;		//pointer to voxels
    private VoxBase[][][] voxCopy;	//pointer to voxels duplicate
    private int colX;			//start x-index of voxels for which this thread is responsible
    private int colXMax;		//end x-index of voxels for which this thread is responsible
    private PG pg;			//global parameters
    private MLightNotifier notifier;	//notifier used to manage progress of individual threads
    private int noticeIndex;		//index of thread. allows notifier to trace progress of individual threads.
 
    public MLightCopier(VoxBase[][][] v, int x, int xMax, PG pg, MLightNotifier notifier, int noticeIndex, VoxBase[][][] vCopy){
        this.vox = v;
        this.colX = x;
        this.colXMax = xMax;
        this.pg = pg;
        this.notifier = notifier;
        this.noticeIndex = noticeIndex;
        this.voxCopy = vCopy;
    }
 
    @Override
    public void run() {
        copyVox();
    }
 
    /**
    * This method iterates through each voxel and copies the light condition value to the temporary array.
    */
    private synchronized void copyVox() {
	try
	{
	    for(int x=colX; x<=colXMax; ++x)
	    {
		for(int y=0; y<vox[x].length; ++y)
		{
		    for(int z=0; z<vox[x][y].length; ++z)
	            {
			voxCopy[x][y][z].light = vox[x][y][z].light;
		    }
		}
	    }
	}
	catch(Exception e)
	{
	}
	notifier.setComplete(noticeIndex);
    }
}