package shopizer.seleniumCommonFunctions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import shopizer.utility.ExceptionHandler;


public class SeleniumCommon extends WebDriverInit {

	private WebDriverWait objWebDriverWait;
	
	Logger log=Logger.getLogger(SeleniumCommon.class);

	// this is constructor
	public SeleniumCommon() {
		super();// calling to base class constructor
	}

	public void openURL(String URL) 
	{
		log.debug("opening the URL :"+URL);
		getWebDriver().manage().window().maximize();
		getWebDriver().navigate().to(URL);
		getWebDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		// waitForPageLoad();
	}

	/**
	 * get title of the web page
	 * 
	 * @return it return the title of current web page as a String
	 */
	public String getTitle() {
		log.debug("getting the title of the page");
		return getWebDriver().getTitle();
	}

	public String getCurrentURL() {
		log.debug("Current URL : "+getWebDriver().getCurrentUrl());
		return getWebDriver().getCurrentUrl();
	}

	/**
	 * get the WebDriverWait object.
	 * 
	 * @param iSecond
	 *            - it takes explicit wait time in second
	 * @return it return the WebDriverWait object
	 */
	public WebDriverWait getWebDriverWait(int iSecond) {
		log.debug("initializing the WebDriverWait class  with timeout : "+iSecond);
		objWebDriverWait = new WebDriverWait(getWebDriver(), iSecond);
		return objWebDriverWait;
	}

	public WebDriverWait getWebDriverWait() {
		log.debug("initializing the WebDriverWait class with default timeout");
		objWebDriverWait = new WebDriverWait(getWebDriver(), Integer.parseInt(Config.getInstance().getExplicitWait()));
		return objWebDriverWait;
	}
	/**
	 * this method will take String parameter which is the value from
	 * ObjectRepository something like this- ID:=email
	 * 
	 * @return it returns WebElement
	 * @param sLocatorValue
	 */

