import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.grogra.rgg.Library;

/**
 * This class contains the growth model
 */
public class MGro
{
	/**
	* Computes gross light-dependent photosynthetic rate (g(C) g^-1(leaf) day^-1)
	* @param ratePhotosyn Maximum gross photosynthetic rate
	* @param light Light intensity in voxel
	* @param lightEx Light intensity at which photosynthetic rate is half of maximum
	*/
	public static float getCgross(float Gmax, float k, float SiteIndex,float light)
	{
		return ((Gmax * light) / (k + light))* SiteIndex ;
	}
	
	/**
	* Computes maintenance costs per leaf mass rate (g(C) g^-1(leaf) day^-1)
	* @param rw Maintenance cost per mass of sap wood
	* @param lp Length of pipe system (m)
	* @param lp_ratio_inv Inverse relationship between leaf area and pipe area.
	* @param dw Wood density
	* @param sla Specific leaf area
	*/
	public static float getRwtot(float rw, float lp, float lp_ratio_inv, float dw, float sla)
	{
		return rw * lp * 100.0f * lp_ratio_inv * dw * sla;
	}
	
	/**
	* Computes the net photosynthetic rate (g(C) g^-1(leaf) day^-1)
	* @param agross gross light-dependent photosynthetic rate
	* @param rl leaf maintenance rate
	* @param rwtot maintenance costs per leaf mass rate
	*/
	public static float getCnet(float agross, float rl, float rwtot)
	{
		return agross - rl - rwtot;
	}
	
	/**
	* Computes the increase in leaf biomass per unit of carbon (g(leaf) g^-1(C))
	* @param cbl_ratio ratio of carbon to leaf biomass
	* @param lp Length of pipe system
	* @param lp_ratio_inv Inverse relationship between leaf area and pipe area.
	* @param cbw_ratio ratio of carbon to woody biomass
	* @param dw wood density
	* @param sla specific leaf area
	* @param carbonOverhead carbon overhead
	*/
	public static float getCb(float cbl_ratio, float lp, float lp_ratio_inv, float cbw_ratio, 
		float dw, float sla, float carbonOverhead, float PipeReuse)
	{
		return 1.0f/((cbl_ratio + (PipeReuse *(lp  * 100.0f * lp_ratio_inv * cbw_ratio * dw * sla)) ) * carbonOverhead);
	}
	
	
	/** New Approach**/
	public static float GR(float GRmax, float Cb, float Cnet, float LL)
	{
		float GRpot=(Cb*Cnet-(1/LL));
		return Math.min(GRmax,GRpot);
	}
	
	public static float BIPot(float GR, float BL0, float LL, float tyear)
	{
		float BIPot=0;
		if(GR==0)
			{BIPot=BL0*(tyear/LL);}
		if(GR!=0)
			{BIPot=(float)(((BL0/GR)*Math.pow(2,(GR*tyear))-(BL0/GR))*(GR+(1/LL)));}
		return BIPot;
	}
	
	public static float tp(float BL0, float GR, float LL, float BI)
	{
		float tp=0;
		float BIused=Math.max(BI,0);
		
		if(GR==0)
			{tp=(BI*LL)/BL0;}
		if(GR!=0)
			{	//to get the log to the base 2, you have to devide the natural log by log(2)
				tp=(float)( (Math.log(((BIused*GR)/(BL0*(GR+(1/LL))))+1)/Math.log(2))/GR );
			}
		return tp;
	}
	
	public static float LeafBiomassNew(float BL0, float GR, float LL, float tyear, float tp)
	{
		return (float)(BL0*Math.pow(2,GR*tp)*Math.pow(2,-((tyear-tp)/LL)));
	}
	
/*
	public static float ALProdMaxLight(float light, float ALProdMax, float GridSize)
	{
		return (float) (ALProdMax*(Math.pow(GridSize,3))*(light/1200));
	}
	
	*/
	
	//Calculate new diameter based on leaf biomass production
	public static float getDiameterNew(float dia, float lpinv, float sla, float inc, float pipeReuseFactor)
	{
		return(float)(2*Math.sqrt( ((Math.pow((dia*100)/2,2)*Math.PI) + (lpinv * sla * inc* pipeReuseFactor))/Math.PI))/100.0f ;
	}
	
//*********************************************************

