package com.hrm.pages;

import com.hrm.base.Keywords;
import com.hrm.utilities.Utils;

public class LoginPage extends Keywords {


	public void login() throws Throwable, InterruptedException {


		String uname= Utils.getDataFromTestData("Hrm_Login", "Username");
		String user_locator= Utils.getDataFromTestData("Hrm_Login", "Locator1");
		String pwd= Utils.getDataFromTestData("Hrm_Login", "Password");
		String pwd_locator= Utils.getDataFromTestData("Hrm_Login", "Locator2");
		String login_locator= Utils.getDataFromTestData("Hrm_Login", "Locator3");


		sendKeys(driver, user_locator, uname);
		sendKeys(driver, pwd_locator, pwd);
		click(driver, login_locator);





	}


}
