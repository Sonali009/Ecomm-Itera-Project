package com.qa.itera.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.itera.factory.DriverFactory;
import com.qa.itera.pages.AccountsPage;
import com.qa.itera.pages.LoginPage;
import com.qa.itera.pages.ProductInfoPage;
import com.qa.itera.pages.RegisterPage;
import com.qa.itera.pages.SearchResultPage;

public class BaseTest {
	
	private WebDriver driver;
	public Properties prop;
	DriverFactory df;
	public LoginPage loginpage;
	public AccountsPage accountspage;
	public SearchResultPage searchResultPage;
	public ProductInfoPage productInfoPage;
	public RegisterPage registerpage;
	
	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.int_properties();
		driver = df.int_driver(prop);
		loginpage = new LoginPage(driver);
		}
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
