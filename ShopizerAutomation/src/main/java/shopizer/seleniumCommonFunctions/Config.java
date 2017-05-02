package shopizer.seleniumCommonFunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;



public class Config {
	
	private static Config instance;

	private String sBaseURL;
	
	private String sBrowserName;
	private String sObjectRepoPath;
	private String sImplicitWait;
	private String sExplicitWait;
	
	private Properties props;
	private FileInputStream fis;
	
	// Initialize the logger object	
	Logger log= Logger.getLogger(Config.class);

	private String sFirefoxDriverPath;

	private String sChromeDriverPath;

	private String sComputerBooksPageObjectsFile;

	private String sCommonObjectsFile;

	private String sDefaultPassword;

	private String sDefaultUserName;

	private String sBusinessBooksPageObjectFile;

	private String sTestDataFilePath;

	private String sScreenshotFilePath;
	
	private Config() 
	{
		readConfig();
	}
	public void readConfig()
	{
		log.info(" Reading the config properties .......");
		try 
		{
			
			fis= new FileInputStream("./src/test/resources/Config.properties");
			props= new Properties();
			
			props.load(fis);
			
			sBaseURL= props.getProperty("baseURL");
			sBrowserName=props.getProperty("browser");
			//Wait for a certain amount of time before throwing an exception that it cannot find the element on the page.
			sImplicitWait=props.getProperty("implicitWaitTime");
			//Wait for elements to become clickable, visible, invisible, etc
			sExplicitWait=props.getProperty("explicitWaitTime");
			sFirefoxDriverPath=props.getProperty("driverPath.firefox");
			sChromeDriverPath=props.getProperty("driverPath.chrome");
			
			sObjectRepoPath=props.getProperty("objectRepoPath");
			sComputerBooksPageObjectsFile=props.getProperty("objectRepoName.ComputerBooks");
			sCommonObjectsFile=props.getProperty("objectRepoName.CommonObjects");
			sDefaultUserName=props.getProperty("defaultUserName");
			sDefaultPassword=props.getProperty("defaultPassword");
			sBusinessBooksPageObjectFile=props.getProperty("objectRepoName.BusinessBooksPage");
			sTestDataFilePath=props.getProperty("testData.Path");
			sScreenshotFilePath=props.getProperty("screenshot.path");
			
			
		} 
		catch (Exception e) 
		{
			log.error("failed to read the config properties from Config.properties file, please see error message "+ e);
		}
		
		finally{
			
			if(fis!=null)
			{
				try {
					
					fis.close();
				} catch (IOException e) 
				{
					log.error(" Failed to close the file. "+e.getMessage(), e);
				}
			}
		}
		
	}
	
	
	public String getDefaultUserName() {
		return sDefaultUserName;
	}
	public String getScreenShotsPath() {
		return sScreenshotFilePath;
	}
	
	public String getDefaultPasword() {
		return sDefaultPassword;
	}
	public String getBaseURL() {
		return sBaseURL;
	}
	public String getBrowserName() {
		return sBrowserName;
	}
	public String getTestDataFilePath() {
		return sTestDataFilePath;
	}
	public String getObjectRepoPath() {
		return sObjectRepoPath;
	}
	public int getImplicitWait() {
		return Integer.parseInt(sImplicitWait);
	}
	public String getExplicitWait() {
		return sExplicitWait;
	}
	
	public String getFirefoxDriverPath() {
		return sFirefoxDriverPath;
	}

	public String getChromeDriverPath() {
		return sChromeDriverPath;
	}
	public String getComputerBooksObjectFile()
	{
		return sComputerBooksPageObjectsFile;
	}
	public String getBusinessBooksObjectFile()
	{
	
		return sBusinessBooksPageObjectFile;
	}
	public String getCommonObjectFile()
	{
		return sCommonObjectsFile;
	}

	public static Config getInstance()
	{
		if(instance==null)
		{
			instance=new Config();
			
			return  instance;
		}
		else{
			return instance;
		}
	}
	
	

	
	

}
