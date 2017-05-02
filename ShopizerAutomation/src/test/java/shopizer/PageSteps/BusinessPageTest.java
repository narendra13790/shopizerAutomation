package shopizer.PageSteps;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import shopizer.PageObjects.BusinessBooksPage;
import shopizer.PageObjects.OrderCheckoutPage;
import shopizer.PageObjects.OrderConfirmationPage;
import shopizer.PageObjects.OrderReviewPage;
import shopizer.PageObjects.ProductInfoPage;
import shopizer.businessFunctions.AppFunctions;
import shopizer.seleniumCommonFunctions.Config;
import shopizer.seleniumCommonFunctions.SeleniumCommon;
import shopizer.utility.Excel_Reader;
import shopizer.utility.ExceptionHandler;

public class BusinessPageTest {

	
	
	SeleniumCommon selenium;
	Config config;

	BusinessBooksPage bbp;
	AppFunctions app;
	ProductInfoPage  prodInfo;
	OrderReviewPage orderReview;
	OrderCheckoutPage orderChkOut;
	OrderConfirmationPage orderConfirm;
	
	@BeforeTest
	public void setUp() throws Exception
	{
		selenium= new SeleniumCommon();
		config=Config.getInstance();
		selenium.openURL(config.getBaseURL());
		app=new AppFunctions(selenium);
		app.doLogin(config.getDefaultUserName(), config.getDefaultPasword());
		bbp= new BusinessBooksPage(selenium);
	
		prodInfo= new ProductInfoPage(selenium);
		orderReview	=new  OrderReviewPage(selenium);
		orderConfirm=new OrderConfirmationPage(selenium);
		orderChkOut= new OrderCheckoutPage(selenium);
		
	}
	
	
	@Parameters({"DropDownName","SortByName"})
	@Test(enabled=true)
	public void TC_01_Verify_Book_Search_ByName(@Optional("SORTFILTER")String sDropDownName,@Optional("Name")String sSortByValue) {

		try{
			
		// Step1 : after login Go to computer books page
				bbp.selectMenu("BusinessBooks");
				// Step 2: Select value from drop down
				bbp.selectDropDownValue(sDropDownName, sSortByValue);
				// Step 3:  Verify the sorted results
				Assert.assertTrue(bbp.verifySortedResults("BY_NAME",null,null));
	}catch(Exception e)
	{
		ExceptionHandler.addVerificationFailure(e);
		
	}
				
				
			}
			
			// 
				@Parameters({"DropDownName","SortByPrice"})
				@Test(enabled=true)
				public void TC_002_Verify_Search_ByPrice(@Optional("SORTFILTER")String sDropDownName,@Optional("Price")String sValue)
				{
					
					try{
						// Step1 : after login Go to computer books page
					bbp.selectMenu("BusinessBooks");
					// Step 2: Select value from drop down
					bbp.selectDropDownValue(sDropDownName, sValue);
					// Step 3:  Verify the sorted results
					Assert.assertTrue(bbp.verifySortedResults("BY_PRICE",null,null));
				}catch(Exception e)
				{
					ExceptionHandler.addVerificationFailure(e);
					
				}
}
				
				@Parameters({"DropDownName","SortByPrice","MinPrice","MaxPrice"})
				@Test(enabled=true)
				public void TC_003_Verify_Search_By_entering_min_max_price(@Optional("SORTFILTER")String sDropDownName,
						@Optional("Price")String sValue,@Optional("15")String sMinPrice,@Optional("40")String sMaxPrice)
				{
					
					try
					{
					// Step1 : after login Go to computer books page
					
					bbp.selectMenu("BusinessBooks");
					Thread.sleep(12000);
					// Step 2: Select value from drop down
					bbp.selectDropDownValue(sDropDownName, sValue);
					
					
					// Step 3 :
					bbp.enterMinMaxPrice(sMinPrice, sMaxPrice);
					Thread.sleep(10000);
					// Step 4:  Verify the sorted results
					
					Assert.assertTrue(bbp.verifySortedResults("BY_MIN_MAX_PRICE",Double.parseDouble(sMinPrice),Double.parseDouble(sMaxPrice)));
				}
				catch(Exception e)
				{
					e.printStackTrace();
					ExceptionHandler.addVerificationFailure(e);
				}	
				
				}
				
				@Parameters({"ItemName","customerId"})
				@Test(enabled=true)
				public void TC_004_Verify_ItemOrder_Completion(@Optional("The Big Switch")String sItemName,@Optional("1001")String sCustomerId)
				{
					
					try
					{
						//String[][]arrValues=Excel_Reader.getDataForTests(config.getTestDataFilePath(), "CustomerData", sCustomerId);
					// Step1 : after login Go to computer books page
					bbp.selectMenu("BusinessBooks");
					Thread.sleep(12000);
					// Step 2: Select item
					bbp.selectItem(sItemName);
					Thread.sleep(6000);
					/// Step 3 : review item details and add to cart
					prodInfo.reviewItemDetailsAndAddTocart(sItemName);
					Thread.sleep(2000);
					/// Step 4 : open cart and checkout 
					orderReview.openCartAncCheckOut();
					Thread.sleep(2000);
					/// Step 5 : place your item order
					orderReview.placeYourOrder();
					Thread.sleep(2000);
					//Step 6 : fill customer details and checkout the order
					orderChkOut.fillCustomerInfo(sCustomerId,"CustomerData");
					//step 7 : verify that order is placed successfully.
					
					Assert.assertTrue(orderConfirm.verfiyOrder());
					
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
					ExceptionHandler.addVerificationFailure(e);
				}	
				}
				
					@Parameters({"PublisherName"})
					@Test(enabled=true)
					public void TC_005_Verify_publisherLink(@Optional("Novells publishing")String PublisherName)
					{
						try {
							// Step1 : after login Go to computer books page
							bbp.selectMenu("BusinessBooks");
							// Step 2: Select PublisherName
							Assert.assertTrue(app.selectPublisher(PublisherName));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							ExceptionHandler.addVerificationFailure(e);
						}
						
					}
				
					
					
					
					
					@Test(enabled=true)
					public void TC_006_Verify_AllPublisherLink()
					{
						try {
							// Step1 : after login Go to computer books page
							bbp.selectMenu("BusinessBooks");
							// Step 2: Select PublisherName
							Assert.assertTrue(app.selectAllPublisher());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							ExceptionHandler.addVerificationFailure(e);
						}
					}
				
				
				@Parameters({"ItemName"})
				@Test(enabled=true)
				public void TC_007_Verify_facebook_share_link(@Optional("The Big Switch")String sItemName)
				{
					
					try
					{
						
					// Step1 : after login Go to computer books page
					bbp.selectMenu("BusinessBooks");
					Thread.sleep(12000);
					// Step 2: Select item
					bbp.selectItem(sItemName);
					Thread.sleep(6000);
					/// Step 3 : Click on facebook share link 
					Assert.assertTrue(prodInfo.shareBookDetailsOnFacebook("Facebook"));
					Thread.sleep(2000);
					
					
					}
					catch(Exception e)
					{
						e.printStackTrace();
						ExceptionHandler.addVerificationFailure(e);
					}
				}
				@AfterTest
				public void closeBrowser()
				{
					
					selenium.closeWebDriver();
				}
}
