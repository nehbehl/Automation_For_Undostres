package com.undostres.utilities;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;

/*
 * Desc: This utility caters to code for multiple browser handling including Chrome, FF and IE
 */

public class BrowserUtilities {

	WebDriver driver;
	static Logger logger = Logger.getLogger(BrowserUtilities.class);
	static Properties configProperties;

	public void launch_Browser(WebDriver driver) {
		this.driver = driver;
	}

	@BeforeSuite
	public void logReport() throws Exception {
		logger = Logger.getLogger("AutomationLog");
		Appender fh = null;
		try {
			fh = new FileAppender(new SimpleLayout(), "un_dos_tres_AutomationLogFile.log");
			logger.addAppender(fh);
			fh.setLayout(new SimpleLayout());
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public static WebDriver launchBrowser(WebDriver driver) {
		String browserName = "";
		logger.info("------------------------------------------");
		logger.info("Launching the browser ................... ");
		configProperties = JavaUtilities.loadPropertiesFile("config.properties");
		browserName = System.getProperty("browser");
		if (System.getProperty("browser") == null || System.getProperty("browser").equalsIgnoreCase("")) {
			browserName = configProperties.getProperty("browserName");
		}
		if (browserName.toUpperCase().trim().equalsIgnoreCase("FIREFOX")) {
			String path = System.getProperty("webdriver.gecko.driver");
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setAcceptUntrustedCertificates(true);
			firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
			FirefoxOptions options = new FirefoxOptions();
			options.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
			if (path != null) {
				System.setProperty("webdriver.gecko.driver", path);
			} else {
				System.setProperty("webdriver.gecko.driver", ".\\BrowserDrivers\\geckodriver.exe");
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			}
			driver = new FirefoxDriver(options);
			driver.manage().window().maximize();
			logger.info("Browser Name: " + browserName);
		} else if (browserName.toUpperCase().trim().equalsIgnoreCase("CHROME")) {
			String path = System.getProperty("webdriver.chrome.driver");
			if (path != null) {
				System.setProperty("webdriver.chrome.driver", path);
			} else {
				System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
			}
			driver = new ChromeDriver();
			logger.info("Browser Name: " + browserName);
		} else if (browserName.toUpperCase().trim().equalsIgnoreCase("IE")) {
			try {
				Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.setProperty("webdriver.ie.driver", ".\\BrowserDrivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().deleteAllCookies();

		}

		else {
			logger.info("Driver does not exist");
			driver = null;
		}
		if (driver != null) {
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();

			String bv = cap.getVersion().toString();
			logger.info("Browser Version: " + bv);
			logger.info("OS Name: " + System.getProperty("os.name"));
			logger.info("OS Version: " + System.getProperty("os.version"));
			logger.info("Browser launched successfully.");
		}
		return driver;
	}
}