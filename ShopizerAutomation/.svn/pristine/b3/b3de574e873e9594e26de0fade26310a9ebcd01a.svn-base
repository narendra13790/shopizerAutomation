package shopizer.PageSteps;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import shopizer.PageObjects.LoginPage;
import shopizer.seleniumCommonFunctions.Config;
import shopizer.seleniumCommonFunctions.SeleniumCommon;

public class LoginSteps {

	LoginPage lp;
	SeleniumCommon selenium;
	Config config;
	
	@BeforeTest
	public void setUp()
	{
		selenium= new SeleniumCommon();
		config=Config.getInstance();
		selenium.openURL(config.getBaseURL());
		lp= new LoginPage(selenium);
	}
	@Test
	public void loginTest() throws InterruptedException
	{
		System.out.println(""+config.getDefaultUserName());
		System.out.println(""+config.getDefaultPasword());
		boolean isLogin=lp.loginTest(config.getDefaultUserName(),config.getDefaultPasword());
		Assert.assertTrue(isLogin,"Login is Failed");
	}
	@AfterTest
	public void closeBrowser()
	{
		selenium.closeWebDriver();
	}

}
