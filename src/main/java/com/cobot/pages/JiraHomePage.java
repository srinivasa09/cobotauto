package com.cobot.pages;

import static com.cobot.lib.BaseUtility.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.cobot.lib.BaseUtility;
import com.cobot.reports.Reports;

public class JiraHomePage {

    public JiraHomePage(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//a[@id='people']/div[2]/div")
    WebElement people;

    public void clickOnPeople() throws Exception {
        try {
            Reports.pass("Successfully Launched the Jira Application Home Page");
            BaseUtility.Click(people);
            Reports.pass("Successfully Clicked on People Button");
        } catch (Exception e) {
            Reports.fail("Unable to Click on People Button");
            throw e;
        }
    }
}
