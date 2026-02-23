import de.grogra.rgg.Library;
import java.util.Random;

/**
 * This class contains parameters that vary with each pass of the simulation.
 */
public class PP extends P
{
	public static final String fileName = "Forest_param_pass";
	public static final String fileExt = ".txt";
	
	public static final String LIGHT_CONTRIBUTION_FACTOR = "LightC";
	public static final String ALMax = "ALMax"; 
	public static final String ALProdMax_Min = "ALProdMax_Min";
	public static final String ALProdMax_Max = "ALProdMax_Max"; 
	public static final String ApicalDominance_Min = "LDTreeDev_Min";
	public static final String ApicalDominance_Max = "LDTreeDev_Max"; 
	public static final String ApicalDev_Min = "BetaD_Min"; 
	public static final String ApicalDev_Max = "BetaD_Max"; 
	public static final String IApical_Min = "LightThreshApical_Min"; 
	public static final String IApical_Max = "LightThreshApical_Max"; 
	public static final String ApicalShape = "BetaS"; 
	public static final String C0 = "CarbonOverheadCosts"; 
	public static final String CBLratio = "CBLratio"; 
	public static final String CBWratio = "CBWratio"; 
	public static final String DisturbanceEffect = "MortalityDisturbanceRate"; 
	public static final String DisturbanceFrequency = "MortalityDisturbanceFrequency"; 
	public static final String FirstOrderAngleSide_Min = "AngleFirstOrderSideView_Min"; 
	public static final String FirstOrderAngleSide_Max = "AngleFirstOrderSideView_Max"; 
	public static final String FirstOrderPhyllotaxis_Min = "PhyllotaxisFirstOrder_Min"; 
	public static final String FirstOrderPhyllotaxis_Max = "PhyllotaxisFirstOrder_Max"; 
	public static final String HigherOrderAngle_Min = "AngleSecondOrderTopView_Min"; 
	public static final String HigherOrderAngle_Max = "AngleSecondOrderTopView_Max"; 
	public static final String Imax = "Imax"; 
	public static final String InitialDiamter = "InitialDiamter"; 
	public static final String InternodeLengthBranchMin_Min = "InternodeLengthBranchMin_Min";
	public static final String InternodeLengthBranchMin_Max = "InternodeLengthBranchMin_Max";
	public static final String InternodeLengthBranchMax_Min = "InternodeLengthBranchMax_Min";
	public static final String InternodeLengthBranchMax_Max = "InternodeLengthBranchMax_Max";
	public static final String InternodeLengthTrunkMin_Min = "InternodeLengthTrunkMin_Min";
	public static final String InternodeLengthTrunkMin_Max = "InternodeLengthTrunkMin_Max";
	public static final String InternodeLengthTrunkMax_Min = "InternodeLengthTrunkMax_Min";
	public static final String InternodeLengthTrunkMax_Max = "InternodeLengthTrunkMax_Max";
	public static final String kInt_Min = "KInt_Min";
	public static final String kInt_Max = "KInt_Max";
	public static final String kL = "LightExtinctionCoeff"; 
	public static final String LDratioBranch = "LDBranch"; 
	public static final String LPratio = "LPratio"; 
	public static final String LR = "DistanceVoxelLightCal"; 
	public static final String MinLeafArea = "MinLeafArea"; 
	public static final String MortalityRateSize = "MortalityBiomassRate";
	public static final String MortalityRateScalingExponent = "MortalityBiomassScalingExponent"; 
	public static final String MortalityTreeFallMin = "MortalityNeighMinDiameter"; 
	public static final String MortalityTreeFallRate = "MortalityNeighRate"; 
	public static final String Rw = "RespirationRateWood"; 
	public static final String SeedlingsPerHa_Min = "NumberSeedlingPerHa_Min"; 
	public static final String SeedlingsPerHa_Max = "NumberSeedlingPerHa_Max";
	public static final String ShorteningFactor_Min = "ShorteningFactor_Min"; 
	public static final String ShorteningFactor_Max = "ShorteningFactor_Max";
	public static final String SiteIndex = "SiteIndex"; 
	public static final String SLA_Min = "SLA_Min"; 	
	public static final String SLA_Max = "SLA_Max"; 
	public static final String SpeciesNum = "NumberSpecies";
	public static final String Stochasticity = "Stochasticity"; 
	public static final String StochasticityTwisting_Min = "StochasticityTwisting_Min";
	public static final String StochasticityTwisting_Max = "StochasticityTwisting_Max";
	public static final String StochasticityBranchingAngle_Min = "StochasticityAngleSecondOrderTopView_Min";
	public static final String StochasticityBranchingAngle_Max = "StochasticityAngleSecondOrderTopView_Max";
	public static final String StochasticityTropism_Min = "StochasticityTropismStrength_Min";
	public static final String StochasticityTropism_Max = "StochasticityTropismStrength_Max";
	public static final String StochasticityAnglePlane_Min = "StochasticityAngleFirstOrderSideView_Min";
	public static final String StochasticityAnglePlane_Max = "StochasticityAngleFirstOrderSideView_Max";
	public static final String StochasticityPhyllo_Min = "StochasticityAngleFirstOrderTopView_Min";
	public static final String StochasticityPhyllo_Max = "StochasticityAngleFirstOrderTopView_Max";
	public static final String StopCriterionBasalArea = "StopCriterionBasalArea";
	public static final String TropismStrength_Min = "TropismStrength_Min";
	public static final String TropismStrength_Max = "TropismStrength_Max";
	public static final String rhoW_Min = "WoodDensity_Min";
	public static final String rhoW_Max = "WoodDensity_Max";
	public static final String PipeReuseFactor_Min = "PipeReuseFactor_Min";
	public static final String PipeReuseFactor_Max = "PipeReuseFactor_Max";
	public static final String tyear = "Tyear";
	public static final String hsun = "Hsun";
	public static final String TreeCompetionNum = "TreeCompetionNum";
	public static final String TreeCompetionDist = "TreeCompetionDist";
	public static final String BranchMortMethod = "BranchMortMethod";
	public static final String BranchMortRandomRate = "BranchMortRandomRate";
	public static final String BranchMortMassRate = "BranchMortMassRate";
	public static final String BranchMortMassScalingExponent = "BranchMortMassScalingExponent";
	public static final String MortalityFallingTrees = "TreeMortNeigh";
	public static final String MortalityBiomass = "TreeMortBiomass";
	public static final String MortalityCarbonStarvation = "TreeMortCarbon";
	public static final String MortalityDisturbance = "TreeMortDist";
	public static final String PipeLengthMethod = "PipeLengthMethod";
	public static final String FormFactorWood = "FormFactorWood";
	public static final String MaxHeightReduction = "SafetyFactorTrunk";
	public static final String EdgeConditions = "EdgeC";
	public static final String CollidingBranches = "BrCollide";
	public static final String MinLeafAreaRatio = "MinLeafAreaRatio";
	
