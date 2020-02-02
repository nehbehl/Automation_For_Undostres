package com.undostres.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/*
 *@Desc: This class holds code specific to start up activities
 */

public class TestListeners implements ITestListener {
	public static ExtentReports extentReport;
	public static ExtentTest extentLogger;

	public void onTestStart(ITestResult result) {
		extentLogger = extentReport.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		extentReport = ExtentReportManager.getExtent();
		ObjectHandler.appLocators = new LocatorReader("ApplicationLocators.xml");
	}

	public void onFinish(ITestContext context) {
		extentReport.flush();
	}

}
