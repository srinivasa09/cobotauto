package com.cobot.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The Class AppData.
 */
public class AppData {

    /** The properties. */
    public static Properties properties = new Properties();

    static {
        try {
            InputStream is = AppData.class.getClassLoader().getResourceAsStream("Config.properties");
            properties.load(is);
            is.close();
        } catch (IOException e) {
        }
    }

    /**
     * Gets the property.
     * @param property the property
     * @return the property
     */
    public static String getProperty(String property) {
        try {
            return properties.getProperty(property).toString();
        } catch (Exception e) {
            return "";
        }
    }

}