	//pre-computed values
	public float lightDistribution[]; //contribution of neighbouring voxels to light intensity in the middle voxel
		
    public static PP[] getParameterPassArray(int size)
    {
	    PP paramObjs[] = new PP[size];
	    for(int i=0; i< size; ++i)
	    {
		    paramObjs[i]=new PP();
	    }
	    return paramObjs;
    }
    
    /**
    * Converts basal area per unit area (m^2/hec to m^2/m^2)
    */
    
    public void precompute(
		float gridSize		//voxel size length (m)
		)
		{
		
		float lightContribution = get(LIGHT_CONTRIBUTION_FACTOR);
		
		if(lightContribution>1.0)
			lightContribution=1.0f;
		if(lightContribution<0.0)
			lightContribution=0.0f;
		
		float lightContribution1=(lightContribution/2.0f)+0.5f;
		
		//num of neighbouring voxels to include in the light intensity moderation
		float lightRangeInVoxels = (get(LR)+1);
		lightDistribution = new float[(int)(lightRangeInVoxels)];
			
		if(lightContribution==0){ //all voxels contribute equally to light in center voxel
			int voxelCount=1;
			for(int i=0; i<lightRangeInVoxels; ++i)
			{
				voxelCount += i*8;
			}
			float lightPerVoxel = 1.0f/voxelCount;
			for(int i=0; i< lightRangeInVoxels; ++i)
			{
				lightDistribution[i] = lightPerVoxel;
			}
		}
		else if(lightContribution==1.0){ //each ring contributes equal proportion of light, distributed evenly in voxels in the ring
			//even contribution factor for neighboring voxels
			float lightPerRing = 1.0f/lightRangeInVoxels;
			for(int i=0; i< lightRangeInVoxels; ++i)
			{
				int numVoxelsInRing = (i==0)?1:i*8;
				lightDistribution[i] = lightPerRing/numVoxelsInRing;
			}
		}
		else{//each ring contributes a decayed proportion of light from previous ring nearer to center voxel
			float lightInRing = 1.0f;
			float lightRemaining = 1.0f;
			for(int i=0; i< lightRangeInVoxels; ++i)
			{
				int numVoxelsInRing = (i==0)?1:i*8;
				
				if(i==lightRangeInVoxels-1)
				{
					lightInRing = lightRemaining;
					lightRemaining -= lightInRing;
				}
				else
				{
					lightInRing = lightRemaining*lightContribution1; //each successive ring of voxels contributions decays exponentially. less to light in middle
					lightRemaining -= lightInRing;
				}
				
				lightDistribution[i] = lightInRing/numVoxelsInRing;
			}
		}

    }
	
     //Parameter ranges of new pass file
     
       public float getApicalDev(Random rnd)
    {
		return Util.getRandomUnif(rnd, get(ApicalDev_Min),get(ApicalDev_Max));
    }
    
       public float getIApical(Random rnd)
    {
		return Util.getRandomUnif(rnd, get(IApical_Min),get(IApical_Max));
    }
    
       public float getFirstOrderAngleSide(Random rnd)
    {
		return Util.getRandomUnif(rnd, get(FirstOrderAngleSide_Min),get(FirstOrderAngleSide_Max));
    }
    
