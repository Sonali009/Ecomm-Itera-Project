package com.qa.itera.pages;

import org.openqa.selenium.By;

public class CartPage {

	private By cartButton= By.id("cart");
	
	public CartPage() {
		System.out.println("my cart page.......");
	}
		
	public void clickOnCart() {
		System.out.println(cartButton);
	}
}
