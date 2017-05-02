package shopizer.seleniumCommonFunctions;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverInit {

	private WebDriver driver;
	Config config;
	
	Logger log= Logger.getLogger(WebDriverInit.class);
	public WebDriverInit()
	{
		config=Config.getInstance();
		initWebDriver(config.getBrowserName());
		setImplicitWait(config.getImplicitWait());
		
	}
	private void initWebDriver(String sBrowserName)
	{
		log.info("Initailizing the WebDriver for broweser : "+sBrowserName);
		try {
			if(driver==null)
			{
				if(sBrowserName.toLowerCase().equals("firefox"))
				{
					
					//home/amir/workspace_automation/shopizer_automation/drivers/geckodriver
					 
					System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+config.getFirefoxDriverPath());
					
					DesiredCapabilities cap= DesiredCapabilities.firefox();
					cap.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
					driver= new FirefoxDriver(cap);
				}
				else if(sBrowserName.toLowerCase().equals("chrome"))
				{
					 
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+config.getChromeDriverPath());
					DesiredCapabilities cap= DesiredCapabilities.chrome();
					driver= new ChromeDriver(cap);
				}
				else if(sBrowserName.toLowerCase().equals("headless"))
				{
					 
					DesiredCapabilities cap= DesiredCapabilities.htmlUnit();
					cap.setJavascriptEnabled(false);
					cap.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
					driver= new HtmlUnitDriver(cap);
				}
				else
				{
					log.error(" Unkown browser name.");
				}
			}
		} 
		catch (Exception e) 
		{
			log.error("Error while initializing the web driver", e);
			e.printStackTrace();
			
		}
			
	}
	public WebDriver getWebDriver()
	{
		return driver;
	}
	public void setImplicitWait(int iSecond)
	{
		log.info(" Setting the implicit wait : "+iSecond +" Seconds");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(iSecond, TimeUnit.SECONDS);
	}
	public void closeWebDriver()
	{
		log.debug("Closing the browser....");
		if(driver!=null)
		{
			//driver.close(); / close just current activate window
			driver.quit(); // this will close all the open windows
			
		}
	}

}
