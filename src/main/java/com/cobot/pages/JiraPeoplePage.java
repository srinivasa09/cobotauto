package com.cobot.pages;

import static com.cobot.lib.BaseUtility.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.cobot.lib.BaseUtility;
import com.cobot.reports.Reports;

public class JiraPeoplePage {

    public JiraPeoplePage(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//button/span/span/span[text()='Add People']")
    WebElement btnAddPeople;

    @FindBy(how = How.XPATH, using = "//form[@id='inviteForm']//input")
    WebElement edtAddEmail;

    @FindBy(how = How.XPATH, using = "//form[@id='inviteForm']/div//button[1]")
    WebElement btnSend;

    public void addNewUser(String emailId) throws Exception {
        try {
            BaseUtility.Click(btnAddPeople);
            BaseUtility.SendKeys(edtAddEmail, emailId);
            BaseUtility.Click(btnSend);
            Reports.pass("Successfully added the new email Id");
        } catch (Exception e) {
            Reports.fail("Unable to adde the new email Id");
            throw e;
        }
    }
}
