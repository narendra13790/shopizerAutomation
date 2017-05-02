package shopizer.PageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import shopizer.businessFunctions.AppFunctions;
import shopizer.seleniumCommonFunctions.Config;
import shopizer.seleniumCommonFunctions.SeleniumCommon;
import shopizer.utility.ReadPropertyFile;

public class BusinessBooksPage {

	SeleniumCommon selenium;
	AppFunctions app;
	Config config = Config.getInstance();

	ReadPropertyFile readProps;
	TreeMap<String, String> compBooksLocators;// map to store locator for
												// Computer books object
												// repository

	Logger log = Logger.getLogger(ComputerBooksPage.class);

	public BusinessBooksPage(SeleniumCommon selenium) throws InterruptedException {
		this.selenium = selenium;
		app = new AppFunctions(this.selenium);

		readProps = new ReadPropertyFile();
		compBooksLocators = readProps.getLocatorMap(config.getComputerBooksObjectFile());
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
			log.error("Failed to select menu : " + sMenuName);
			return false;
		}

	}

	public boolean selectDropDownValue(String sDropDownName, String sValue) {

		try {
			selenium.selectDropDown(app.getDropDown(sDropDownName), sValue);
			// verify that value is selected
			return selenium.isValueSelectedFromDropDown(app.getDropDown(sDropDownName), sValue);

		} catch (Exception e) {

			log.error(" Failed to select the value [" + sValue + "] from drop down:" + sDropDownName);
			return false;
		}

	}

	public boolean enterMinMaxPrice(String sMinPriceValue, String sMaxPriceValue) {

		try {

			selenium.getElement(compBooksLocators.get("cpBooksPage.priceFilterMinimum")).sendKeys(sMinPriceValue);
			selenium.getElement(compBooksLocators.get("cpBooksPage.priceFilterMaximum")).sendKeys(sMaxPriceValue);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public boolean verifySortedResults(String sFilterType, Double dblMinPrice, Double dblMaxPrice) {

		WebElement productContainer = selenium.getElement(compBooksLocators.get("cpBooksPage.ProductListContainer"));
		boolean bSorted = false;
		switch (sFilterType.toUpperCase()) {

		case "BY_NAME":

			bSorted = verifySorting(
					selenium.getChildElements(productContainer, compBooksLocators.get("cpBooksPage.ItemNames")),
					sFilterType, null, null);

			break;
		case "BY_PRICE":
			bSorted = verifySorting(
					selenium.getChildElements(productContainer, compBooksLocators.get("cpBooksPage.ItemPrices")),
					sFilterType, null, null);

			break;
		case "BY_MIN_MAX_PRICE":

			bSorted = verifySorting(
					selenium.getChildElements(productContainer, compBooksLocators.get("cpBooksPage.ItemPrices")),
					sFilterType, dblMinPrice, dblMaxPrice);

			break;

		default:
			log.error("Unknown value for filter .");

		}
		return bSorted;

	}

	/**
	 * 
	 * @param itemList
	 */
	public boolean verifySorting(List<WebElement> itemList, String sSortingBy, Double dblMinValue, Double dblMaxValue) {
		boolean bSorted = true;
		// get all item list in another list
		List<String> itemNames = new ArrayList<String>();
		for (WebElement ele : itemList) {

			itemNames.add(ele.getText());

			System.out.println("---" + ele.getText());

		}
		// now verify list is sorted or not?
		for (int i = 1; i < itemNames.size(); i++) {
			if (sSortingBy.equalsIgnoreCase("By_Name")) {
				// if the first item has high order then second one then sorting
				// is failed
				if (itemNames.get(i - 1).charAt(0) > itemNames.get(i).charAt(0)) {
					bSorted = false;
					break;
				}
			} else if (sSortingBy.equalsIgnoreCase("By_Price")) {
				// Example :CAD18.99
				double dblPrice1 = Double.parseDouble(itemNames.get(i - 1).replaceAll("CAD", ""));// first
																									// item
																									// price
				double dblPrice2 = Double.parseDouble(itemNames.get(i).replaceAll("CAD", ""));// 2nd
																								// item
																								// price

				if (dblPrice1 > dblPrice2) {
					bSorted = false;
					break;
				}
			} else if (sSortingBy.equalsIgnoreCase("BY_MIN_MAX_PRICE")) {
				double dblPrice = Double.parseDouble(itemNames.get(i).replaceAll("CAD", ""));

				if (!((dblMinValue <= dblPrice) || (dblPrice <= dblMaxValue))) {
					bSorted = false;
				}

			} else {
				log.error("Sorting parameter is not matched.");
				bSorted = false;
			}
		}
		return bSorted;
	}

	public void selectItem(String sItemName) {

		WebElement productContainer = selenium.getElement(compBooksLocators.get("cpBooksPage.ProductListContainer"));
		List<WebElement> lstItems = selenium.getChildElements(productContainer,
				compBooksLocators.get("cpBooksPage.ItemNames"));

		for (WebElement ele : lstItems) {
			if (ele.getText().contains(sItemName)) {
				// if item found then click on item
				selenium.getChildElement(productContainer,
						compBooksLocators.get("cpBooksPage.ItemImage").replace("#itemName#", sItemName)).click();
				;
				break;
			}
		}

	}

}
