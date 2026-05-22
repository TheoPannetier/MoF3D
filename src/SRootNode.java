import de.grogra.turtle.Translate;

/**
* This is the fundamental class or module representing a tree
* with minimum parameters for simulating tree growth in forest models, 
* e.g. height, dbh.
* 
* To avoid using an additional Translate node in the instanced graph to
* represent the tree position, this class also extends the Translate node.
*/
public class SRootNode extends Translate implements S
{
    public float length;
    public float diameter;
    
    public SRootNode(float length, float diameter, float translateX, float translateY, float translateZ)
    {
		super(translateX, translateY, translateZ);
		
		this.length = length;
		this.diameter = diameter;
    }
}