	public static float htFromDiaTrunk(float diameter, float diameterPrev, float length, float LDRatio, float LInc)
	{
		diameter = 100.0f*diameter;
		diameterPrev = 100.0f*diameterPrev;
		
		return (float)(length +
			(
			(LDRatio * Math.pow(diameter,0.666666666)) - 
			(LDRatio * Math.pow(diameterPrev,0.666666666))
			) * (1 + LInc)
			);
	}
	
	
	public static float htFromDiaTrunkCritical(float diameter, float LDRatioCrit)
	{
		diameter = 100.0f*diameter;
				
		return (float) (LDRatioCrit * Math.pow(diameter,0.666666666));
	}
	
	public static float htFromDiaBranch(float diameterNew, float LDRatio, float ShorteningFactor, float order, float length, float diameter)
	{
		diameter = 100.0f*diameter;
		diameterNew = 100.0f*diameterNew;
		return (float)	(length+((LDRatio * Math.pow(ShorteningFactor,order) * Math.pow(diameterNew,0.666666)) - 
					(LDRatio * Math.pow(ShorteningFactor,order) * Math.pow(diameter,0.666666))));

	}
	
	public static float htFromDiaBranchNewBranches(float diameterNew, float LDRatio, float ShorteningFactor, float order)
	{
		diameterNew = 100.0f*diameterNew;
				
		return (float)	
			(LDRatio * Math.pow(ShorteningFactor,order) * Math.pow(diameterNew,0.666666666)); 
			
	}
	
	/******************************************************************************************************************/
	/** Apical control*/

	public static double LInc(float ApicalDev, float light, float IApical, float ApicalShape)
	{
		return ApicalDev * Math.exp(-1.0*Math.pow((light/IApical), ApicalShape));
	}
	
	//Compute potential increase (dLTPot) in tree height based on the diameter growth in the previous year, 
	//The potential increase can be altered by apical dominance of the stem apical meristem (this process in expressed by LInc)
	public static float dLTPot(float light,	float diameter,	float diameterPrev, float LInc, 	float LDRatio)
		{
			diameter = diameter * 100.0f; //Convert diameter to cm
			diameterPrev = diameterPrev * 100.0f; //Convert diameter to cm
			return (float) (( (LDRatio * Math.pow((diameter + diameter - diameterPrev),0.6666666)) - 
					  (LDRatio * Math.pow((diameter),0.6666666))) * (1 + LInc));
		}
	
	//Computes the potential height increase up to the critical height for a defined diameter
	public static float dLTPotCrit(float diameterNew, float LDRatioCrit, float height)
		{
			diameterNew = diameterNew * 100.0f; //Convert diameter to cm
			return (float) ( (LDRatioCrit * Math.pow(diameterNew,0.666666666)) - height );
		}
		
	public static float dLBPot(float diameter, float diameterPrev, float length, float LDRatio, float ShorteningFactor,float order)
		{
			diameter = diameter * 100.0f; //Convert diameter to cm
			diameterPrev = diameterPrev * 100.0f; //Convert diameter to cm
			return (float) 	((LDRatio * Math.pow(ShorteningFactor,order) * Math.pow(diameter+diameter-diameterPrev,0.666666)) - 
						(LDRatio * Math.pow(ShorteningFactor,order) * Math.pow(diameter,0.666666)));
		}


	/******************************************************************************************************************/
	/** Title*/
	/**
	* Computes the increment of the cross sectional area of new segment
	*/
	public static float segmentAreaInc(float diameter, float length, float lengthPrev, float lengthInternodeLast)
	{
		return (float)(Math.pow(((diameter/2.0f) * ((length-lengthPrev+lengthInternodeLast)/length)),2.0) * Math.PI);
	}
	
	/**
	* Returns new leaf area for a leaf compartment newly related to a branch.
	*/
	public static float getNewLeafArea(float lpRatio, float diameter)
	{
		return (float)(lpRatio * Math.PI * Math.pow((diameter*100.0/2.0f),2.0));
	}
	
	public static float getInternodeLength(float internodeLengthMin, float internodeLengthMax, float kInt, float branchLengthPrev, float branchLength, float lengthInternodeLast)
	{
		return (float)(internodeLengthMin + ((internodeLengthMax-internodeLengthMin) / (1.0 + Math.exp(-kInt * (branchLength - branchLengthPrev + lengthInternodeLast) - internodeLengthMax *2.0))));
	}
	
	public static int getNumLateralBranches(float internodeLength, float branchLengthPrev, float branchLength, float lengthInternodeLast)
	{
		return (int)(Math.floor((branchLength - branchLengthPrev + lengthInternodeLast)/internodeLength));
	}
}