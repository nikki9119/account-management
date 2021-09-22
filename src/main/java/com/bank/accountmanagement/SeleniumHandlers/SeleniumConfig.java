package com.bank.accountmanagement.SeleniumHandlers;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumConfig {
	private WebDriver driver;
	
	public SeleniumConfig() {
	    Capabilities capabilities = DesiredCapabilities.chrome();
	    driver = new ChromeDriver(capabilities);
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	static {
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Aswatth\\Desktop\\chromedriver_win32\\chromedriver.exe");
	}
	public WebDriver getDriver() {
		return driver;
	}	
}
