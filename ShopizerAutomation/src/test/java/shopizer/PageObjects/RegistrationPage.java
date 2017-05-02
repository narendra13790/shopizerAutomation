package shopizer.PageObjects;

import java.util.TreeMap;

import shopizer.businessFunctions.AppFunctions;
import shopizer.seleniumCommonFunctions.Config;
import shopizer.seleniumCommonFunctions.SeleniumCommon;
import shopizer.utility.ReadPropertyFile;

public class RegistrationPage {

	SeleniumCommon selenium;
	AppFunctions app;
	ReadPropertyFile readProps;
	Config config=Config.getInstance();

	TreeMap<String,String>commLocators;
	public RegistrationPage(SeleniumCommon selenium) {
		this.selenium = selenium;
		app = new AppFunctions(this.selenium);
		readProps= new ReadPropertyFile();
		commLocators=readProps.getLocatorMap(config.getCommonObjectFile());

	}

	public void userRegister(String userName, String lName, String gender, String country, String state, String userId,
			String email, String password) throws InterruptedException {
		

		// click on register link
		selenium.getElement(commLocators.get("commonObject.SignLink")).click();
		
		selenium.getElement(commLocators.get("commonObject.RegLink")).click();
		
		// enter user name ,id ,gender, password , email id
		
		selenium.enterText(commLocators.get("commonObject.firstName"),userName);
		selenium.enterText(commLocators.get("commonObject.lastName"),lName);
		selenium.enterText(commLocators.get("commonObject.username"),userId);
		selenium.enterText(commLocators.get("commonObject.email"),email);
		selenium.enterText(commLocators.get("commonObject.password"),password);
		selenium.enterText(commLocators.get("commonObject.passwordAgain"),password);
		
		
		selenium.selectDropDown(selenium.getElement(commLocators.get("commonObject.genderDropDown")), gender);
		selenium.selectDropDown(selenium.getElement(commLocators.get("commonObject.countryDropDown")), country);
		selenium.selectDropDown(selenium.getElement(commLocators.get("commonObject.provinceDropDown")), state);
		
		// click on submit button
		selenium.getElement(commLocators.get("commonObject.submitRegButton")).click();
	}

}
