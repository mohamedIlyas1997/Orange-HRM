package com.hrm.base;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.hrm.utilities.TestListeners;


public class Keywords extends TestListeners  {


	public void click(WebDriver driver, String path) throws IOException, InterruptedException  { 
		try {
			WebElement webElement = driver.findElement(By.xpath(path));
			webElement.click();
			extentTest.get().log(Status.PASS, path + " is clicked Succesfully" , MediaEntityBuilder.
					createScreenCaptureFromPath((TakeScreenshot(Baseclass.driver))).build());		
		} catch (Exception e) {
			extentTest.get().log(Status.FAIL, path + " is not able to click");		
		}
	}

	public String sendKeys(WebDriver driver, String path, String keysToSend) throws IOException {	
		try {
			WebElement webElement = driver.findElement(By.xpath(path));
			webElement.sendKeys(keysToSend);
			extentTest.get().log(Status.PASS,  keysToSend + " is entered Succesfully",MediaEntityBuilder.
					createScreenCaptureFromPath((TakeScreenshot(Baseclass.driver))).build());
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			extentTest.get().log(Status.FAIL,  keysToSend + " is not entered Succesfully");
		}
		return keysToSend;
	}


	public void navigateUrl(WebDriver driver, String inputData) {
		driver.navigate().to(inputData);
	}

	public void wait(String inputData) {
		try {
			int time = Integer.parseInt(inputData);
			int seconds = time * 1000;
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			Reporter.log("Unable to wait ", false );
		}
	}


}