       public int getFirstOrderPhyllotaxis(Random rnd)
    {
		return(int)Util.getRandomUnif(rnd, get(FirstOrderPhyllotaxis_Min),get(FirstOrderPhyllotaxis_Max)+1);
    }
    
    
       public float getHigherOrderAngle(Random rnd)
    {
		return Util.getRandomUnif(rnd, get(HigherOrderAngle_Min),get(HigherOrderAngle_Max));
    }
    
   
    public float getInternodeLengthTrunkMin(Random rnd)
    {
		return Util.getRandomUnif(rnd, get(InternodeLengthTrunkMin_Min),get(InternodeLengthTrunkMin_Max));
    }
    
        public float getInternodeLengthTrunkMax(Random rnd)
    {
		return Util.getRandomUnif(rnd, get(InternodeLengthTrunkMax_Min),get(InternodeLengthTrunkMax_Max));
    }
    
    
    public float getInternodeLengthBranchMin(Random rnd)
    {
		return Util.getRandomUnif(rnd, get(InternodeLengthBranchMin_Min),get(InternodeLengthBranchMin_Max));
    }
    
    public float getInternodeLengthBranchMax(Random rnd)
     {
		return Util.getRandomUnif(rnd, get(InternodeLengthBranchMax_Min),get(InternodeLengthBranchMax_Max));
    }
    
    public float getkInt(Random rnd)
    {
		return Util.getRandomUnif(rnd, get(kInt_Min),get(kInt_Max));
    }
    
       public float getSeedlingsPerHa(Random rnd)
    {
		return Util.getRandomUnif(rnd, get(SeedlingsPerHa_Min),get(SeedlingsPerHa_Max));
    }

       public float getTropismStrength(Random rnd)
    {
		return Util.getRandomUnif(rnd, get(TropismStrength_Min),get(TropismStrength_Max));
    }
    
    	public float getSLA(Random rnd)
    {
		return Util.getRandomUnif(rnd, get(SLA_Min),get(SLA_Max));
    }
    
       public float getrhoW(Random rnd)
    {
	  	return Util.getRandomUnif(rnd, get(rhoW_Min),get(rhoW_Max));
    }
    
           public float getLDRatioTrunk(Random rnd)
    {
		return Util.getRandomUnif(rnd, get(ApicalDominance_Min),get(ApicalDominance_Max))+get(PP.LDratioBranch);
    }
    
     public float getShorteningFactor(Random rnd)
    {
		return Util.getRandomUnif(rnd, get(ShorteningFactor_Min),get(ShorteningFactor_Max));
    }
    
     public float getPipeReuseFactor(Random rnd)
    {
	    	return Util.getRandomUnif(rnd, get(PipeReuseFactor_Min),get(PipeReuseFactor_Max));
    }

    public float getALProdMax(Random rnd)
    {
		return Util.getRandomUnif(rnd, get(ALProdMax_Min),get(ALProdMax_Max));
    }
    
     
    //Estimation of additional leaf traits based on the SLA and Nmass following Wright et al. (2004) and Marino et al. (2010)
       public float getLL(float SLA)
    {
		return (float) (30 * Math.pow(10.0, (-1.294361 + 1.108207 * Math.log10(10000.0/SLA) )));
    }
    
       public float getNmass(float SLA)
    {
		return (float) (Math.pow(10.0,(1.414868 - 0.5898308 * Math.log10(10000.0/SLA))));
    }
   
       public float getRL(float SLA, float ConversionFactor)
    {
		return (float) (ConversionFactor * (Math.pow(10.0,(3.06 - 1.01 * Math.log10(10000.0/SLA)))));
    }
    
       public float getGmax(float SLA, float Nmass, float ConversionFactor)
    {
		return (float) (ConversionFactor * (Math.pow(10.0, (3.71 + 0.47 * Math.log10(Nmass) - 0.85 * Math.log10(10000.0/SLA)))));
    }
    
       public float getk(float SLA)
    {
		return (float) (Math.pow(10.0,(1.61 + 0.32 * Math.log10(10000.0/SLA))));
    }
    
    public float getStochasticityTwisting(Random rnd)
    {
	    return Util.getRandomUnif(rnd, get(StochasticityTwisting_Min),get(StochasticityTwisting_Max));
    }
    
    public float getStochasticityBranchingAngle(Random rnd)
    {
	    return Util.getRandomUnif(rnd, get(StochasticityBranchingAngle_Min),get(StochasticityBranchingAngle_Max));
    }
    
    public float getStochasticityTropism(Random rnd)
    {
	    return Util.getRandomUnif(rnd, get(StochasticityTropism_Min),get(StochasticityTropism_Max));
    }
    
    public float getStochasticityAnglePlane(Random rnd)
    {
	    return Util.getRandomUnif(rnd, get(StochasticityAnglePlane_Min),get(StochasticityAnglePlane_Max));
    }
  
     public float getStochasticityPhyllo(Random rnd)
    {
	    return Util.getRandomUnif(rnd, get(StochasticityPhyllo_Min),get(StochasticityPhyllo_Max));
    }
  
}