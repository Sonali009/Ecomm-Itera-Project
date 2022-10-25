package com.qa.itera.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	public static String highlight;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	/**
	 * This method is used to initialize the driver on the basis of give browser value.
	 * @param browserName
	 * @return webdriver
	 * @throws InterruptedException 
	 */
	
	public WebDriver int_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim(); 
		highlight = prop.getProperty("highlight").trim();
		optionsManager = new OptionsManager(prop);
		System.out.println(browserName);
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
		}
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver= new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if (browserName.equals("safari")) {
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else {
			System.out.println("browser not found....");
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		
		
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	/**
	 * this method is initializing the properties from .properties file
	 * @return properties (prop)
	 */
	public Properties int_properties() {
		prop = new Properties();
		FileInputStream ip = null;
		
		String env = System.getProperty("env");
		
		if(env==null) {try { ip = new FileInputStream("./src/test/resources/config/config.properties");
		prop.load(ip);
		
	} catch (FileNotFoundException e) {
					e.printStackTrace();
	}catch (IOException e) {
		e.printStackTrace();
}
			
		}else {
			try {
			switch (env) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
					case "dev":
						ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
						break;
			default: System.out.println("please pass the right env....");
				break;
			} 
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}return prop;
		
	}
	
	
	/*
	 * Take Screenshot--
	 * 
	 */
	public String getScreenShot() {
	String src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BASE64);
	File srcFile = new File(src);
	
	String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
	File destination = new File(path);
	
	try {
		FileUtils.copyFile(srcFile, destination);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return path;
	
	}
	
}
