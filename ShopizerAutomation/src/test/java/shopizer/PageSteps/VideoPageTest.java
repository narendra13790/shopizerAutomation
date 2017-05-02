package shopizer.PageSteps;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import shopizer.PageObjects.VideoPage;
import shopizer.businessFunctions.AppFunctions;
import shopizer.seleniumCommonFunctions.Config;
import shopizer.seleniumCommonFunctions.SeleniumCommon;
import shopizer.utility.ExceptionHandler;

public class VideoPageTest {

	SeleniumCommon selenium;
	Config config;
	AppFunctions app;

	VideoPage vp;
	@BeforeTest
	public void setUp() throws Exception {
		selenium = new SeleniumCommon();
		config = Config.getInstance();
		selenium.openURL(config.getBaseURL());
		app = new AppFunctions(selenium);
		vp= new VideoPage(selenium);

	}
	
	public void TC_001_verify_video_streaming()
	{
		try {
			// Step1 :select videos link
			vp.selectMenu("Videos");
			// Step 2: play the video
			Assert.assertTrue(app.playMovie("Pavarotti", 10));
			Assert.assertTrue(app.playMovie("Andrea Bochelli", 10));
			Assert.assertTrue(app.playMovie("Phil Callins", 10));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExceptionHandler.addVerificationFailure(e);
		}
	}
	
	@AfterTest
	public void closeBrowserName()
	{
		
		selenium.closeWebDriver();
	}
	
}
