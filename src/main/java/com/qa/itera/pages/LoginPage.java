package com.qa.itera.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.itera.utils.Constants;
import com.qa.itera.utils.ElementUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;


@Story("US-100: design the loginpage for itera appliaction")
@Epic("Epic-100: design login page feature")
public class LoginPage {
	
	private ElementUtil elementutil;
	private WebDriver driver;
	
	private By username = By.id("Username");
	private By password = By.id("Password");
	private By loginbutton = By.name("login");
	private By register = By.xpath("//a[text()='Sign Up']");
	
	
	
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		elementutil = new ElementUtil(driver);
		//elementutil.waitForUrlToBe(5, "url");
		 
	}
	
	
	//page actions--
	@Step("checking login page title....")
	public String getLoginPageTitle() {
		return elementutil.waitForTitleContains(10, Constants.LOGIN_PAGE_TITLE);
			}
	
		@Step("login with username: {0} and password {1}")
		public AccountsPage doLogin(String un, String pw) {
		System.out.println(un+pw);
				
		elementutil.doSendKeys(username, un);
		elementutil.doSendKeys(password, pw);
		elementutil.doClick(loginbutton); 
		//elementutil.waitForElementToBeClickable(loginbutton, 10);
		//elementutil.waitForTitleContains(10, Constants.ACC_PAGE_TITLE);
				return new AccountsPage(driver);
		
}
		@Step("Navigating to register page....")
		public RegisterPage navigateToRegisterPage() {
			elementutil.doClick(register);
			return new RegisterPage(driver);
		}
}
