package com.qa.itera.tests;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.itera.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetUp() {
		accountspage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void productInfoDataTest() {
		searchResultPage = accountspage.doSearch("evy");
		productInfoPage = searchResultPage.selectProductFromResults("evy");
		Map<String, String> accproductInfoMap = productInfoPage.getProductInformation();
		
	}
	
	
	

}
