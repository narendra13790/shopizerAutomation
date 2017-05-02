package shopizer.PageObjects;

import java.util.TreeMap;

import org.apache.log4j.Logger;

import shopizer.businessFunctions.AppFunctions;
import shopizer.seleniumCommonFunctions.Config;
import shopizer.seleniumCommonFunctions.SeleniumCommon;
import shopizer.utility.ReadPropertyFile;

public class OrderReviewPage {

	
	
	SeleniumCommon selenium;
	AppFunctions app;
	Config config=Config.getInstance();
	
	ReadPropertyFile  readProps;
	TreeMap<String,String>commLocators;// map to store locator for Computer books object repository
	
	Logger log =Logger.getLogger(ComputerBooksPage.class);
	//Constructor
	public OrderReviewPage(SeleniumCommon selenium) throws InterruptedException {
		this.selenium=selenium;
		app=new AppFunctions(this.selenium);
		
		readProps= new ReadPropertyFile();
		commLocators=readProps.getLocatorMap(config.getCommonObjectFile());
	}

	/**
	 * open the cart and checkout  your  product
	 */
	public void openCartAncCheckOut() 
	{

		selenium.getElement(commLocators.get("commonObject.OpenCartButton")).click();
		selenium.getElement(commLocators.get("commonObject.CheckoutButton")).click();
			
		
	}

	public void placeYourOrder() {

		selenium.getElement(commLocators.get("commonObject.PlaceOrderButton")).click();
	}

	public boolean openCartAndVerifyItem(String sItemName) 
	{
		
		selenium.getElement(commLocators.get("commonObject.OpenCartButton")).click();
		
		String sItemNameInCart=selenium.getElement(commLocators.get("commonObject.ItemNameinCart").
				replace("#item#", sItemName)).getText();
		
		if(sItemNameInCart.contains(sItemName)) 
		{
			//selenium.getElement(commLocators.get("commonObject.DeleteItemFromCart")).click();
			// after verifying the item  , remove it from cart
			selenium.getElement(commLocators.get("commonObject.OpenCartButton")).click();
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	

	
	public String openCartAndverifyRemoveItem(String sItemName) 
	{
		
		// open cart and verify item is there in cart 
		selenium.getElement(commLocators.get("commonObject.OpenCartButton")).click();
		
		String sItemNameInCart=selenium.getElement(commLocators.get("commonObject.ItemNameinCart").
				replace("#item#", sItemName)).getText();
		
		if(sItemNameInCart.contains(sItemName)) 
		{
		  // now remove the item from cart
			selenium.getElement(commLocators.get("commonObject.DeleteItemFromCart")).click();
			
			// now verify the item count and verify message like no item in cart.
			///String itemCount=selenium.getElement(commLocators.get("commonObject.NoItemInCart")).getText();
			
			selenium.getElement(commLocators.get("commonObject.OpenCartButton")).click();
			
			String NoItemMsg=selenium.getElement(commLocators.get("commonObject.NoItemInCartMsg")).getText();
			
			return NoItemMsg;
			
		}
		else
		{
			return "";
		}
		
		
		
	}
	
	

}
