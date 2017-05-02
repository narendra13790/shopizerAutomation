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
	
		
		regPage.userRegister(userName, lName, gender, country, state, userId, email, password);
		
	}
	@DataProvider(name="newUserData")
	public Iterator<Object[]> getInputData()
	{
		ArrayList<Object[]> objectList= new ArrayList<>();
		Object[]arrValues={"amir","suleman","Male","United States","Iowa","nasami14","amir@abc.com","nasami14"};
		//Object[]arrValues2={"Amir","Suleiman","Male","United States","Iowa","amir123","amir@abc.com","amir123"};
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
