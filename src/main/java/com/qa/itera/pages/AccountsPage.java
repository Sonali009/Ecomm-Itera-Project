package com.qa.itera.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.itera.utils.Constants;
import com.qa.itera.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	ElementUtil elementutil;

	private By logo = By.xpath("//a[@class='navbar-brand']");
	private By AccountHeaders = By.xpath("//tr[@class='table-primary']/th");
	private By searchfield = By.id("searching");
	private By searchbutton = By.xpath("//input[@value='Search']");
		
		public AccountsPage(WebDriver driver) {
			this.driver = driver;
			elementutil = new ElementUtil(driver);
		}
		
		public String getAccountsPageTitle() {
			return elementutil.waitForTitleIs(5, Constants.ACC_PAGE_TITLE);
		}
		public boolean isLogoExist() {
			return elementutil.doIsDisplayed(logo);
		} 
		
		public int getAccountPageHeadersCount() {
			return elementutil.getElements(AccountHeaders).size();
		}
		
		public List<String> getAccountPageHeadersList() {
			List<WebElement> acclist = elementutil.getElements(AccountHeaders);
			List<String> accSectionList = new ArrayList<String>();
			for(WebElement e : acclist) {
				accSectionList.add(e.getText());
			}
			return accSectionList;
		}
		
		public SearchResultPage doSearch(String productName) {
			System.out.println("searching for the product"+ productName);
			elementutil.doSendKeys(searchfield, productName);
			elementutil.doClick(searchbutton);
			return new SearchResultPage(driver);
		}
		
	}

