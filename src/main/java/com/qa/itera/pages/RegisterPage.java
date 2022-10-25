package com.qa.itera.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.itera.utils.Constants;
import com.qa.itera.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil elementutil;
	
	
	private By firstName = By.id("FirstName");
	private By lastName = By.id("Surname");
	private By epost = By.id("E_post");
	private By mobile = By.id("Mobile");
	private By username = By.id("Username");
	private By password = By.id("Password");
	private By confirmPwd = By.id("ConfirmPassword");
	private By submitbtn = By.id("submit");
	private By successMsg = By.xpath("//label[text()='Registration Successful']");
	private By register = By.xpath("//a[text()='Sign Up']");
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		elementutil= new ElementUtil(driver);
			}
	
	public boolean accountRegistration(String firstname, String lastname, String ePost, String mob, String userName, 
			String passwrd, String confirmpwd) {
		elementutil.doSendKeys(this.firstName, firstname);
		elementutil.doSendKeys(this.lastName, lastname);
		elementutil.doSendKeys(this.epost, ePost);
		elementutil.doSendKeys(this.mobile, mob);
		elementutil.doSendKeys(this.username, userName);
		elementutil.doSendKeys(this.password, passwrd);
		elementutil.doSendKeys(this.confirmPwd, confirmpwd);
		
		elementutil.doClick(submitbtn);
		elementutil.waitForVisibilityOfElement(this.successMsg, 5);
		String msg = elementutil.doGetElementText(this.successMsg);
		System.out.println(msg);
		
		if(msg.contains(Constants.ACCOUNT_CREATION_SUCCESS_MESSG)) {
			
			elementutil.doClick(register);
			return true;
					}else {
						return false;
					}
		
		
	}
	
	

}
