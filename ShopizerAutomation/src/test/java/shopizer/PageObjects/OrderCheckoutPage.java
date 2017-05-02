package shopizer.PageObjects;

import java.util.HashMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import shopizer.businessFunctions.AppFunctions;
import shopizer.seleniumCommonFunctions.Config;
import shopizer.seleniumCommonFunctions.SeleniumCommon;
import shopizer.utility.Excel_Reader;
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
	
	
	
	
	public void fillCustomerInfo(String sCustomerId,String sheetName) 
	{
		
		
		String[][]arrValues=Excel_Reader.getDataForTests(config.getTestDataFilePath(), sheetName, sCustomerId);
		
		selenium.enterText(commLocators.get("commonObjects.CheckOut.customer.firstName"),arrValues[0][1]);
		selenium.enterText(commLocators.get("commonObjects.CheckOut.customer.lastName"),arrValues[0][2]);
		selenium.enterText(commLocators.get("commonObjects.CheckOut.customer.emailAddress"),arrValues[0][3]);
		selenium.enterText(commLocators.get("commonObjects.CheckOut.customer.billing.company"),arrValues[0][4]);
		selenium.enterText(commLocators.get("commonObjects.CheckOut.customer.billing.address"),arrValues[0][5]);
		selenium.enterText(commLocators.get("commonObjects.CheckOut.customer.billing.city"),arrValues[0][6]);
		
		selenium.enterText(commLocators.get("commonObjects.CheckOut.billingPostalCode"),arrValues[0][7]);
		
	
		
		selenium.selectDropDown(selenium.getElement(commLocators.get("commonObjects.CheckOut.billingStateList"))
				,arrValues[0][8]);
		
		selenium.selectDropDown(selenium.getElement(commLocators.get("commonObjects.CheckOut.customerCountry")),arrValues[0][9]);
		
		selenium.enterText(commLocators.get("commonObjects.CheckOut.customerPhone"),arrValues[0][10]);
		
		
		selenium.selectCheckBox(commLocators.get("commonObjects.Checkout.customerAgreedButton"));
		
		selenium.click(commLocators.get("commonObjects.OrderSubmitButton"));
				
				
				
		
		
	}
//	public HashMap<String,String[]> getCustomerData(String sCustomerId)
//	{
//		HashMap<String,String[]>customerData= new HashMap<>();
//		String[]arrValues={"amir","suleman","email@abc.com","XYZ company","street address : B4/177 Jaipur ",
//				"city name abc","22001","Canada","Alberta","1234567894"};
//		customerData.put("1001", arrValues);
//		//CustomerId
//		return customerData;
//		
//	}

	
	
	
	

}
