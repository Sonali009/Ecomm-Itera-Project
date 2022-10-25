package com.qa.itera.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.itera.utils.ElementUtil;

public class ProductInfoPage {

ElementUtil elementutil;
	
	//private By productHeader =  By.xpath("/html/body/div/div/table/tbody/tr[2]/td[1]");
	private By productHeader = By.xpath("/html/body/div/div/table/tbody/tr/td[1]");
	private By productImages = By.xpath("/html/body/div/div/table/tbody/tr/td[1]");
	private By productMetaData = By.xpath("//table[@class='table table-hover']/tbody/tr/td[1][contains(text(),'evy')]");
	//private By productMetaData = By.xpath("//section[@id='informations_block_left_1']/div/ul/li");
	//private By productPriceData = By.xpath("(//span[@class='price product-price'])[2]");
	//private By quantity = By.xpath("");
	//private By addToCartBtn = By.xpath("//a[@class='button ajax_add_to_cart_button btn btn-default']");
	//private By productImages = By.xpath("/html/body/div/div/table/tbody/tr[2]");
	
	public ProductInfoPage(WebDriver driver) {
		elementutil = new ElementUtil(driver);
		
	}
	
	public String getHeaderText() {
		return elementutil.doGetElementText(productHeader);
	}
	
	public int getProductImagesCount() {
		return elementutil.getElements(productImages).size();
	}

	public Map<String, String> getProductInformation() {
		Map<String, String> productinfomap = new HashMap<String, String>();
		productinfomap.put("name", getHeaderText());
		List<WebElement> productmetadatalist = elementutil.getElements(productMetaData);
		System.out.println(productmetadatalist.size());
		
		for(WebElement e : productmetadatalist) {
			String meta[] = e.getText().split(":");
			String metakey = meta[0].trim();
			String metavalue = meta[1].trim();
			productinfomap.put(metakey, metavalue);
					
		}
		
		return productinfomap;
		
	}

}
