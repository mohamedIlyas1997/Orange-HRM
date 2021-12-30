package com.hrm.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.hrm.base.Baseclass;


public class TestListeners extends Baseclass implements ITestListener {

	//	public ExtentTest test;

	public static ExtentReports extent= ExtentManager.createInstance();
	public static ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
	public static ExtentTest test;
	public static String testcaseName, testDescription,category;

	public void onTestStart(ITestResult result) {

		//ExtentTest test= extent.createTest(result.getTestClass().getName() +  " :: " + result.getMethod().getMethodName());	
		ExtentTest test= extent.createTest(result.getMethod().getMethodName());		
		extentTest.set(test);

	}


	public void onTestSuccess(ITestResult result) {

		try {

			String path = TakeScreenshot(Baseclass.driver);
			extentTest.get().pass("<b><font color =green>" + "Screenshot of Passed" + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		catch (IOException exp) {
			exp.printStackTrace();
			extentTest.get().fail("Test failed, cannot attach screenshot");
		}

		String logText="<b>Test Method : " +  result.getMethod().getMethodName() + " Successful</b>";
		//String logText= result.getMethod().getMethodName() + " Successful";
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m); //get() access from thread local

	}


	public void onTestFailure(ITestResult result)  {

		String methodName= result.getMethod().getMethodName();
		String exceptiomessage=Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color=red>Exception Occurred, Click to See Details:"
		+ "</font></b></summary>" + exceptiomessage.replaceAll(",", "<br>") + "</details> \n");

	//	extentTest.get().fail(result.getThrowable());		

		try {
			//	String path = TakeScreenshot(Baseclass.driver, result.getMethod().getMethodName());
			String path = TakeScreenshot(Baseclass.driver);
			extentTest.get().fail("<b><font color =red>" + "Screenshot of Failure" + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		catch (IOException exp) {
			exp.printStackTrace();
			extentTest.get().fail("Test failed, cannot attach screenshot");
		}

		String logText="<b>Test Method " + methodName + " Failed</b>";
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);

	}	

	public void onTestSkipped(ITestResult result) {
		String logText="<b>Test Method " + result.getMethod().getMethodName() + " Skipped</b>";
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m); //get() access from thread local

	}


	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {


	}


	public void onStart(ITestContext context) {


	}


	public void onFinish(ITestContext context) {
		if (extent !=null) {
			extent.flush();		
		}

	}



	public static String getscreenshotName(String methodName) {
		Date d =new Date();
		String fileName= methodName + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
		return fileName;

	}
	



	public static String TakeScreenshot(WebDriver driver) throws IOException {
		String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@BeforeMethod
	public static void testcasename(String testcasename, String testdecsrip) {
		test=extent.createTest(testcasename, testdecsrip);

	}

	


}

