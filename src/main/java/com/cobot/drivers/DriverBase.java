package com.cobot.drivers;

import org.openqa.selenium.WebDriver;

/**
 * The Class DriverBase.
 */
public class DriverBase {

    /**
     * Gets the driver.
     * @param platformName the platform name
     * @param browserName the browser name
     * @return the driver
     * @throws Exception
     */
    public static WebDriver getDriver() throws Exception {
        return new DriverFactory().getDriver();
    }
}