	public WebElement getElement(String sLocatorValue) {
		String arrLocator[] = null;
		WebElement ele = null;
		try {

			arrLocator = sLocatorValue.split(":=");// ID:=signin_userName

			if (arrLocator.length != 2) {
				throw new Exception("Locator value is not correct");
			}

			// now arrLocator[0] contains value for switch case
			// and arrLocator[1] contains the value for web element
			
			switch (arrLocator[0].toUpperCase().trim())// arrLocator[0] -> 0th index
													// will be having
													// locatorType from
													// arrLocator
			{
			case "XPATH":

				ele = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(arrLocator[1])));

				break;
			case "ID":
				ele = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id(arrLocator[1])));
				break;
			case "LINKTEXT":
				ele = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText(arrLocator[1])));
				break;
			case "PARTIALLINKTEXT":

				ele = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(arrLocator[1])));
				break;
			case "TAGNAME":

				ele = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.tagName(arrLocator[1])));
				break;
			case "CLASSNAME":

				ele = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.className(arrLocator[1])));
				break;
			case "NAME":

				ele = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.name(arrLocator[1])));
				break;
			default:
				log.error(" Unknown locator type :"+arrLocator[0]);
				throw new Exception("Unknown locator type :"+arrLocator[0]);
			}
			
			
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
			log.error(" Unknown locator type :"+arrLocator[0]);
			ExceptionHandler.addVerificationFailure(e);
			
		}
		return ele;
		
	}
	/**
	 * this method will take String parameter which is the value from
	 * ObjectRepository something like this- ID:=email
	 * 
	 * @return it returns WebElement
	 * @param sLocatorValue
	 */

	public WebElement getElementByClickable(String sLocatorValue) {
		String arrLocator[] = null;
		WebElement ele = null;
		try {

			arrLocator = sLocatorValue.split(":=");// ID:=signin_userName

			if (arrLocator.length != 2) {
				throw new Exception("Locator value is not correct");
			}

			// now arrLocator[0] contains value for switch case
			// and arrLocator[1] contains the value for web element
			
			switch (arrLocator[0].toUpperCase().trim())// arrLocator[0] -> 0th index
													// will be having
													// locatorType from
													// arrLocator
			{
			case "XPATH":

				ele = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(arrLocator[1])));

				break;
			case "ID":
				ele = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id(arrLocator[1])));
				break;
			case "LINKTEXT":
				ele = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.linkText(arrLocator[1])));
				break;
			case "PARTIALLINKTEXT":

				ele = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.partialLinkText(arrLocator[1])));
				break;
			case "TAGNAME":

				ele = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.tagName(arrLocator[1])));
				break;
			case "CLASSNAME":

				ele = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.className(arrLocator[1])));
				break;
			case "NAME":

				ele = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.name(arrLocator[1])));
				break;
			default:
				log.error(" Unknown locator type :"+arrLocator[0]);
				throw new Exception("Unknown locator type :"+arrLocator[0]);
			}
			
			
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
			log.error(" Unknown locator type :"+arrLocator[0]);
			ExceptionHandler.addVerificationFailure(e);
			
		}
		return ele;
		
	}
	/**
	 * This method will be select the provided value from drop down
	 * @param eleDropDown - drop down web element object
	 * @param sValueToBeselect
	 */
	public void selectDropDown(WebElement eleDropDown, String sValueToBeselect)
	{
		
		try {
			
			Select select = new Select(eleDropDown);
			select.selectByVisibleText(sValueToBeselect);
			
		} 
		catch (Exception e) 
		{
			log.error("Failed to select the value from drop down"+ sValueToBeselect);
			ExceptionHandler.addVerificationFailure(e);
		}
		
	}

	public boolean isValueSelectedFromDropDown(WebElement dropDown, String sValue)
	{
		boolean bSelected=false;
		try {
			
			Select select = new Select(dropDown);
			String sSelectedValue=select.getFirstSelectedOption().getText();
			
			if(sSelectedValue.equals(sValue))
			{
				bSelected= true;
			}
			
		} 
		catch (Exception e) 
		{
			log.error("Failed to select the value from drop down"+ sValue);
			ExceptionHandler.addVerificationFailure(e);
		}
		return bSelected;
		
	}
	public List<WebElement> getChildElements(WebElement eleRoot,String sChildElementLocator)
	{
		
		
		String arrLocator[] = null;
		List<WebElement> eleList = null;
		try {

			arrLocator = sChildElementLocator.split(":=");// ID:=signin_userName

			if (arrLocator.length != 2) {
				throw new Exception("Locator value is not correct");
			}

			// now arrLocator[0] contains value for switch case
			// and arrLocator[1] contains the value for web element
			
			switch (arrLocator[0].toUpperCase().trim())
			{
			case "XPATH":

				eleList = eleRoot.findElements(By.xpath(arrLocator[1]));

				break;
			case "ID":
				eleList = eleRoot.findElements(By.id(arrLocator[1]));
				break;
			case "LINKTEXT":
				eleList = eleRoot.findElements(By.linkText(arrLocator[1]));
				break;
			case "PARTIALLINKTEXT":

				eleList = eleRoot.findElements(By.partialLinkText(arrLocator[1]));
				break;
			case "TAGNAME":
				eleList = eleRoot.findElements(By.tagName(arrLocator[1]));

				break;
			case "CLASSNAME":
				eleList = eleRoot.findElements(By.className(arrLocator[1]));

				break;
			case "NAME":
				eleList = eleRoot.findElements(By.name(arrLocator[1]));

				break;
			default:
				log.error(" Unknown locator type :"+arrLocator[0]);
				throw new Exception("Unknown locator type :"+arrLocator[0]);
			}
			
			
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
			log.error(" Unknown locator type :"+arrLocator[0]);
			ExceptionHandler.addVerificationFailure(e);
			
		}
		return eleList;

		
	}
	/**
	 * Get child element from root element
	 * @param eleRoot
	 * @param sChildElementLocator
	 * @return
	 */
	public WebElement getChildElement(WebElement eleRoot,String sChildElementLocator)
	{
		
		
		String arrLocator[] = null;
		WebElement eleList = null;
		try {

			arrLocator = sChildElementLocator.split(":=");// ID:=signin_userName

			if (arrLocator.length != 2) {
				throw new Exception("Locator value is not correct");
			}

			// now arrLocator[0] contains value for switch case
			// and arrLocator[1] contains the value for web element
			
			switch (arrLocator[0].toUpperCase().trim())
			{
			case "XPATH":

				eleList = eleRoot.findElement(By.xpath(arrLocator[1]));

				break;
			case "ID":
				eleList = eleRoot.findElement(By.id(arrLocator[1]));
				break;
			case "LINKTEXT":
				eleList = eleRoot.findElement(By.linkText(arrLocator[1]));
				break;
			case "PARTIALLINKTEXT":

				eleList = eleRoot.findElement(By.partialLinkText(arrLocator[1]));
				break;
			case "TAGNAME":
				eleList = eleRoot.findElement(By.tagName(arrLocator[1]));

				break;
			case "CLASSNAME":
				eleList = eleRoot.findElement(By.className(arrLocator[1]));

				break;
			case "NAME":
				eleList = eleRoot.findElement(By.name(arrLocator[1]));

				break;
			default:
				log.error(" Unknown locator type :"+arrLocator[0]);
				throw new Exception("Unknown locator type :"+arrLocator[0]);
			}
			
			
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
			log.error(" Unknown locator type :"+arrLocator[0]);
			ExceptionHandler.addVerificationFailure(e);
			
		}
		return eleList;

		
	}
	/**
	 * 
	 * @param sLocator - Locator Value
	 */
	public void doubleClick(String sLocator)
	{
		WebElement ele=getElement(sLocator);
		ele.click();
		
	}
	/**
	 * 
	 * @param sLocator - Locator Value
	 */
	public void click(String sLocator)
	{
		WebElement ele=getElement(sLocator);
		ele.click();
		
	}
	/**
	 * 
	 * @param sLocator - Locator Value
	 */
	public void clickOnElement(String sLocator)
	{
		WebElement ele=getPresentElement(sLocator);
		ele.click();
		
	}
	/**
	 * 
	 * @return
	 */
	public boolean enterText(String sLocator,String sValueToBeEntered)
	{
		WebElement ele=getElement(sLocator);
		// clear the text 
		ele.clear();
		// send/enter the new text/ value
		ele.sendKeys(sValueToBeEntered);
		return true;
	}

	public boolean selectCheckBox(String sLocator) 
	{
		 WebElement ele=getElementByClickable(sLocator);
		 if(!ele.isSelected())
		 {
			ele.click();
		 }
		 
		return ele.isSelected();
		
		
	}
	// this  will be used to switch any newly opened window
	public  void switchToWindow(String currentWindowId)
	{
		Set<String>lstWindows=getWebDriver().getWindowHandles();// it will return  unique window id for all opened windows
		 
		for(String window:lstWindows)
		{
		  if(!currentWindowId.equals(window))
		  {
			  getWebDriver().switchTo().window(window);
			  break;
		  }
		}
	}

	/**
	 * this method will take String parameter which is the value from
	 * ObjectRepository something like this- ID:=email
	 * 
	 * @return it returns list of  WebElement
	 * @param sLocatorValue
	 */

	public List<WebElement> getElements(String sLocatorValue) {
		String arrLocator[] = null;
		List<WebElement >ele = null;
		try {

			arrLocator = sLocatorValue.split(":=");// ID:=signin_userName

			if (arrLocator.length != 2) {
				throw new Exception("Locator value is not correct");
			}

			// now arrLocator[0] contains value for switch case
			// and arrLocator[1] contains the value for web element
			
			switch (arrLocator[0].toUpperCase().trim())// arrLocator[0] -> 0th index
													// will be having
													// locatorType from
													// arrLocator
			{
			case "XPATH":

				ele = getWebDriver().findElements((By.xpath(arrLocator[1])));

				break;
			case "ID":
				ele = getWebDriver().findElements((By.id(arrLocator[1])));
				break;
			case "LINKTEXT":
				ele = getWebDriver().findElements((By.linkText(arrLocator[1])));
				break;
			case "PARTIALLINKTEXT":

				ele = getWebDriver().findElements((By.partialLinkText(arrLocator[1])));
				break;
			case "TAGNAME":

				ele = getWebDriver().findElements((By.tagName(arrLocator[1])));
				break;
			case "CLASSNAME":

				ele = getWebDriver().findElements((By.className(arrLocator[1])));
				break;
			case "NAME":

				ele = getWebDriver().findElements((By.name(arrLocator[1])));
				break;
			default:
				log.error(" Unknown locator type :"+arrLocator[0]);
				throw new Exception("Unknown locator type :"+arrLocator[0]);
			}
			
			
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
			log.error(" Unknown locator type :"+arrLocator[0]);
			ExceptionHandler.addVerificationFailure(e);
			
		}
		return ele;
		
	}

	public void closeAlert() 
	{
		try
		{
			getWebDriverWait().until(ExpectedConditions.alertIsPresent()).accept();
		}
		catch(Exception e)
		{
			
		}
	}

	public void movetToElementAndClick(String string) 
	{
		WebElement ele=getElement(string);
		int x=ele.getLocation().x;
		int y=ele.getLocation().y;
		Actions action= new Actions(getWebDriver());
		
		action.moveToElement(ele,x+5,y+5).clickAndHold(ele).release().click(ele).build().perform();
	}
	
	
	
	
	/**
	 * 
	 */
	public void takeScreenshot(String sFilename)
	{
	
		try {
			File srcFile=((TakesScreenshot)getWebDriver()).getScreenshotAs(OutputType.FILE);
			SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddhhmmss");
			String fileName=sFilename+"_"+sdf.format(new Date());
			FileUtils.copyFile(srcFile, new File(config.getScreenShotsPath()+fileName+".png"));
			
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExceptionHandler.addVerificationFailure(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExceptionHandler.addVerificationFailure(e);
		}
	}
	
	public WebElement getPresentElement(String sLocatorValue) {
		String arrLocator[] = null;
		WebElement ele = null;
		try {

			arrLocator = sLocatorValue.split(":=");// ID:=signin_userName

			if (arrLocator.length != 2) {
				throw new Exception("Locator value is not correct");
			}

			// now arrLocator[0] contains value for switch case
			// and arrLocator[1] contains the value for web element
			
			switch (arrLocator[0].toUpperCase().trim())// arrLocator[0] -> 0th index
													// will be having
													// locatorType from
													// arrLocator
			{
			case "XPATH":

				ele = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(arrLocator[1])));

				break;
			case "ID":
				ele = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.id(arrLocator[1])));
				break;
			case "LINKTEXT":
				ele = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.linkText(arrLocator[1])));
				break;
			case "PARTIALLINKTEXT":

				ele = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(arrLocator[1])));
				break;
			case "TAGNAME":

				ele = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.tagName(arrLocator[1])));
				break;
			case "CLASSNAME":

				ele = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.className(arrLocator[1])));
				break;
			case "NAME":

				ele = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.name(arrLocator[1])));
				break;
			default:
				log.error(" Unknown locator type :"+arrLocator[0]);
				throw new Exception("Unknown locator type :"+arrLocator[0]);
			}
			
			
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
			log.error(" Unknown locator type :"+arrLocator[0]);
			ExceptionHandler.addVerificationFailure(e);
			
		}
		return ele;
		
	}
}
