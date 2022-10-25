package com.qa.itera.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.itera.base.BaseTest;
import com.qa.itera.utils.Constants;

public class AccountsPageTest extends BaseTest{
	
	SoftAssert softAssert = new SoftAssert();
	
	
	@BeforeTest 
	public void AccSetUp() {
		accountspage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void accountpageTitleTest() {
		String title = accountspage.getAccountsPageTitle();
		System.out.println("home page title is ...."+title);
		Assert.assertEquals(title, Constants.ACC_PAGE_TITLE, "account page doesnt exist...");
	}
	
	@Test(priority = 2)
	public void accountsPageLogoTest() {
		Assert.assertTrue(accountspage.isLogoExist(), "logo isnt available...");
		
	}
	@Test(priority = 3)
	public void accountsPageSectionsCount() {
		Assert.assertEquals(accountspage.getAccountPageHeadersCount(), 
				Constants.ACC_PAGE_SECTIONS_COUNT);
				//Error.ACC_PAGE_SECTION_ERROR);
	}
	
	@Test(priority = 4)
	public void accountsSectionsTest() {
		List<String> actAccSecList = accountspage.getAccountPageHeadersList();
		System.out.println(actAccSecList);
		Assert.assertEquals(actAccSecList, Constants.expectedAccSecList());
	}
	
	@Test(priority=5)
	public void searchTest() {
		searchResultPage = accountspage.doSearch("evy");
		Assert.assertTrue(searchResultPage.getProductResultsCount()>0, com.qa.itera.utils.Error.SEARCH_NOT_SUCCESSFUL);
	}
	
	@Test(priority=6)
	public void selectProductTest() {
		//searchResultPage = accountspage.doSearch("evy");
		productInfoPage = searchResultPage.selectProductFromResults("evy");
		String actheader = productInfoPage.getHeaderText();
		softAssert.assertEquals(actheader, com.qa.itera.utils.Error.PRODUCT_HEADER_NOT_FOUND);
		softAssert.assertEquals(productInfoPage.getProductImagesCount(), Constants.PRODUCT_IMAGES_COUNT_Camilla);
		softAssert.assertAll();
	}

}
