package com.undostres.utilities;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

/*
 * Description: This class holds code related to interaction with elements on the web application 
 */

@Listeners(TestListeners.class)
public class ObjectHandler {
	public static LocatorReader appLocators;
	WebDriver driver;
	

	public ObjectHandler(WebDriver driver) {
		this.driver = driver;		
	}

	public static By byLocator(String locator) {
		By result = null;

		if (locator.startsWith("//")) {
			result = By.xpath(locator);
		} else if (locator.startsWith("css=")) {
			result = By.cssSelector(locator.replace("css=", ""));
		} else if (locator.startsWith("class=")) {
			result = By.className(locator.replace("class=", ""));
		} else if (locator.startsWith("tag=")) {
			result = By.tagName(locator.replace("tag=", ""));
		} else if (locator.startsWith("#")) {
			result = By.name(locator.replace("#", ""));
		} else if (locator.startsWith("plt=")) {
			result = By.name(locator.replace("plt=", ""));
		} else if (locator.startsWith("lt=")) {
			result = By.name(locator.replace("lt=", ""));
		} else if (locator.startsWith("(")) {
			result = By.xpath(locator);
		} else {
			result = By.id(locator);
		}

		return result;
	}

	public String createDynamicQueryXpath(String string, String toBeReplaceKey,
			String replacedValed) {
		String result = "";
		if (string.contains(toBeReplaceKey)) {
			result = string.replace(toBeReplaceKey, replacedValed);
		}
		return result;
	}

	public WebElement getWebElementOfLocator(String key) {
		String locator = appLocators.getLocator(key);
		return (driver.findElement(byLocator(locator)));
	}

	public List<WebElement> getWebElementsOfLocator(String key) {
		String locator = appLocators.getLocator(key);
		return (driver.findElements(byLocator(locator)));
	}
}