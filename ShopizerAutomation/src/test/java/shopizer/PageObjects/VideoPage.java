package shopizer.PageObjects;

import java.util.TreeMap;

import org.apache.log4j.Logger;

import shopizer.businessFunctions.AppFunctions;
import shopizer.seleniumCommonFunctions.Config;
import shopizer.seleniumCommonFunctions.SeleniumCommon;
import shopizer.utility.ExceptionHandler;
import shopizer.utility.ReadPropertyFile;

public class VideoPage 
{

	SeleniumCommon selenium;
	AppFunctions app;
	ReadPropertyFile readProps;
	Config config=Config.getInstance();

	TreeMap<String,String>commLocators;
	
	Logger log = Logger.getLogger(VideoPage.class);
	public VideoPage(SeleniumCommon selenium) {
		this.selenium = selenium;
		app = new AppFunctions(this.selenium);
		readProps= new ReadPropertyFile();
		commLocators=readProps.getLocatorMap(config.getCommonObjectFile());

	}
	/**
	 * 
	 * @param MenuName
	 *            - Menu Name
	 */
	public boolean selectMenu(String sMenuName) {
		try {
			return app.selectMenu(sMenuName);

		} catch (Exception e) {
			log.error("Failed to select business book menu : " + sMenuName);
			ExceptionHandler.addVerificationFailure(e);
			return false;
		}

	}

}
