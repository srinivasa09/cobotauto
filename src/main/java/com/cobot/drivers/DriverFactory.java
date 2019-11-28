package com.cobot.drivers;

import static com.cobot.drivers.DriverType.CHROME;

import org.openqa.selenium.WebDriver;

import com.cobot.utils.AppData;

/**
 * A factory for creating Driver objects.
 */
public class DriverFactory {

    /**
     * Gets the driver.
     * @param platformName the platform name
     * @param browserName the browser name
     * @return the driver
     * @throws Exception
     */
    public WebDriver getDriver() throws Exception {
        DriverType driverType = CHROME;
        try {
            driverType = DriverType.valueOf(AppData.properties.getProperty("browser").toUpperCase());
        } catch (Exception exception) {
            throw exception;
        }
        return driverType.getWebDriverObject();
    }
}
