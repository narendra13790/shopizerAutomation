package shopizer.PageObjects;

import java.util.TreeMap;

import org.apache.log4j.Logger;

import shopizer.businessFunctions.AppFunctions;
import shopizer.seleniumCommonFunctions.Config;
import shopizer.seleniumCommonFunctions.SeleniumCommon;
import shopizer.utility.ReadPropertyFile;

public class ContactPage 
{

	SeleniumCommon selenium;
	AppFunctions app;
	Config config = Config.getInstance();

	ReadPropertyFile readProps;
	TreeMap<String, String> commLocators;// map to store locator for
												// Computer books object
												// repository

	Logger log = Logger.getLogger(ComputerBooksPage.class);

	public ContactPage(SeleniumCommon selenium) throws InterruptedException {
		this.selenium = selenium;
		app = new AppFunctions(this.selenium);

		readProps = new ReadPropertyFile();
		commLocators = readProps.getLocatorMap(config.getCommonObjectFile());
	}

	// It will return  the message after submitting your query
	public String getMessageSent()
	{
				
				selenium.enterText(commLocators.get("commonObjects.Contact.UserName"), "Narendra");
				selenium.enterText(commLocators.get("commonObjects.Contact.Email"), "Narendra@gmail.com");
				selenium.enterText(commLocators.get("commonObjects.Contact.Subject"), "I received wrong product/item");
				selenium.enterText(commLocators.get("commonObject.Contact.Commments"), "It was defect item");
				
				
				selenium.movetToElementAndClick(commLocators.get("commonObjects.ContactPage.SubmitButton"));
				
				app.closeAlert();// waiting for alert , if alert comes up then will close it
				
				return selenium.getElement(commLocators.get("commonObjects.Contact.Message")).getText();
				
	}
}
