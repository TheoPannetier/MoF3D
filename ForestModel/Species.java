public class Species
{
	public float ID;
	public float SLA;
	public float rhoW;
	public float LL;
	public float Nmass;
	public float RL;
	public float Gmax;
	public float k;
	public float FirstOrderPhyllotaxis;
	public int   FirstOrderPhyllotaxisNum;
	public float FirstOrderAngleSide; 
	public float HigherOrderAngle; 
	public float InternodeLengthTrunkMin;
	public float InternodeLengthTrunkMax; 
	public float InternodeLengthBranchMin;
	public float InternodeLengthBranchMax;
	public float kInt;
	public float TropismStrength; 
	public float LDRatioTrunk;
	public float ApicalDev; 
	public float IApical; 
	public float ShorteningFactor;
	public float maxPipeLength;
	public float StochasticityTwisting;
	public float StochasticityBranchingAngle;
	public float StochasticityTropism;
	public float StochasticityAnglePlane;
	public float StochasticityPhyllo;
	public float PipeReuseFactor;
	public float ALProdMax;
	

	public Species(
			float ID,
			float SLA,
			float rhoW,
			float LL,
			float Nmass,
			float RL,
			float Gmax,
			float k,
			float FirstOrderPhyllotaxis,
			float FirstOrderAngleSide, 
			float HigherOrderAngle, 
			float InternodeLengthTrunkMin,
			float InternodeLengthTrunkMax,
			float InternodeLengthBranchMin,
			float InternodeLengthBranchMax,
			float kInt,
			float TropismStrength, 
			float LDRatioTrunk, 
			float ApicalDev, 
			float IApical,
			float ShorteningFactor,
			float StochasticityTwisting,
			float StochasticityBranchingAngle,
			float StochasticityTropism,
			float StochasticityAnglePlane,
			float StochasticityPhyllo,
			float PipeReuseFactor,
			float ALProdMax
			 
		)
	{
		this.ID = ID;
		this.SLA = SLA;
		this.rhoW = rhoW;
		this.LL = LL;
		this.Nmass = Nmass;
		this.RL = RL;
		this.Gmax = Gmax;
		this.k = k;
		this.FirstOrderPhyllotaxis = 360.0f/FirstOrderPhyllotaxis ;
		this.FirstOrderPhyllotaxisNum = (int)FirstOrderPhyllotaxis ;
		this.FirstOrderAngleSide = 90.0f - FirstOrderAngleSide;
		this.HigherOrderAngle = HigherOrderAngle;
		this.InternodeLengthTrunkMin = InternodeLengthTrunkMin;
		this.InternodeLengthTrunkMax = InternodeLengthTrunkMax;
		this.InternodeLengthBranchMin = InternodeLengthBranchMin;
		this.InternodeLengthBranchMax = InternodeLengthBranchMax;
		this.kInt = kInt;
		this.TropismStrength = TropismStrength;
		this.LDRatioTrunk = LDRatioTrunk;
		this.ApicalDev = ApicalDev;
		this.IApical = IApical;
		this.ShorteningFactor = ShorteningFactor;
		this.maxPipeLength = 0 ;
		this.StochasticityTwisting = StochasticityTwisting ;
		this.StochasticityBranchingAngle = StochasticityBranchingAngle ;
		this.StochasticityTropism = StochasticityTropism ;
		this.StochasticityAnglePlane = StochasticityAnglePlane ;
		this.StochasticityPhyllo = StochasticityPhyllo ;
		this.PipeReuseFactor = PipeReuseFactor;
		this.ALProdMax = ALProdMax;
		
	}
}

