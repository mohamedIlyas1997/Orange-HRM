package com.hrm.utilities;


//Listener class used to generate Extent reports

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager extends TestListeners implements ITestListener
{
	public WebDriver driver;


	private static ExtentReports extent;
	
	
	public static ExtentReports createInstance() {
		
		String fileName= getReportName();
		String directory =System.getProperty("user.dir") + "/reports/";
		new File(directory).mkdirs();
		String path= directory + fileName;
		
		ExtentHtmlReporter htmlReporter= new ExtentHtmlReporter(path);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("OS","10");
		extent.setSystemInfo("Java Version","1.8");
		extent.setSystemInfo("user","Mohamed Ilyas");
		extent.attachReporter(htmlReporter);
		
		return extent;
		
	}
	
	
	public static String getReportName() {
//		Date d =new Date();
//		String fileName= "Test Report_" + "_" + d.toString().replace(":", "_").replace(" ", "-") + ".html";
//		return fileName;
		
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
		return repName;
		
		
		
		

	}
	



}

