package com.undostres.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.undostres.utilities.ObjectHandler;
import com.undostres.utilities.TestListeners;

public class PaymentPage {
	static WebDriver driver;
	static Logger logger = Logger.getLogger(PaymentPage.class);
	ObjectHandler objHandle;

	public PaymentPage(WebDriver driver) {
		PaymentPage.driver = driver;
		objHandle = new ObjectHandler(driver);
	}

	public void validatePaymentPageDisplay(String customerNumber) {
		try {
			String paymentMessageLocator = objHandle.createDynamicQueryXpath(
					ObjectHandler.appLocators.getLocator("PaymentPage.messageLabel"), "customerNumber", customerNumber);
			if (driver.findElement(By.xpath(paymentMessageLocator)).isDisplayed()) {
				logger.info("Payment page successfully displayed");
				TestListeners.extentLogger.pass("Payment page successfully displayed");
			} else {
				logger.warn("Payment page not displayed");
				TestListeners.extentLogger.error("Payment page not displayed");
			}
		} catch (Exception e) {
			logger.error("Something went wrong when validating display of Payment page, Stack Trace: " + e);
			TestListeners.extentLogger
					.fail("Something went wrong when validating display of Payment page, Stack Trace: " + e);
		}
	}

	public void enterPaymentData(String cardName, String cardNumber, String expMonth, String expYear, String cvv,
			String emailId) {
		try {
			objHandle.getWebElementOfLocator("PaymentPage.cardNameTextBox").sendKeys(cardName);
			objHandle.getWebElementOfLocator("PaymentPage.cardNumberTextBox").sendKeys(cardNumber);
			objHandle.getWebElementOfLocator("PaymentPage.expMonthTextBox").sendKeys(expMonth);
			objHandle.getWebElementOfLocator("PaymentPage.expYearTextBox").sendKeys(expYear);
			objHandle.getWebElementOfLocator("PaymentPage.cvvTextBox").sendKeys(cvv);
			objHandle.getWebElementOfLocator("PaymentPage.emailTextBox").sendKeys(emailId);
			logger.info("Successfully entered payment details");
			TestListeners.extentLogger.pass("Successfully entered payment details");

		} catch (Exception e) {
			logger.error("Something went wrong when entering payment data, Stack Trace: " + e);
			TestListeners.extentLogger.fail("Something went wrong when entering payment data, Stack Trace: " + e);
		}
	}

	public void clickCardOption() {
		objHandle.getWebElementOfLocator("PaymentPage.selectCardLabel");
	}

	public void clickPaymentButton() {
		try {
			objHandle.getWebElementOfLocator("PaymentPage.paymentButton").click();
			logger.info("Clicked Payment button");
			TestListeners.extentLogger.pass("Clicked Payment button");
		} catch (Exception e) {
			logger.error("Something went wrong when clicking Payment button, Stack Trace: " + e);
			TestListeners.extentLogger.fail("Something went wrong when clicking Payment button, Stack Trace: " + e);
		}
	}

	public void submitDataOnPopUp(String userName, String pwd) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(objHandle.getWebElementOfLocator("PaymentPage.userNameTextBox")));
			
			objHandle.getWebElementOfLocator("PaymentPage.userNameTextBox").sendKeys(userName);
			objHandle.getWebElementOfLocator("PaymentPage.pwdTextBox").sendKeys(pwd);
			driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='g-recaptcha']//iframe")));
			objHandle.getWebElementOfLocator("PaymentPage.captchaCheckBox").click();
			driver.switchTo().defaultContent();

			wait.until(ExpectedConditions.elementToBeClickable(objHandle.getWebElementOfLocator("PaymentPage.loginButton")));
			
			objHandle.getWebElementOfLocator("PaymentPage.loginButton").click();
			logger.info("Submitted pop-up data");
			TestListeners.extentLogger.pass("Submitted pop-up data");			
		} catch (Exception e) {
			logger.error("Something went wrong when entering data on pop-up, Stack Trace: " + e);
			TestListeners.extentLogger.fail("Something went wrong when entering data on pop-up, Stack Trace: " + e);
		}
	}
	
	public void validateRechargeStatus() {
		try {
			if(objHandle.getWebElementOfLocator("PaymentPage.errorLabel").isDisplayed()) {
				logger.warn("Recharge has not been successful");
				TestListeners.extentLogger.error("Recharge has not been successful");			
			} else {
				logger.info("Recharge was successful");
				TestListeners.extentLogger.pass("Recharge was successful");				
			}
		} catch(Exception e) {
			logger.error("Something went wrong when validating payment status, Stack Trace: " + e);
			TestListeners.extentLogger.fail("Something went wrong when validating payment status, Stack Trace: " + e);				
		}
	}
}