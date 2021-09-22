package com.bank.accountmanagement.SeleniumHandlers;

import org.openqa.selenium.WebDriver;

public class SeleniumHandler {

    private SeleniumConfig config;

    public SeleniumHandler() {
        config = new SeleniumConfig();
    }
    public void closeWindow() {
        this.config.getDriver().close();
    }
	
	public void setURL(String url)
	{
		config.getDriver().get(url);
	}
	public String getCurrentURL()
	{
		return config.getDriver().getCurrentUrl();
	}
	
    public String getTitle() {
        return this.config.getDriver().getTitle();
    }
}
