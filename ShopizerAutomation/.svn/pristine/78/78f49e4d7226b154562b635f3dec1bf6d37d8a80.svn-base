package shopizer.PageObjects;

import java.util.HashMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import shopizer.businessFunctions.AppFunctions;
import shopizer.seleniumCommonFunctions.Config;
import shopizer.seleniumCommonFunctions.SeleniumCommon;
import shopizer.utility.ReadPropertyFile;

public class OrderCheckoutPage {

	
	SeleniumCommon selenium;
	AppFunctions app;
	Config config=Config.getInstance();
	
	ReadPropertyFile  readProps;
	TreeMap<String,String>commLocators;// map to store locator for Computer books object repository
	
	Logger log =Logger.getLogger(ComputerBooksPage.class);
	//Constructor
	public OrderCheckoutPage(SeleniumCommon selenium) throws InterruptedException {
		this.selenium=selenium;
		app=new AppFunctions(this.selenium);

		readProps= new ReadPropertyFile();
		commLocators=readProps.getLocatorMap(config.getCommonObjectFile());
	}
	
	
	
	
	public void fillCustomerInfo(String sCustomerId) 
	{
		
		HashMap<String,String[]>customerData=getCustomerData(sCustomerId);
		
		String[]arrValues=customerData.get(sCustomerId);
		
		selenium.enterText(commLocators.get("commonObjects.CheckOut.customer.firstName"),arrValues[0]);
		selenium.enterText(commLocators.get("commonObjects.CheckOut.customer.lastName"),arrValues[1]);
		selenium.enterText(commLocators.get("commonObjects.CheckOut.customer.emailAddress"),arrValues[2]);
		selenium.enterText(commLocators.get("commonObjects.CheckOut.customer.billing.company"),arrValues[3]);
		selenium.enterText(commLocators.get("commonObjects.CheckOut.customer.billing.address"),arrValues[4]);
		selenium.enterText(commLocators.get("commonObjects.CheckOut.customer.billing.city"),arrValues[5]);
		
		selenium.enterText(commLocators.get("commonObjects.CheckOut.billingPostalCode"),arrValues[6]);
		
	
		
		selenium.selectDropDown(selenium.getElement(commLocators.get("commonObjects.CheckOut.billingStateList"))
				,arrValues[7]);
		
		selenium.selectDropDown(selenium.getElement(commLocators.get("commonObjects.CheckOut.customerCountry")),arrValues[8]);
		
		selenium.enterText(commLocators.get("commonObjects.CheckOut.customerPhone"),arrValues[9]);
		
		
		selenium.selectCheckBox(commLocators.get("commonObjects.Checkout.customerAgreedButton"));
		
		selenium.click(commLocators.get("commonObjects.OrderSubmitButton"));
				
				
				
		
		
	}
	public HashMap<String,String[]> getCustomerData(String sCustomerId)
	{
		HashMap<String,String[]>customerData= new HashMap<>();
		String[]arrValues={"amir","suleman","email@abc.com","XYZ company","street address : B4/177 Jaipur ",
				"city name abc","22001","United States","Iowa","1234567894"};
		customerData.put("1001", arrValues);
		return customerData;
		
	}
	
	
	
	

}
