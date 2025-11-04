import de.grogra.xl.util.ObjectList;
import de.grogra.rgg.Library;

public class SpeciesPool
{
	public ObjectList speciesPool;
	public PP pp;
	public float CGrossMax;
	public float CnetMax;
	public float grMax;
	
	public SpeciesPool()
	{
		this.speciesPool = new ObjectList();
		CGrossMax = 0 ;
		CnetMax = 0 ;
		grMax= 0 ;
	}
	
	public void setPP(PP pp)
	{
		this.pp = pp;
	}
	
	public void clear()
	{
		this.speciesPool.clear();
	}
	
	public void addSpecies(Species s)
	{
		this.speciesPool.add(s);
	}
	
	public Species getSpecies(int index)
	{
		return (Species)(this.speciesPool.get(index));
	}
	
	public int getSpeciesCount()
	{
		return this.speciesPool.size();
	}
	
	public void generate()
	{
	
		for(int x=0; x<pp.get(PP.SpeciesNum);x++)
		{
			float SLAtemp=pp.getSLA();
			float Nmasstemp=pp.getNmass(SLAtemp);
			
			//ConversionFactor for photosynthetic rate: from seconds to day
			float BConv=(float)(3600*Math.pow(10,-9)*12*pp.get(PP.hsun));
			//ConversionFactor for leaf respiration: from seconds to day (we assume that respiration takes place 24hours)
			float BConv1=(float)(3600*Math.pow(10,-9)*12*24);
									
			Species s = new Species(
			x,
			SLAtemp,
			pp.getrhoW(),
			pp.getLL(SLAtemp),
			Nmasstemp,
			pp.getRL(SLAtemp,BConv1),
			pp.getGmax(SLAtemp,Nmasstemp,BConv),
			pp.getk(SLAtemp),
			pp.getFirstOrderPhyllotaxis(),
			pp.getFirstOrderAngleSide(),
			pp.getHigherOrderAngle(),
			pp.getInternodeLengthTrunkMin(),
			pp.getInternodeLengthTrunkMax(),
			pp.getInternodeLengthBranchMin(),
			pp.getInternodeLengthBranchMax(),
			pp.getkInt(),
			pp.getTropismStrength(),
			pp.getLDRatioTrunk(),
			pp.getApicalDev(),
			pp.getIApical(),
			pp.getShorteningFactor(),
			pp.getStochasticityTwisting(),
			pp.getStochasticityBranchingAngle(),
			pp.getStochasticityTropism(),
			pp.getStochasticityAnglePlane(),
			pp.getStochasticityPhyllo(),
			pp.getPipeReuseFactor(),
			pp.getALProdMax()
			);
		    this.addSpecies(s);
		    
		    //track max CGross
		    float temp = MGro.getCgross(s.Gmax, s.k, pp.get(PP.SiteIndex), pp.get(PP.Imax));
		    if(temp>CGrossMax)
			    CGrossMax = temp;
		    
		    //track max Cnet
		    float cnet = temp - s.RL;
		    if(cnet>CnetMax)
			    CnetMax = cnet;
		    
		    //trace max Cnet*CB-1/LL
		    float cb = (1.0f/(pp.get(PP.CBLratio) * pp.get(PP.C0)));
		    float temp3 = cb * cnet - (1.0f / s.LL);
		    if(temp3>grMax)
			    grMax = temp3;
		    
		    s.maxPipeLength = 0.7f * getMaxHeight( s.Gmax, pp.get(PP.Imax), pp.get(PP.SiteIndex), s.k, pp.get(PP.Rw), 1/pp.get(PP.LPratio), s.rhoW, s.SLA, s.RL, pp.get(PP.CBLratio),  pp.get(PP.CBWratio), pp.get(PP.C0),  s.LL,s.PipeReuseFactor);
		}
	}
	
	//Method to calulate theoretical maximum height
	private float getMaxHeight(float Gmax,float LightMax,float SiteIndex,float k,float Rw,
		float lp_ratio_inv,float rhoW,float SLA,float RL,float CBLRatio, float CBWRatio,float C0, float LeafLifeSpan, float dummyVarX)
	{
		float maxHeight=0;
		for(int i=0; i<10000; ++i)
		{
			float height=(float)i/100;
			
			float Cgross = MGro.getCgross(Gmax, k, SiteIndex,LightMax);
			float Rwtot = MGro.getRwtot(Rw, height, lp_ratio_inv, rhoW, SLA);
			float Cnet = MGro.getCnet(Cgross, RL, Rwtot);
			float Cb = MGro.getCb(CBLRatio, height, lp_ratio_inv, CBWRatio, rhoW, SLA, C0,dummyVarX);
			float BiomassChange=(Cnet*Cb)-(1/LeafLifeSpan);
			
			//Set max height by checking if positive growth is possible
			if(BiomassChange>0)
			{
				maxHeight=height;
			}
		}
		return maxHeight;
	}
}