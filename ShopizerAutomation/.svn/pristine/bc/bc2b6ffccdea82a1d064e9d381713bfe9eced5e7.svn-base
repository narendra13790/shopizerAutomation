package shopizer.PageObjects;

import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.Actions;

import shopizer.businessFunctions.AppFunctions;
import shopizer.seleniumCommonFunctions.Config;
import shopizer.seleniumCommonFunctions.SeleniumCommon;
import shopizer.utility.ReadPropertyFile;

public class ProductInfoPage {

	
	SeleniumCommon selenium;
	AppFunctions app;
	Config config=Config.getInstance();
	
	ReadPropertyFile  readProps;
	TreeMap<String,String>commLocators;// map to store locator for Computer books object repository
	
	Logger log =Logger.getLogger(ComputerBooksPage.class);
	//Constructor
	public ProductInfoPage(SeleniumCommon selenium) throws InterruptedException {
		this.selenium=selenium;
		app=new AppFunctions(this.selenium);

		readProps= new ReadPropertyFile();
		commLocators=readProps.getLocatorMap(config.getCommonObjectFile());
	}
	
	/**
	 * Add the item into cart
	 * @param sItemName
	 */
	public void reviewItemDetailsAndAddTocart(String sItemName) throws Exception
	{
			Actions action=  new Actions(selenium.getWebDriver());
			action.doubleClick(selenium.getElement(commLocators.get("commonObjects.ItemInfoPage.AddToCart"))).build().perform();
		
	}

	public boolean shareBookDetailsOnFacebook(String fbHomeLinkText) 
	{
		
		// switch to iframe bbcause th facebook share button exist inside  the frame 
		selenium.getWebDriver().switchTo().frame(selenium.getElement(commLocators.get("commonObjects.iFrame")));
		
		selenium.click(commLocators.get("commonObjects.FaceBookShareButton"));
		
		// now switch  back to default content from iFrame
		selenium.getWebDriver().switchTo().defaultContent();
		
		// save current window d before switching to new window
		String mainWindowId=selenium.getWebDriver().getWindowHandle();
		
		//now switch to other window
		selenium.switchToWindow(mainWindowId);
		

		  //String ActualWindowTitel=selenium.getTitle();
		  String fbLinkText= selenium.getElement(commLocators.get("commonObjects.FaceBookHomeLink")).getText();// get the face book link text
		
		  if(fbLinkText.contains(fbHomeLinkText) && selenium.getCurrentURL().contains("https://www.facebook.com"))
		  {
			  selenium.getWebDriver().switchTo().window(mainWindowId);
			  return true;
		  }
		  else
		  {
			  selenium.getWebDriver().switchTo().window(mainWindowId);
			  return false;
		  }
		
	}

}
