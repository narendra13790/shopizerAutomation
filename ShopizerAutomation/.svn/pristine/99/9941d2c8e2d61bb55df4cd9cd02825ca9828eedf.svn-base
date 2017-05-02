package shopizer.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import shopizer.seleniumCommonFunctions.Config;
/**
 * 
 * @author narendra gurjar
 *
 */
public class ReadPropertyFile
{
	private FileInputStream objFis = null;
	private Properties objProps = null;
	private static Logger logger = Logger.getLogger(ReadPropertyFile.class);

	public void loadFile(String sFileName)
	{
		try
		{
			// getObjectRepoPath method returns base path of all properties file
			//Config.getInstance().getObjectRepoPath() : /src/test/resources/ObjectRepository/
			
			//sFileName: it is the name for file inside object repository and could be CommonObjects.Prpperties or
			// ComputerBooks PageObject .prpperties , but we will make more afterwards
			
			///src/test/resources/ObjectRepository/CommonObjects.properties 
			//src/test/resources/ObjectRepository/CommonObjects.properties
			//"sFullFilePath"	/home/amir/workspace_automation/shopizer_automation/shopizer_automation/src/test/resources/ObjectRepository/CommonObjects.properties	
//"sFullFilePath"	/home/amir/workspace_automation/shopizer_automation/src/test/resources/ObjectRepository/CommonObjects.properties	

			String sFullFilePath =System.getProperty("user.dir")+Config.getInstance().getObjectRepoPath()+sFileName;
			objFis = new FileInputStream(sFullFilePath);
			objProps = new Properties();
			objProps.load(objFis);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (objFis != null)
			{
				try
				{
					objFis.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
					logger.error("An error occurred while loading the file : " + sFileName, e);
				}
			}
		}
	}

	

	public TreeMap<String, String> getLocatorMap(String sFilePath) 
	{
		loadFile(sFilePath);
		TreeMap<String, String> map = new TreeMap<String, String>();
		Set<Entry<Object, Object>> setProps = objProps.entrySet();
		
		Iterator<Entry<Object, Object>> itr = setProps.iterator();
		while (itr.hasNext())
		{
			Entry<Object,Object> keyValues= itr.next();
			map.put(String.valueOf(keyValues.getKey()), String.valueOf(keyValues.getValue()));
		}
		return map;
	}

	

	
	

	
}
