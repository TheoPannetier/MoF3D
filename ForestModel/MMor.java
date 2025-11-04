import de.grogra.rgg.Library;
/**
* This class contains the mortality model
*/
public class MMor
{
    /**
    * Computes the death rate based on the tree biomass
    */
    public static float mortalityBiomass(float dbh, float length, float mortRate, float densityWood, float mortScalingExponent)
    {
	 /**The mortality rate is parameterized based on the biomass in kg, thus the wood denisity needs to be converted to kg/m-3*/
	 return mortRate * (float) (Math.pow( (0.0509*densityWood*(Math.pow(dbh*100,2.0))*length) , -mortScalingExponent ) );
	 }
       
    /**
    * Computes the proportion of trees to kill if a random disturbance occur.
    */
    public static float disturbanceKill(float frequency, float effect)
    {
		float killProportion = 0;
		double ran=Math.random();
		if(ran<frequency)
		{
			killProportion = effect;
			
		}
		return killProportion;
    }
    
    /**
    * Kills a random tree inside the specified voxel
    */
    public static SRootBase killRandom(VoxBase v)
    {
		int treeCount = v.getTreeCount();
		if(treeCount==0)
			return null;
		else
		{
			double ran = Math.random();
			int killedTreeIndex = (int)(ran*treeCount);
			SRootBase tree = v.getTree(killedTreeIndex);
			return tree;
		}
    }
}