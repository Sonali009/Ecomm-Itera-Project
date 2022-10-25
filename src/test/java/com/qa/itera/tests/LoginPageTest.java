package com.qa.itera.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.itera.base.BaseTest;
import com.qa.itera.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest{
	
	@Description("Login page title test....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginpage.getLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title , Constants.LOGIN_PAGE_TITLE);
	}

	@Description("login test with correct credentials...")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 3)
	public void loginTest() {
		accountspage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountspage.getAccountsPageTitle(), Constants.ACC_PAGE_TITLE);
	}
}

