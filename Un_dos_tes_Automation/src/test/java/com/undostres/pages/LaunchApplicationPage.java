package com.undostres.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import com.undostres.utilities.TestListeners;

@Listeners(TestListeners.class)
public class LaunchApplicationPage {
	static WebDriver driver;
	static Logger logger = Logger.getLogger(LaunchApplicationPage.class);
	
	public LaunchApplicationPage(WebDriver driver) {
		LaunchApplicationPage.driver = driver;
	}

	public void launchApplication(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			logger.error(
					"Something went wrong when trying to launch Cloud BI application, Stack Trace: "
							+ e);
			TestListeners.extentLogger.fail(
					"Something went wrong when trying to launch Cloud BI application, Stack Trace: "
							+ e);
		}
	}
}