import de.grogra.rgg.Library;

/**
* This class contains the germination and dispersion model
*/
public class MGer
{
	public static float gEnergyUsed = 0;
	public static float gEnergyUsedMin = 0;
	public static float gEnergyUsedMax = 0;
	private static float voxBArea[][] = null; //basal area for single voxels (no contribution from side voxels)
	private static float gEnergyGlob = 0;
	
	private static float energyAccum[] = null;		//accumulated energy amount from successive "rings" of voxels around current voxel
	private static float outsideCorridor[] = null;	//counts number of voxels in lateral range that are out of bounds
	private static float withinCorridor[] = null;	//counts number of voxels in lateral range within bounds.
	private static boolean hasVoxOutsideCorr; 		//flag to indicate if a voxel is at parameter (corridor) of scene.
	
	public static int PHYLLO_ALTERNATE = 0;
}