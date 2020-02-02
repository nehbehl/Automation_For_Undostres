package com.undostres.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

/*
 *@Desc: This class contains code related to Extent for the automation execution report purpose
 *
 */


public class ExtentReportManager {

	public static ExtentReports extent;
	public static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	public static String reportPathNValue;
	public static String folderPathValue;
	static int flag = 0;
	
	public static ExtentReports getExtent() {
		if (extent != null)
			return extent;
		extent = new ExtentReports();
		extent.attachReporter(getHtmlReporter());
		return extent;
	}

	private static ExtentHtmlReporter getHtmlReporter() {
		folderPathValue = createDateTimeFolder();
		reportPathNValue = folderPathValue;
		htmlReporter = new ExtentHtmlReporter(reportPathNValue + "/Undostres_Report.html");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Un dos tres Automation Report");
		htmlReporter.config().setReportName("Basic Flow");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);

		return htmlReporter;
	}
	
	public static String createDateTimeFolder() {
		if (flag == 0) {
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH-mm-ss");
			String time = dateFormat.format(now);
			time = "Undostres Automation Report\\" + time + "\\Report";
			File dir = new File(time);
			dir.mkdirs();
			flag = 1;
			return time;
		} else
			return "";
	}	

	public static ExtentTest createTest(String name, String description) {
		test = extent.createTest(name, description);
		return test;
	}
}