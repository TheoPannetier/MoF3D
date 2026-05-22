import de.grogra.imp3d.shading.RGBAShader;
import de.grogra.turtle.F;

public class SInternode extends F implements S
{
    public int voxX; //index of voxel in which branch resides
    public int voxY; //index of voxel in which branch resides
    public int voxZ; //index of voxel in which branch resides
    public int treeId;
    public int branchId;
    public int order;
	
    public SInternode()
    {
		super();
    }
    
    public SInternode(int treeId, int branchId, float length, float diameter, int order)
    {
		super(length,diameter);
		this.diameter=diameter*5; //for visual aspects
		this.treeId = treeId;
		this.branchId = branchId;
		this.order = order;
		
		this.setShader(new RGBAShader(0.55f,0.27f,0.075f));
    }
}