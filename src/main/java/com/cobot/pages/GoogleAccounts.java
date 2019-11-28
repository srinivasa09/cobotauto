package com.cobot.pages;

import static com.cobot.lib.BaseUtility.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.cobot.lib.BaseUtility;
import com.cobot.reports.Reports;

public class GoogleAccounts {

    public GoogleAccounts(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//input[@id='identifierId']")
    WebElement edtUserName;

    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    WebElement edtPassword;

    @FindBy(how = How.XPATH, using = "//div[@id='identifierNext']/span")
    WebElement btnNext;

    @FindBy(how = How.XPATH, using = "//*[@id='passwordNext']/span/span")
    WebElement btnSignIn;

    public void signIn(String userName, String password) throws Exception {
        try {
            BaseUtility.SendKeys(edtUserName, userName);
            BaseUtility.Click(btnNext);
            BaseUtility.SendKeys(edtPassword, password);
            BaseUtility.Click(btnSignIn);
            Reports.pass("Successfully Signed with Google Account");
        } catch (Exception e) {
            Reports.fail("Unable to Signed with Google Account");
            throw e;
        }
    }
}
