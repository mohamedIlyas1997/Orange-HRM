package com.hrm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {

	public static WebDriver driver;
	public static Properties pro;

	@BeforeSuite
	public void setup() {

	WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(getproperty("URL"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

	}

		@AfterSuite
		public void teardown() {
			driver.quit();
		}

	public static String TimeStamp() {
		return new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
	}

	
	public static String TakeScreenshot(WebDriver driver) throws IOException {

		File source =  ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshots/" + TimeStamp() + ".png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}

		
	public static Properties readconfig() {

		try {
			pro= new Properties();
			FileInputStream fis= new FileInputStream(System.getProperty("user.dir") + "/Configuration/Config.properties");
			pro.load(fis);
		} catch
		(FileNotFoundException e) 
		{
			e.printStackTrace(); 
		}catch (IOException e) {
			e.printStackTrace();
		} return pro;
	}

	public static String getproperty(String Data) {
		readconfig();
		String data=pro.getProperty(Data);
		return data;
	}


	
	
}
