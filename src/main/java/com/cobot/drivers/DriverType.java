package com.cobot.drivers;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import com.cobot.utils.AppData;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * The Enum DriverType.
 */
public enum DriverType implements DriverSetup {

    /** The firefox. */
    FIREFOX {
        public WebDriver getWebDriverObject() {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if (HEADLESS) {
                options.addArguments("headless");
            }
            return new FirefoxDriver(options);
        }
    },

    /** The chrome. */
    CHROME {
        public WebDriver getWebDriverObject() {
            System.setProperty("webdriver.chrome.driver", "../Drivers/chromedriver.exe");
            HashMap<String, Object> chromePreferences = new HashMap<String, Object>();
            chromePreferences.put("profile.password_manager_enabled", false);

            ChromeOptions options = new ChromeOptions();
            if (HEADLESS) {
                options.addArguments("headless");
            }
            options.setExperimentalOption("prefs", chromePreferences);

            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(options);
        }
    },

    /** The ie. */
    IE {
        public WebDriver getWebDriverObject() {
            WebDriverManager.iedriver().setup();
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
            return new InternetExplorerDriver(options);
        }
    };

    /**
     * 
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    /** The Constant HEADLESS. */
    public final static boolean HEADLESS = Boolean.valueOf(AppData.properties.getProperty("headless"));
}