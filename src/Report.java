import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Calendar;
import javax.vecmath.Point3d;

import de.grogra.pf.data.Dataseries;
import de.grogra.pf.data.Dataset;
import de.grogra.rgg.Library;
import de.grogra.rgg.RGGRoot;
import de.grogra.grogra.Analysis;
import de.grogra.xl.util.ObjectList;

public class Report
{
	private static PrintWriter writer;
	
	/**
	* Creates a report file for a parameter for specified parameter pass and time step.
	*/
	public static PrintWriter startReport(int pass, int step, String paramName, String reportFolderPathAbs, boolean append, boolean isFileEachStep)
	{
	    try {
		    String filePath = reportFolderPathAbs + 
				File.separator + 
				paramName + 
				"_replicate_" + 
				pass; 
		    if(isFileEachStep)
			    {filePath += "_time_step_" + step;}
		    filePath += ".txt";
				
		    //create file instance with required path and naming
		    writer = new PrintWriter(new FileOutputStream(new File(filePath),append));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Writes a line in the current report file.
	*/
	public static void writeLine(String line)
	{
	    writer.println(line);
	}
	
	public static void write(String s)
	{
	    writer.print(s);
	}
	
	public static void append(String s)
	{
	    writer.append(s);
	}
	
	public static void appendLine(String s)
	{
	    writer.append(s);
	    writer.append("\n");
	}
	
	/**
	* close buffer
	*/
	public static void endReport()
	{
	    if(writer != null)
	    {
	        writer.close();
	    }
	}
	
	/**
	* Write time stamp line
	*/
	public static void writeTime()
	{
	    writeLine("Report Generated on " + getTime());
	}
	
	/**
	* Writes current simulation pass, time step as well as global parameters
	*/
	public static void writeGlobalParameters(PG pg)
	{
	    writeLine("Replicate\t" + Forest.currPass);
	    writeLine("Timestep\t" + Forest.currStep);
	    writeLine("MaxX\t" + pg.getInt(PG.MaxX));
	    writeLine("MaxY\t" + pg.getInt(PG.MaxY));
	    writeLine("MaxZ\t" + pg.getInt(PG.MaxZ));
	    writeLine("Corridor\t" + pg.get(PG.Corridor));
	    writeLine("GridSize\t" + pg.get(PG.GridSize));
	}
	
	public static void writeTotalEnergyUsed(float gEnergy, float gEnergyMin, float gEnergyMax, PG pg)
	{
	    float x = pg.get(PG.MaxX);
	    float y = pg.get(PG.MaxY);
	    float gridSize = pg.get(PG.GridSize);
	    float corr2 = pg.get(PG.Corridor)*2;
	    x -= corr2;
	    y -= corr2;
	    x /= gridSize;
	    y /= gridSize;
	    writeLine("ENERGY_USED_MEAN\t" + gEnergy/(x*y));
	    writeLine("ENERGY_USED_MIN\t" + gEnergyMin);
	    writeLine("ENERGY_USED_MAX\t" + gEnergyMax);
	}
	
	public static void writeGROGRAshoots(RGGRoot r)
	{
	    Dataset ds = Analysis.listOfAllShoots(r);
	    int rowCount = ds.getRowCount();
	    int columnCount = ds.getColumnCount();
	
	    for(int i=0; i<rowCount; ++i)
	    {
		Dataseries rowData = ds.getRow(i);
		for(int j=0; j<columnCount; ++j)
		{
			write(rowData.getCell(j).toString() + "\t");
		}
		write("\n");
	    }
	}
	
	/**
	* Get current date and time as a string.
	*/
	private static String getTime()
	{
	    Calendar cal = Calendar.getInstance();
	    String result = cal.get(Calendar.YEAR) + "_" +
					(cal.get(Calendar.MONTH)+1) + "_" +
					cal.get(Calendar.DAY_OF_MONTH) + "_" +
					cal.get(Calendar.HOUR_OF_DAY) + "_" +
					cal.get(Calendar.MINUTE) + "_" +
					cal.get(Calendar.SECOND) + "_" +
					cal.get(Calendar.MILLISECOND);
	    return result;
	}
	
	public static void writeTreeHeaders()
	{
		writeLine("treeID" + "\t" +
		"speciesID" + "\t" +
		"height" + "\t" +
		"diameter" + "\t" +
		"basalarea" + "\t" +
		"x" + "\t" +
		"y" + "\t" +
		"age" + "\t" +
		"diameterdelta" + "\t" +
		"heightdelta" + "\t" +
		"leafbiomass" + "\t" +
		"leafarea" + "\t" +
		"apicallight" + "\t"
		);
	}
	
	public static void writeTree(SRoot t, VoxBase[][][] vox, PG pg)
	{
		write(t.treeId + "\t");
		write(t.speciesId + "\t");
		write(t.length + "\t");
		write(t.diameter + "\t");
		float radius = t.diameter/2.0f;
		write(Math.PI*radius*radius + "\t");
		write(t.translateX + "\t");
		write(t.translateY + "\t");
		write(t.age + "\t");
		write((t.diameter-t.diameterPrev) + "\t");
		write((t.length-t.heightPrev) + "\t");
		write("\n");
	}
	
	
	public static void writeMortalityHeaders()
	{
		writeLine("treeID\tspeciesID\theight\tdiameter\tbasalarea\tx\ty\tage\tcauseDeath");
	}
	
	public static void writeMortality(SRootBase t, int causeDeath)
	{
		write(t.treeId + "\t");
		write(t.length + "\t");
		write(t.diameter + "\t");
		float radius = t.diameter/2.0f;
		write(Math.PI*radius*radius + "\t");
		write(t.voxX + "\t");
		write(t.voxY + "\t");
		write(t.age + "\t");
		write(causeDeath + "\t");
		write("\n");
	}
	
	private static void writeLightHeaders()
	{
		writeLine("x\ty\tlightAtFloor");
	}
	
	private static void writeVoxelHeaders()
	{
		writeLine("x\ty\tz\tleafarea");
	}
	
	public static void reportLight(int currPass, int currStep, String folderReport, VoxBase vox[][][], PG pg)
	{		
		if(currStep % pg.getInt(PG.ReportLight) == 0)
		{
			try
			{
				Report.startReport(currPass,currStep,"light",folderReport,false,true);
				Report.writeTime();
				Report.writeLightHeaders();
				
				for(int i=0; i< vox.length; ++i)
				{
					for(int j=0; j< vox[i].length; ++j)
					{
						writeVoxLight(i,j,vox[i][j], pg.get(PG.MaxZ));
					}	
				}
				
				Report.endReport();
			}
			catch(Exception ex)
			{
				Library.println("error" + ex.getMessage());
			}
		}
	}
	
	public static void writeVoxLight(int x, int y, VoxBase[] vox, float MaxZ)
	{
		write(x + "\t");
		write(y + "\t");
		write(vox[0].light + "\t");
		write("\n");
		

	}
	
	public static void reportVoxel(int currPass, int currStep, String folderReport, VoxBase vox[][][], PG pg)
	{	
		if(currStep % pg.getInt(PG.ReportVoxel) == 0)
		{
			try
			{
				Report.startReport(currPass,currStep,"voxel",folderReport,false,true);
				Report.writeTime();
				Report.writeVoxelHeaders();
				
				for(int i=0; i< vox.length; ++i)
				{
					for(int j=0; j< vox[i].length; ++j)
					{
						for(int k=0; k<vox[i][j].length; ++k)
						{
							writeVox(i,j,k,vox[i][j][k]);
						}
					}	
				}
				
				Report.endReport();
			}
			catch(Exception ex)
			{
				Library.println("error" + ex.getMessage());
			}
		}
	}
	
	public static void writeVox(int x, int y, int z, VoxBase vox)
	{
		write(x + "\t");
		write(y + "\t");
		write(z + "\t");
		write(vox.leafArea + "\t");
		write("\n");
	}
	
	
}