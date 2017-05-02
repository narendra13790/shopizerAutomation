package shopizer.PageObjects;

import shopizer.businessFunctions.AppFunctions;
import shopizer.seleniumCommonFunctions.SeleniumCommon;

public class LoginPage {

	SeleniumCommon selenium;
	AppFunctions app;
	
	public LoginPage(SeleniumCommon selenium) 
	{
		this.selenium=selenium;
		app=new AppFunctions(this.selenium);
		
	}

	public boolean loginTest(String defaultUserName, String defaultPasword) throws Exception 
	{
		 
		boolean bLogin=app.doLogin(defaultUserName, defaultPasword);
		return bLogin;
	}

}
