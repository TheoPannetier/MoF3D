import de.grogra.graph.impl.Node;
//import de.grogra.rgg.Library;

public class SBud extends Node implements S
{
    public int treeId;
    public int branchId;
    public int order;
    
    public SBud(int treeId, int branchId, int order)
    {
		this.treeId = treeId;
		this.branchId = branchId;
		this.order = order;
    }
}