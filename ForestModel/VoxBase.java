import java.util.Hashtable;
import de.grogra.rgg.Library;
import de.grogra.xl.util.ObjectList;

/**
 * This class represents a 3d voxel in the regularly divided space.
 */
public class VoxBase
{
	public int x;
	public int y;
	public int z;
	
	public float light;      		//normalized amount of light available in this voxel
	public float leafArea;   		//total leaf area in this voxel**
	
	public ObjectList trees; 		//list of trees in this voxel
	
	public ObjectList branches;	 	//list of axes in this voxel
	public Hashtable leafAreaBranches;	//leaf area of a specific axis found in this voxel
	
	public Hashtable BProdTotnew; //total biomass produced for branches in this voxel //20150203**
	
	public float GR;
    
    public VoxBase(int x, int y, int z)
    {
		this.x = x;
		this.y = y;
		this.z = z;
		
		light=0;
		leafArea=0;
		
		this.GR = 0;
		
		trees = new ObjectList();
		branches = new ObjectList();
		
		leafAreaBranches = new Hashtable();
		BProdTotnew = new Hashtable();
    }
    
	public static VoxBase[][][] getVoxArray(int x, int y, int z)
    {
		VoxBase voxels[][][] = new VoxBase[x][y][z];
		
		for(int i=0; i<x; ++i)
		{
			for(int j=0; j<y; ++j)
			{
				for(int k=0; k<z; ++k)
				{
					voxels[i][j][k]=new VoxBase(i,j,k);	
				}
			}
		}
		
		return voxels;
    }
	
    public void addTree(SRootBase t)
    {
		trees.add(t);    
    }
    
    public SRootBase getTree(int index)
    {
        return (SRootBase)(trees.get(index));
    }
    
    public int getTreeCount()
    {
		return trees.size();    
    }
    
    public void removeTree(SRootBase t)
    {
		trees.remove(t);
    }
    
    public void removeTree(int index)
    {
		trees.remove(index);    
    }
    
    public void reset()
    {
		light=0;
		leafArea=0;
		
		trees.clear();
		
		branches.clear();
		leafAreaBranches.clear();
		BProdTotnew.clear();
    }
    
    public static void resetVoxels(VoxBase[][][] voxs)
    {
		//reset voxels
		for(int i=0; i<voxs.length; ++i)
		{
			for(int j=0; j<voxs[i].length; ++j)
			{
				for(int k=0; k<voxs[i][j].length; ++k)
				{
					voxs[i][j][k].reset();
				}
			}	
		}  
    }
    
    public float getLeafArea()
    {return leafArea;}
	
	public float sumLeafArea()
	{
		leafArea=0;//reset value
		for(int i=0; i<branches.size();++i)
		{
			leafArea += getLeafAreaAxis(((SBranch)(branches.get(i))).branchId);
		}
	}
	
	public void addBranch(SBranch b, float leafArea)
	{
		if(!branches.contains(b))
		{
			branches.add(b);
			leafAreaBranches.put(b.branchId, new Float(leafArea));
		}
	}
	
	public void removeBranch(SBranch b)
	{
		branches.remove(b);
		leafAreaBranches.remove(b.branchId);
		BProdTotnew.remove(b);
	}
	
	public SBranch getBranch(int index)
	{
		return (SBranch)(branches.get(index));
	}
	
	public int getBranchCount()
	{
		return branches.size();
	}
	
	public float getLeafAreaAxis(int branchId)
	{
		return ((Float)leafAreaBranches.get(branchId)).floatValue();
	}
	
	public void setLeafAreaAxis(int branchId, float leafArea)
	{
		leafAreaBranches.remove(branchId);
		leafAreaBranches.put(branchId, new Float(leafArea));
	}
	
	public boolean isRelated(SBranch b)
	{
		if(branches.contains(b))
			return true;
		
		return false;
	}
	
	public void setBProdTot(SBranch b, float BProdTot)
	{
		BProdTotnew.remove(b);
		BProdTotnew.put(b, new Float(BProdTot));
	}
	
	public Object getBProdTot(SBranch b)
	{
		return BProdTotnew.get(b);
	}
	
	public void removeBProdTot(SBranch b)
	{
		BProdTotnew.remove(b);
	}
}