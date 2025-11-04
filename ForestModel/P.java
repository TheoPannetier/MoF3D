import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.StringTokenizer;

import de.grogra.rgg.Library;

/**
 * This class is the base class for parameters. It is inherited by classes
 * PG (parameter global) and PP (parameter pass).
 */
public class P
{
    //Hashtable containing parameter string key and float value pairs
    public Hashtable params;
    
    public P()
    {
	params = new Hashtable();
    }
    
    public void clear()
    {
	params.clear();
    }
    
    public float get(String key)
    {
	return ((Float)(params.get(key))).floatValue();
    }
    
    public int getInt(String key)
    {
	return (int)((Float)(params.get(key))).floatValue();
    }
    
    /**
    * This method reads a parameter file specified by the URL into the parameter object
    */
    public static void readFromFile(String absFileURL, P paramObj)
    {
	//check if parameter object is null
	if(paramObj == null)
		return;
	
	//open file with buffered reader 
        BufferedReader br = new BufferedReader(new FileReader(absFileURL));
        try {
            String line = br.readLine(); //read first line in file
			String[] tokens;
	    
			//read all lines in file
            while (line != null) {
                
				tokens = line.split("\t");
				if(tokens.length == 2)
				{
					paramObj.params.put(tokens[0], Float.parseFloat(tokens[1]));
				}
                line = br.readLine(); //read next line in file
            }
            
        } finally {
            br.close(); //close buffered reader
        }
    }
    
    public static String getFilePathGlobal(String paramFolderPath)
    {
	String absPath = 
	    paramFolderPath + 
	    File.separator +
	    PG.fileName +
	    PG.fileExt;
		
		return absPath;
    }
    
    public static String getFilePathPass(String paramFolderPath, int passNum)
    {
        String absPath = 
	    paramFolderPath + 
	    File.separator +
	    PP.fileName +
	    passNum +
	    PP.fileExt;
		
		return absPath;
    }
    
    /**
     * Read global parameters
     */
    public static void readGlobalParameters(P paramObj, String paramFolderPath)
    {
		String absPath = P.getFilePathGlobal(paramFolderPath);
		P.readFromFile(absPath, paramObj);
    }
    
    /**
     * Read parameters for each pass of simulation
     */
    public static void readPassParameters(P[] paramObjs, String paramFolderPath)
    {
		for(int i=0; i<paramObjs.length; ++i)
		{
			String absPath = P.getFilePathPass(paramFolderPath,i);
			P.readFromFile(absPath, paramObjs[i]);
		}
    }
    
    /**
     * Constructs an array of parameter pass objects
     */
    public static P[] getParameterArray(int size)
    {
		P paramObjs[] = new P[size];
		for(int i=0; i< size; ++i)
		{
			paramObjs[i]=new P();
		}
    }
}