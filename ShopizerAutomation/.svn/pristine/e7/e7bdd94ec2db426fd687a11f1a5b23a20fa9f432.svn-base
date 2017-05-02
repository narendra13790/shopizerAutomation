package shopizer.PageSteps;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import shopizer.PageObjects.ComputerBooksPage;
import shopizer.PageObjects.OrderReviewPage;
import shopizer.PageObjects.ProductInfoPage;
import shopizer.businessFunctions.AppFunctions;
import shopizer.seleniumCommonFunctions.Config;
import shopizer.seleniumCommonFunctions.SeleniumCommon;

public class OrderReviewTest

{
	SeleniumCommon selenium;
	Config config;
	ComputerBooksPage cbp;
	ProductInfoPage prodInfo;
	OrderReviewPage orderReview;

	AppFunctions app;

	@BeforeTest
	public void setUp() throws InterruptedException {
		selenium = new SeleniumCommon();
		config = Config.getInstance();
		selenium.openURL(config.getBaseURL());
		app = new AppFunctions(selenium);
		app.doLogin(config.getDefaultUserName(), config.getDefaultPasword());
		cbp = new ComputerBooksPage(selenium);
		prodInfo = new ProductInfoPage(selenium);
		orderReview = new OrderReviewPage(selenium);

	}

	@Parameters("ItemName")
	@Test(groups = { "SmokeTest" })
	public void TC_01_Verify_that_item_added_to_cart(@Optional("Node Web Development") String sItemName)
			throws InterruptedException {
		try {
			// Step1 : after login Go to computer books page
			cbp.selectMenu("ComputerBooks");
			Thread.sleep(12000);
			// Step 2: Select item
			cbp.selectItem(sItemName);
			Thread.sleep(6000);
			/// Step 3 : review item details and add to cart
			prodInfo.reviewItemDetailsAndAddTocart(sItemName);
			Thread.sleep(2000);
			/// Step 4 : open cart and verify that item is added to cart
			boolean bActualResult=orderReview.openCartAndVerifyItem(sItemName);
			
			Assert.assertEquals(true, bActualResult,"Item is not added to cart");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Parameters("ItemName")
	@Test(groups = { "SmokeTest" })
	public void TC_02_VerifyThat_Item_is_removed_from_cart(@Optional("Node Web Development")String sItemName)throws InterruptedException 
	{
		//No items in your shopping cart
		
			try {
				// Step1 : after login Go to computer books page
				cbp.selectMenu("ComputerBooks");
				Thread.sleep(12000);
				// Step 2: Select item
				cbp.selectItem(sItemName);
				Thread.sleep(6000);
				/// Step 3 : review item details and add to cart
				prodInfo.reviewItemDetailsAndAddTocart(sItemName);
				Thread.sleep(3000);
			
				/// Step 4: open cart , remove the item and verify that item is removed form cart
				String bActualResult=orderReview.openCartAndverifyRemoveItem(sItemName);
				
				Assert.assertEquals("No items in your shopping cart", bActualResult," Failed to verify the item removal test");
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@AfterTest
	public void closeBrowser()
	{
		
		selenium.closeWebDriver();
	}

}
