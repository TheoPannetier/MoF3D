import de.grogra.rgg.Library;
/*
* This is class or module represents a tree
* with additional parameters for simulating tree architectural growth, 
* e.g. tree ID, voxel coordinates, age, height-diameter exponent, 
* specific leaf area, wood density.
*/
public class SRoot extends SRootBase
{
	public float internodeLen;
	public float internodeDia;
	
	public int speciesId;
	
	public SRoot(int treeId, float length, float diameter, 
		float translateX, float translateY, float translateZ,
		int voxx, int voxy, int speciesId
		)		
	{
		super(treeId,
			length, 
			diameter, 
			translateX, translateY, translateZ,
			voxx, voxy
			);
				
		this.speciesId = speciesId;
	}
}