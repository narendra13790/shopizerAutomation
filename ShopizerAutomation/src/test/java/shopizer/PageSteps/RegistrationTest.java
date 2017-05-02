package shopizer.PageSteps;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import shopizer.PageObjects.RegistrationPage;
import shopizer.seleniumCommonFunctions.Config;
import shopizer.seleniumCommonFunctions.SeleniumCommon;
import shopizer.utility.ExceptionHandler;

public class RegistrationTest {

	SeleniumCommon selenium;
	Config config;
	RegistrationPage regPage;
	
	@BeforeTest
	public void setUp()
	{
		selenium= new SeleniumCommon();
		config=Config.getInstance();
		selenium.openURL(config.getBaseURL());
		regPage= new RegistrationPage(selenium);
	}
	@Test(dataProvider="newUserData")
	public void userRegistration(String userName,String lName,String gender,String country, String state,String userId,String email,String  password) throws InterruptedException
	{
	
		
		try {
			regPage.userRegister(userName, lName, gender, country, state, userId, email, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExceptionHandler.addVerificationFailure(e);
		}
		
	}
	@DataProvider(name="newUserData")
	public Iterator<Object[]> getInputData()
	{
		ArrayList<Object[]> objectList= new ArrayList<>();
		Object[]arrValues={"Gill","Sing","Male","United States","Iowa","gill16","amir@abc.com","gill16"};
		
		objectList.add(arrValues);
		//objectList.add(arrValues2);
		return objectList.iterator();
	}
	@AfterTest
	public void closeBrowser()
	{
		selenium.closeWebDriver();
	}

}
