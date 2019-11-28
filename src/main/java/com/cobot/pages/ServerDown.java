package com.cobot.pages;

import static com.cobot.lib.BaseUtility.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.cobot.lib.BaseUtility;

public class ServerDown {
    public ServerDown(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//div[@id='error-state']/span")
    WebElement errorFound;

    public Boolean isServerDown() {
        try {
            return BaseUtility.isElementPresent(errorFound);
        } catch (Exception e) {
            return false;
        }
    }
}
