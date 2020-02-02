package com.undostres.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.undostres.utilities.ObjectHandler;
import com.undostres.utilities.TestListeners;

public class RechargeCellularPage {
	static WebDriver driver;
	static Logger logger = Logger.getLogger(RechargeCellularPage.class);
	ObjectHandler objHandle;
	
	public RechargeCellularPage(WebDriver driver) {
		RechargeCellularPage.driver = driver;
		objHandle = new ObjectHandler(driver);
	}	
	
	public void clickOperator() {
		try {
			objHandle.getWebElementOfLocator("RechargeCellularPage.operatorTextBox").click();
			logger.info("Successfully clicked on Operator text box");
			TestListeners.extentLogger.pass("Successfully clicked on Operator text box");
		} catch (Exception e) {
			logger.error("Something went wrong when trying to click Operator, Stack Trace: " + e);
			TestListeners.extentLogger.fail("Something went wrong when trying to click Operator, Stack Trace: " + e);
		}
	}

	public void selectOperator(String operatorName) {
		try{
			String operatorLocator = objHandle.createDynamicQueryXpath(
					ObjectHandler.appLocators.getLocator("RechargeCellularPage.operatorSelectionDropdown"),
					"operatorName", operatorName);
			driver.findElement(By.xpath(operatorLocator)).click();			
			logger.info("Successfully selected operator : " + operatorName);
			TestListeners.extentLogger.pass("Successfully selected operator : " + operatorName);
		} catch(Exception e) {
			logger.error("Something went wrong when trying to select Operator, Stack Trace: " + e);
			TestListeners.extentLogger.fail("Something went wrong when trying to select Operator, Stack Trace: " + e);			
		}
	}
	
	public void enterMobileNumber(String mobileNumber) {
		try {
			objHandle.getWebElementOfLocator("RechargeCellularPage.mobileNumberTextBox").sendKeys(mobileNumber);
			logger.info("Successfully entered mobile number : " + mobileNumber);
			TestListeners.extentLogger.pass("Successfully entered mobile number : " + mobileNumber);			
		} catch(Exception e) {
			logger.error("Something went wrong when trying to enter mobile number, Stack Trace: " + e);
			TestListeners.extentLogger.fail("Something went wrong when trying to enter mobile number, Stack Trace: " + e);			
		}
	}
	
	public void clickRechargeAmountTextBox() {
		try {
			objHandle.getWebElementOfLocator("RechargeCellularPage.rechargeAmountTextBox").click();
			logger.info("Successfully clicked on recharge amount text box");
			TestListeners.extentLogger.pass("Successfully clicked on recharge amount text box");
		} catch (Exception e) {
			logger.error("Something went wrong when trying to click recharge amount text box, Stack Trace: " + e);
			TestListeners.extentLogger.fail("Something went wrong when trying to click recharge amount text box, Stack Trace: " + e);
		}		
	}
	
	public void selectRechargeAmount(String rechargeAmount) {
		try{
			String amountLocator = objHandle.createDynamicQueryXpath(
					ObjectHandler.appLocators.getLocator("RechargeCellularPage.rechargeAmountDropdown"),
					"dollarAmount", rechargeAmount);
			driver.findElement(By.xpath(amountLocator)).click();			
			logger.info("Successfully selected recharge amount : " + rechargeAmount);
			TestListeners.extentLogger.pass("Successfully selected recharge amount : " + rechargeAmount);			
		} catch(Exception e) {
			logger.error("Something went wrong when trying to select recharge amount, Stack Trace: " + e);
			TestListeners.extentLogger.fail("Something went wrong when trying to select recharge amount, Stack Trace: " + e);				
		}		
	}
	
	public void clickSiguienteButton() {
		try {
			objHandle.getWebElementOfLocator("RechargeCellularPage.siguienteButton").click();
			logger.info("Successfully clicked Siguiente button");
			TestListeners.extentLogger.pass("Successfully clicked Siguiente button");			
		} catch(Exception e) {
			logger.error("Something went wrong when trying to click Siguiente button, Stack Trace: " + e);
			TestListeners.extentLogger.fail("Something went wrong when trying to click Siguiente button, Stack Trace: " + e);			
		}
	}
}