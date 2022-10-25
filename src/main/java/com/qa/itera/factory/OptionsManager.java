package com.qa.itera.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		//ChromeOptions co = new ChromeOptions();
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
			
			if(Boolean.parseBoolean(prop.getProperty("--incognito"))) co.addArguments("--incognito");
				
			return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		//ChromeOptions co = new ChromeOptions();
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
			
			if(Boolean.parseBoolean(prop.getProperty("--incognito"))) co.addArguments("--incognito");
				
			return fo;
	}

}
