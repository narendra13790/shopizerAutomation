package shopizer.PageObjects;

import java.util.TreeMap;

import org.apache.log4j.Logger;

import shopizer.businessFunctions.AppFunctions;
import shopizer.seleniumCommonFunctions.Config;
import shopizer.seleniumCommonFunctions.SeleniumCommon;
import shopizer.utility.ReadPropertyFile;

public class OrderConfirmationPage {

	
	
	SeleniumCommon selenium;
	AppFunctions app;
	Config config=Config.getInstance();
	
	ReadPropertyFile  readProps;
	TreeMap<String,String>commLocators;// map to store locator for Computer books object repository
	
	Logger log =Logger.getLogger(ComputerBooksPage.class);
	//Constructor
	public OrderConfirmationPage(SeleniumCommon selenium) throws InterruptedException {
		this.selenium=selenium;
		app=new AppFunctions(this.selenium);

		readProps= new ReadPropertyFile();
		commLocators=readProps.getLocatorMap(config.getCommonObjectFile());
	}
	
	public boolean verfiyOrder()
	{
		if(selenium.getElement(commLocators.get("commonObjects.OrderCompletion.Message")).isDisplayed())
		{
			String str=selenium.getElement(commLocators.get("commonObjects.OrderCompletion.Id")).getText();
			System.out.println("order id:"+str);
			return true;
		}
		else
		{
			return false;
		}
		
	}

}
