package com.qa.itera.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.itera.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private Actions act; 
	private JavaScriptUtil JsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		act = new Actions(this.driver);
		JsUtil = new JavaScriptUtil(this.driver);
	}
	
	public  WebElement getElement(By locator) {
		WebElement ele =  driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			JsUtil.flash(ele);
		}
		return ele;
	}
	
	public  void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	
	public  void doClick(By locator) { 
		getElement(locator).click(); 
	}
	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public void doLinkClick(By locator, String value) {
		
		List<WebElement> linkslist = getElements(locator);

		System.out.println(linkslist.size());
		
		for(WebElement e : linkslist) {
			
			String text = e.getText();
			System.out.println(text);
			if(text.equals(value)) {
				e.click();
				break;
			}
			
		}
		
	}
	
	//******************DropDown Utils************************
	
	
	
	public void doSelectByVisibleText(By locator, String text) {
		
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	/**
	 * this method is used to select the value from drop down without select class.
	 * @param locator
	 * @param value
	 */
	public void doSelectDropDownWithoutSElectClass(By locator, String value) {
		List<WebElement> wb = getElements(locator);
		for(WebElement e : wb) {
			if(e.getText().equals(value)) {
				e.click();
				break;
			}
		}

	}
	
	//**************ACTIONS CLASS UTILS********************
	//2 level menu and sub menu
		public  void doMoveToElement(By locator) {
			Actions act = new Actions(driver);
			act.moveToElement(getElement(locator)).perform();
			
		}
		//3 level menu and sub menu
		public  void doMoveToElement(By locator1, By locator2) {
			Actions act = new Actions(driver);
			act.moveToElement(getElement(locator1)).perform();
			act.moveToElement(getElement(locator2)).perform();
			
		}
		// 3 level menu and sub menu with click
		public  void doMoveToElement(By locator1, By locator2, By locator3) {
			Actions act = new Actions(driver);
			act.moveToElement(getElement(locator1)).perform();
			act.moveToElement(getElement(locator2)).perform();
			doClick(locator3);
			
		}
		
		//**********************Calendar Utils*************************
		
		public  void selectDate(String day, String tagname) {
			String xpath = "//" +tagname + "[text()='"+day+"']";
			//driver.findElement(By.xpath("//"+tagname+"[text()='"+day+"']")).click();
			//getElement(By.xpath(xpath)).click();
			doClick(By.xpath(xpath));
			
		}
		
		public  void selectDate(By locator, String day) {
			boolean flag = false;
			List<WebElement> daysList = driver.findElements(locator);
			for(WebElement e : daysList) {
				if(e.getText().equals(day)) {
					System.out.println("right date...."+ day);
					e.click();
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				System.out.println("wrong date..." + day);
			}
		}
		
		//************wait concept*************
		
		public WebElement waitForPresenceOfElement(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return  wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		public Alert waitForAlertPresent(int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.alertIsPresent());
		}
		
		public String getAlertText(int timeout) {
			String text = waitForAlertPresent(timeout).getText();
			acceptAlert(timeout);
			 return text;
		}
		
		public void acceptAlert(int timeout) {
			waitForAlertPresent(timeout).accept();
		}
		public void dismissAlert(int timeout) {
			waitForAlertPresent(timeout).dismiss();
		}
		
		public String waitForTitleContains(int timeout, String title) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.titleContains(title));
			return driver.getTitle();
		}
		
		public String waitForTitleIs(int timeout, String title) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.titleIs(title));
			return driver.getTitle();
		}
		public Boolean waitForTitle(int timeout, String title) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.titleIs(title));
			
		}

		public WebElement waitForVisibilityOfElement(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			//WebElement ele = driver.findElement(locator);
			return  wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
		}
		public  List<WebElement> waitForVisibilityOfElements(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
					return  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		public void printElementsText(By locator, int timeout) {
			waitForVisibilityOfElements(locator, timeout)
						.stream().forEach(ele -> System.out.println(ele.getText()));
		}
		
		public WebElement waitForElementToBeClickable(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			 return wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		
		public void clickWhenReady(By locator, int timeout) {
			waitForElementToBeClickable(locator, timeout).click();
		}
		public void getElementAttribute(By locator, int timeout, String name) {
			waitForElementToBeClickable(locator, timeout).getAttribute(name);
		}

		public Boolean waitForElementTextToBeClickable(By locator, int timeout, String value) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			 return wait.until(ExpectedConditions.textToBe(locator, value));
		}
		public Boolean waitForUrlToBe( int timeout, String urlvalue) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			 return wait.until(ExpectedConditions.urlContains(urlvalue));
		}
		public WebElement waitforElementWithFluentWait(By locator, int timeout, int poolingTime) {
			Wait<WebDriver> wait= new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeout))
					.pollingEvery(Duration.ofSeconds(poolingTime))
					.ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class);
				return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

			
		}
}
