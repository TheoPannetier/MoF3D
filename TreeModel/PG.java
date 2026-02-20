/**
 * This class contains all global parameters
 */
public class PG extends P
{
	//Name of global file
	public static final String fileName = "Forest_param_global";
	public static final String fileExt = ".txt";
	
	//Parameters of  global file
	public static final String Timesteps = "Timesteps";
	public static final String Replicates = "Replicates";
	public static final String RandomSeed = "RandomSeed";
	public static final String MaxX = "MaxX";
	public static final String MaxY = "MaxY";
	public static final String MaxZ = "MaxZ";
	public static final String Corridor = "WidthCorridor";
	public static final String GridSize = "VoxelSize";
	public static final String ReportForestVariables = "ReportForest";
	public static final String ReportLight	 = "ReportLight";
	public static final String ReportMortality = "ReportMortality";
	public static final String ReportShoots	 = "ReportShoots";
	public static final String ReportTreeVariables = "ReportTrees";
	public static final String ReportVoxel	 = "ReportVoxel";
	public static final String SimulateForest = "SimulateForest";
	public static final String ThreadCount = "ThreadCount";
	public static final String ShaderVisualization = "VisualizationShader";
	public static final String COLOR_SET = "VisualizationMethod";
	
	public static int pass = 0; //current simulation pass
	
	//pre-computed values
	public float gridArea;
	public int gridIndexXMax;
	public int gridIndexYMax;
	public int gridIndexZMax;
	public float gridDiagonal;
	public float gridSizeHalf;
	public float areaTotal;
	
	public PG()
	{
	    super();
	}

	public void precompute()
        {
	    float gridLen = get(GridSize);
            gridArea = gridLen * gridLen;
	    
	    gridIndexXMax = (int)(((get(MaxX)+(2*get(Corridor)))/gridLen)-1);
	    gridIndexYMax = (int)(((get(MaxY)+(2*get(Corridor)))/gridLen)-1);
	    gridIndexZMax = (int)(((get(MaxZ))/gridLen)-1);
	    
	    gridDiagonal = (float)(Math.sqrt(gridArea + gridArea));
	    
	    float x = get(MaxX);
	    float y = get(MaxY);
	    areaTotal = x*y;
	    
	    gridSizeHalf = gridLen/2.0f;
        }
	
	public boolean isValidVox(int x, int y, int z)
	{
	    if((x>=0)&&
		(y>=0)&&
		(z>=0)&&
		(x<=gridIndexXMax)&&
		(y<=gridIndexYMax)&&
		(z<=gridIndexZMax)
		)
		return true;
		
	    return false;
	}
}