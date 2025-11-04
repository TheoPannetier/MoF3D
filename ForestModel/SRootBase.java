import de.grogra.turtle.Translate;

/**
* This is class or module represents a tree
* with additional parameters for simulating tree growth in forest models, 
* e.g. tree ID, voxel coordinates, age, height-diameter exponent, 
* specific leaf area, wood density.
*/
public class SRootBase extends SRootNode
{
    public int treeId;
	
    public int voxX;
    public int voxY;
	
    public int age;
	
    public float diameterPrev;
    public float heightPrev;
    
    public float biomassInc;
    public float dLTPot;
    public float LInc;
    public float crownRadius;
    
    public float leafBiomass;
    public float leafBiomassProduction;
    public float leafBiomassLoss;
    
    public float trunkBiomass;
    public float branchBiomass1stOrder;
    public float branchBiomass2ndOrder;
    
    public float trunkBiomassProduction;
    public float branchBiomass1stOrderProduction;
    public float branchBiomass2ndOrderProduction;

    public float trunkBiomassLoss;
    public float branchBiomass1stOrderLoss;
    public float branchBiomass2ndOrderLoss;

    public float xPositionBudMax;
    public float xPositionBudMin;
    public float yPositionBudMax;
    public float yPositionBudMin;
    
    public float heightFirstBranching;
     
            
    public SRootBase(int treeId, float length, float diameter, 
			float translateX, float translateY, float translateZ,
			int voxx, int voxy
			)
    
    {
        super(length,diameter,translateX, translateY, translateZ);
		
		this.treeId = treeId;

		this.voxX = voxx;
		this.voxY = voxy;
		
		this.age = 0;
		this.biomassInc = 0;
		this.diameterPrev=0.0f;
		this.heightPrev=0.0f;
		
		dLTPot = 0;
		LInc = 0;
		crownRadius = 0;
		
		this.leafBiomass= 0 ;
		this.leafBiomassProduction= 0 ;
		this.leafBiomassLoss= 0 ;
		
		this.trunkBiomass= 0 ;
		this.branchBiomass1stOrder= 0 ;
		this.branchBiomass2ndOrder= 0 ;
	
		
		this.trunkBiomassProduction= 0;
		this.branchBiomass1stOrderProduction= 0;
		this.branchBiomass2ndOrderProduction= 0;

		this.trunkBiomassLoss= 0;
		this.branchBiomass1stOrderLoss= 0;
		this.branchBiomass2ndOrderLoss= 0;
		
		this.xPositionBudMax=translateX;
		this.xPositionBudMin=translateX;
		this.yPositionBudMax=translateY;
		this.yPositionBudMin=translateX;
		 
		this.heightFirstBranching=0;

    }
    
	/**
	* Returns estimated basal area of the tree by computing cross-section
	* area using DBH.
	*/
    public float getBasalArea()
    {
		float radius = diameter/2.0f;
		return (float)(Math.PI * radius*radius);    
    }
    
	/**
	* Returns estimated trunk volume of tree.
	*/
    public float getVolume()
    {
		return length * getBasalArea();
    }
}