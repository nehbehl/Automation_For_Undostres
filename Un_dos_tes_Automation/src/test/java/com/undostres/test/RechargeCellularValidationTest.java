package com.undostres.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.undostres.pages.LaunchApplicationPage;
import com.undostres.pages.PaymentPage;
import com.undostres.pages.RechargeCellularPage;
import com.undostres.utilities.BrowserUtilities;
import com.undostres.utilities.JavaUtilities;
import com.undostres.utilities.TestListeners;

@Listeners(TestListeners.class)
public class RechargeCellularValidationTest {

	private WebDriver driver;
	LaunchApplicationPage launchAppPage;
	RechargeCellularPage rechargePage;
	PaymentPage paymentPage;
	Properties prop;
	
	@BeforeClass
	public void beforeClass() {
		driver = BrowserUtilities.launchBrowser(driver);
		launchAppPage = new LaunchApplicationPage(driver);
		rechargePage = new RechargeCellularPage(driver);
		paymentPage = new PaymentPage(driver);
		prop = JavaUtilities.loadPropertiesFile("config.properties");
	}
	
	@Test
	public void launch_UnDosTres_WebApplication() {
		launchAppPage.launchApplication(prop.getProperty("applicationURL"));
	}	
	
	@Test(dependsOnMethods = { "launch_UnDosTres_WebApplication" })
	public void selectOperatorOnRechargeCellular() {
		rechargePage.clickOperator();
		rechargePage.selectOperator("Telcel");
	}
	
	@Test(dependsOnMethods = { "selectOperatorOnRechargeCellular" })
	public void enterMobileNumber() {
		rechargePage.enterMobileNumber("5523261151");
	}
	
	@Test(dependsOnMethods = { "enterMobileNumber" })
	public void selectRechargeAmount() {
		rechargePage.clickRechargeAmountTextBox();
		rechargePage.selectRechargeAmount("$10");
	}
	
	@Test(dependsOnMethods = { "selectRechargeAmount" })
	public void clickSiguienteButton() {
		rechargePage.clickSiguienteButton();
	}
	
	@Test(dependsOnMethods = { "clickSiguienteButton" })
	public void validatePaymentPage() {
		paymentPage.validatePaymentPageDisplay("5523261151");		
	}
	
	@Test(dependsOnMethods = { "validatePaymentPage" })
	public void enterPaymentDetails() {
		paymentPage.clickCardOption();
		paymentPage.enterPaymentData("Test", "4111111111111111", "11", "2025", "111", "test@test.com");
		paymentPage.clickPaymentButton();
	}
	
	@Test(dependsOnMethods = { "enterPaymentDetails" })
	public void submitDataOnPopUp() {
		paymentPage.submitDataOnPopUp("marze.zr@gmail.com", "123456");
		paymentPage.validateRechargeStatus();
	}

	@AfterClass
	public void afterClass() {
		driver.close();
		driver.quit();
	}
}