import de.grogra.imp3d.shading.RGBAShader;
import java.util.Random;

public class Util
{	
	public static float random(float low, float high)
	{
		double stoc = Math.random() * (high-low);
		return low+(float)stoc;
	}
	
	public static int randomInt(float low, float high)
	{
		double stoc = Math.random() * (high-low);
		return (int)(low+stoc);
	}
	
	public static int random(int low, int high)
	{
		double stoc = Math.random() * (double)(high-low);
		return (int)(low+stoc);
	}
	
	public static RGBAShader getColorBiomass(float biMin, float biRange, float bi)
	{
		//color gradient dark green to light green		
		float pRateNorm = ((bi-biMin)/biRange);//*0.5f;
		float r = 0; float g = 0; float b = 0;
		return new RGBAShader(0,1-pRateNorm,0);
	}
	
	public static RGBAShader getColorRate(float pRateMin, float pRateRange, float pRate)
	{
		float pRateNorm = (pRate-pRateMin)/pRateRange;
		float r = 0; float g = 0; float b = 0;
		return new RGBAShader(pRateNorm,0,(1.0f-pRateNorm));
	}
}
