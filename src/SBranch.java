import de.grogra.graph.impl.Node;
import de.grogra.imp3d.shading.RGBAShader;
import de.grogra.xl.util.ObjectList;

public class SBranch extends Node implements S
{
	public int treeId;
	public int branchId;
	public float length;
	public float diameter;
	public float lengthPrev;
	public float diameterPrev;
	public float biomassInc;
	public float pos[];
	public float dLBPot;
	public float LInc;
	public int order;
	public float phylloCurr;
	public int phylloRound;
	public float leafBiomass;
	public float leafBiomassProduction;
	public float leafBiomassLoss;
	public float branchBiomass;
	public float branchBiomassProduction;
	public float branchBiomassLoss;
	
	public ObjectList voxels;
	public RGBAShader shader;
	public float lastLateralPos; //position or length along branch where last lateral branch was inserted
	
    public SBranch()
    {
		super();
    }
    
    public SBranch(int treeId, int branchId, float length, float diameter, int order)
    {
	    super();
		this.treeId = treeId;
		this.branchId = branchId;
		this.length = length;
		this.diameter = diameter;
		this.lengthPrev = length;
		this.diameterPrev = 0.0f;
		this.voxels = new ObjectList();
		this.biomassInc = 0;
		this.pos = null;
		this.dLBPot=0;
		this.LInc = 0;
		this.order = order;
		this.lastLateralPos = 0;
		this.phylloCurr= 0;
		this.phylloRound= 0 ;

		this.leafBiomass= 0 ;
		this.leafBiomassProduction= 0 ;
		this.leafBiomassLoss= 0 ;
	
		this.branchBiomass= 0 ;
		this.branchBiomassProduction= 0 ;
		this.branchBiomassLoss= 0 ;

    }
	
	public void addVox(int x, int y, int z)
	{
		if(getVox(x,y,z) == null)
			voxels.add(new int[]{x,y,z});
	}
	
	public void removeVox(int x, int y, int z)
	{
		for(int i=0; i<voxels.size(); ++i)
		{
			int[] vid = (int[])(voxels.get(i));
			if((vid[0]==x)&&(vid[1]==y)&&(vid[2]==z))
			{
				voxels.remove(i);
				return;
			}
		}
	}
	
	public int voxCount()
	{
		return voxels.size();
	}
	
	public int[] getVox(int index)
	{
		return (int[])(voxels.get(index));
	}
	
	public int[] getVox(int x, int y, int z)
	{
		for(int i=0; i<voxels.size(); ++i)
		{
			int[] vid = (int[])(voxels.get(i));
			if((vid[0]==x)&&(vid[1]==y)&&(vid[2]==z))
				return vid;
		}
		return null;
	}
	
	public void setShaderBiomass(float biMin, float biRange)
	{
		this.shader = Util.getColorBiomass(biMin,biRange,biomassInc);
	}
	
	public RGBAShader getShader()
	{
		return shader;
	}
	
	public float[] getPos()
	{
		return pos;
	}
	
	public void setPos(float x, float y, float z)
	{
		this.pos = new float[]{x,y,z};
	}
}