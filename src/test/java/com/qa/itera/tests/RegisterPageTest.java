package com.qa.itera.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.itera.base.BaseTest;
import com.qa.itera.utils.Constants;
import com.qa.itera.utils.Error;
import com.qa.itera.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	

	@BeforeTest
	public void setupRegister() {
		registerpage=loginpage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object data[][]= ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return data;
	}
//	@Test
//	public void userRegisterTest() {
//		Assert.assertTrue(registerpage.accountRegistration("camel", "c", "pune", "9191919191", "camel_c", "gggg", "gggg"),
//				Error.REGISTER_FAILED_MESSG);
//		
//		
//	}
	@Test(dataProvider = "getRegisterData")
	public void userRegisterTest(String firstname, String lastname, String	epost, String	mobile, String	username,
			String password, String confirmPwd) {
		Assert.assertTrue(registerpage.accountRegistration(firstname, lastname, epost, mobile, username, password, confirmPwd), 
				Error.REGISTER_FAILED_MESSG);
	}

	
	
	
	
	
}
