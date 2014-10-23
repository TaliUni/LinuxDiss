package overview;

import java.io.File;

public class FileDisplay {
	//-------------------------------------new class for node, so that it contains all info of file, but only displays shortname---------
	/*    //if simply added a File, then the full path would show (as File.toString() contains the full path. If I used File.getName() then
	    //node would not recognise the node as a File, but as a string, so this overcomes that.*/
   
    	private String shortName;
    	private String path;
    	private File file;
    	
    	public FileDisplay(File file)
    	{
    		this.file = file;
    		shortName = file.getName();
    		path = file.getPath();
    	}
    	public FileDisplay(String string)
    	{
    		this.file = new File(string);
    		shortName = file.getName();
    		path = file.getPath();
    	}
    	
    	public File getFile()
    	{
    		return file;
    	}
    	
    	public String getShortName()
    	{
    		return shortName;
    	}
    	public String getPath()
    	{
    		return path;
    	}
    	public String toString()
    	{
    		
    		if (file.getName().isEmpty())
    		{
    			return file.getPath();
    		}
    		else
    		{
    			
    			return shortName;
    		}
    			
    		
    	}
    	
    	public boolean isDirectory()
    	{
    		return file.isDirectory();
    	}
    }


