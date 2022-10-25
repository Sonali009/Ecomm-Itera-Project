package com.qa.itera.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.itera.utils.ElementUtil;

public class SearchResultPage {

	private WebDriver driver;
	ElementUtil elementutil;

	By searchItemResult = By.xpath("/html/body/div/div/table/tbody/tr[2]/td[1]");
	By resultsItem = By.xpath("/html/body/div/div/table/tbody/tr/td[1]");
			
	
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(driver);
		
	}
	public int getProductResultsCount() {
		return elementutil.getElements(searchItemResult).size();
	}
	
	public ProductInfoPage selectProductFromResults(String productName) {
		List<WebElement> resultItemsList = elementutil.getElements(resultsItem);
		System.out.println("total item display"+resultItemsList.size());
		
		for(WebElement e : resultItemsList) {
			if(e.getText().equals(productName)){
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
		
	}

}
