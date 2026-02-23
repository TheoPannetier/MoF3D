import de.grogra.imp3d.shading.RGBAShader;
import java.util.Random;

public class Util
{	
	public static float getRandomUnif(Random rnd, float low, float high)
	{
		float stoc =  rnd.nextFloat() * (high - low);
		return low + stoc;
	}
	
	public static int getRandomUnifInt(Random rnd, float low, float high)
	{
		return (int)(getRandomUnifInt(rnd, low, high));
	}
	
	public static int getRandomUnifInt(Random rnd, int low, int high)
	{
		int diff =  high - low;
		return low + rnd.nextInt(diff);
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
