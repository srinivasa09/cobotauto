package com.cobot.pages;

import static com.cobot.lib.BaseUtility.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.cobot.lib.BaseUtility;
import com.cobot.reports.Reports;

public class JiraLoginPage {

    public JiraLoginPage(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//button[@id='google-signin-button']/span/span/span")
    WebElement btnLoginWithGoogle;

    public void clickOnSignWithGoogle() throws Exception {
        try {
            BaseUtility.Click(btnLoginWithGoogle);
            Reports.pass("Clicked on Sign In With Google Account");
        } catch (Exception e) {
            Reports.fail("Unable to Click on Sign In With Google Account");
            throw e;
        }
    }

}
