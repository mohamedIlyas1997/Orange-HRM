package com.hrm.testcases;

import org.testng.annotations.Test;

import com.hrm.base.Baseclass;
import com.hrm.pages.LoginPage;

public class TestCases extends Baseclass {
	
	LoginPage log= new LoginPage();
	

	
	@Test
	public void loginpage() throws Throwable {
		log.login();
	}
	

}
