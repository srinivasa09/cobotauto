/********************************************************************************************************************************
 * Change History:
 * 
 * Revision     Date            Updated by                  Comments
 * ---------------------------------------------------------------------------------------------------------------------------
 * 1.0          10-Mar-2018     Punnam Santosh Kumar        Creating the new Function Library
 * 
 * 
 * Copyright (c) Rock Interview. All Rights Reserved.
 ********************************************************************************************************************************/
package com.cobot.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cobot.contants.Constants;
import com.cobot.reports.Reports;
import com.cobot.utils.AppData;

/**
 * The Class BaseUtility.
 */
public class BaseUtility {

    /** WebDriver */
    public static WebDriver driver;

    public static int responseCode;

    public static String jobId;

    /**
     * Returns the text value of an Object
     * @param object is the WebElement
     * @return the text
     * @throws Exception to the calling place
     */
    public static String getText(WebElement object) throws Exception {
        Reports.info("Running... getText(WebElement object)");
        try {
            waitForObjectToBePresent(object, Constants.MAX_WAITING_TIME);
            return object.getText();
        } catch (Exception e) {
            Reports.fail("Unable to read the object: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Returns the text value of an Object
     * @param object is the WebElement
     * @param property is the object attribute value
     * @return the require attribute value
     * @throws Exception to the calling place
     */
    public static String getProperty(WebElement object, String property) throws Exception {
        Reports.info("Running... getProperty(WebElement object, String property)");
        try {
            waitForObjectToBePresent(object, Constants.MAX_WAITING_TIME);
            return object.getAttribute(property);
        } catch (Exception e) {
            Reports.fail("Unable to read the object: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Clicks on the Object.
     * @param objname is the WebElement
     * @throws Exception to the calling place
     */
    public static void Click(WebElement objname) throws Exception {
        Reports.info("Running... Click(WebElement objname)");
        try {
            waitForObjectToBeClickable(objname, Constants.MAX_WAITING_TIME);
            if (objname.isDisplayed()) {
                objname.click();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Reports.fail("Unable to click on the object: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Enters the input value in Object.
     * @param object is the WebElement
     * @param value is the input value
     * @throws Exception to the calling place
     */
    public static void SendKeys(WebElement object, String value) throws Exception {
        Reports.info("Running... SendKeys(WebElement object, String value)");
        try {
            waitForObjectToBePresent(object, Constants.MAX_WAITING_TIME);
            object.clear();
            object.sendKeys(value);
        } catch (Exception e) {
            Reports.fail("Unable to enter the text in the input object: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Gets the value from table.
     * @param object is the WebElement
     * @param row is the table row number
     * @param col is the table column number
     * @return the value from table
     * @throws Exception to the calling place
     */
    public static String getValueFromTable(WebElement object, int row, int col) throws Exception {
        Reports.info("Running... getValueFromTable(WebElement object, int row, int col)");
        try {
            waitForObjectToBePresent(object, Constants.MAX_WAITING_TIME);
            List<WebElement> tableRows = object.findElements(By.tagName("tr"));
            WebElement currentRow = tableRows.get(row - 1);
            List<WebElement> tableCols = currentRow.findElements(By.tagName("td"));
            String cell = tableCols.get(col - 1).getText();
            return cell;
        } catch (Exception e) {
            Reports.fail(
                    "Exception in getValueFromTable(WebElement object, int row, int col) Method: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Doble clicks on the Object.
     * @param object is the WebElement
     * @throws Exception to the calling place
     */
    public static void dobleclick(WebElement object) throws Exception {
        Reports.info("Running... dobleclick(WebElement object)");
        try {
            waitForObjectToBeClickable(object, Constants.MAX_WAITING_TIME);
            Actions action = new Actions(driver);
            action.moveToElement(object).build().perform();
            action.doubleClick(object).build().perform();
        } catch (Exception e) {
            Reports.fail("Exception in dobleclick(WebElement object) Method: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Checks if is object present or not.
     * @param object is the WebElement
     * @return true, if successful
     * @throws Exception to the calling place
     */
    public static boolean isElementPresent(WebElement object) {
        Reports.info("Running... isElementPresent(WebElement object)");
        try {
            waitForObjectToBePresent(object, Constants.MIN_WAITING_TIME);
            object.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for object to be clickable.
     * @param object is the WebElement
     * @param time is the waiting time
     * @throws Exception to the calling place
     */
    public static void waitForObjectToBeClickable(WebElement object, int time) throws Exception {
        Reports.info("Running... waitForObjectToBeClickable(WebElement object, int time)");
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(object));
    }

    /**
     * Wait for object to be present.
     * @param object is the WebElement
     * @param time is the waiting time
     * @throws Exception to the calling place
     */
    public static void waitForObjectToBePresent(WebElement object, int time) throws Exception {
        Reports.info("Running... waitForObjectToBeClickable(WebElement object, int time)");
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOf(object));
    }

    /**
     * Select text by visible text.
     * @param object is the WebElement
     * @param time is the waiting time
     * @throws Exception to the calling place
     */
    public static void selectTextByVisibleText(WebElement object, String text) throws Exception {
        Reports.info("Running... selectTextByVisibleText(WebElement object, String text)");
        try {
            Select select = new Select((object));
            select.selectByVisibleText(text);
        } catch (Exception e) {
            Reports.fail("Unable to Select the value from the drop down object: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Select text by index.
     * @param object is the WebElement
     * @param index is the item index value
     * @throws Exception to the calling place
     */
    public static void selectTextByIndex(WebElement object, int index) throws Exception {
        Reports.info("Running... selectTextByIndex(WebElement object, int index)");
        try {
            Select select = new Select((object));
            select.selectByIndex(index);
        } catch (Exception e) {
            Reports.fail("Unable to Select the value from the drop down object: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Generates the Random string.
     * @param length is the length of the random string
     * @param stringCase is the case like [UCASE OR LCASE] (Optional)
     * @return the random string
     * @throws Exception to the calling place
     */
    public static String randomString(int length, String... stringCase) throws Exception {
        Reports.info("Running... randomString(int length, String... stringCase)");

        String rndString = "";
        String letters = "";

        if (stringCase.length > 0) {
            if (StringUtils.equalsIgnoreCase("UCASE", stringCase[0])) {
                letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            } else if (StringUtils.equalsIgnoreCase("LCASE", stringCase[0])) {
                letters = "abcdefghijklmnopqrstuvwxyz";
            } else {
                letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            }
        } else {
            letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        }

        for (int i = 0; i < length; i++) {
            double a = Math.random();
            int x = (int) (a * 100);

            if (letters.length() == 26) {
                if (x > 25 && x < 52) {
                    x = x - 26;
                } else if (x > 51 && x < 78) {
                    x = x - 52;
                } else if (x > 77 && x < 99) {
                    x = x - 78;
                } else {
                    x = 1;
                }
            } else {
                if (x > 51 && x < 78) {
                    x = x - 26;
                } else if (x > 77 && x < 99) {
                    x = x - 52;
                } else {
                    x = 1;
                }
            }

            char letter = letters.charAt(x);
            rndString = rndString + String.valueOf(letter);
        }

        return rndString;
    }

    /**
     * Generates the Random number.
     * @param length if the the length of the random number
     * @return the random string
     * @throws Exception to the calling place
     */
    public static String randomNumber(int length) throws Exception {
        Reports.info("Running... randomNumber(int length)");
        String rndNumber = "";
        String numbers = "1234567890";

        for (int i = 0; i < length; i++) {
            double a = Math.random();
            int x = (int) (a * 10);

            if (x > 9) {
                x = x - 10;
            }
            char number = numbers.charAt(x);
            rndNumber = rndNumber + number;
        }
        return rndNumber;
    }

    /**
     * Gets the column data.
     * @param inputArray the test object array
     * @param colName is the column name
     * @return the cell data
     * @throws IOException
     */
    public static String getColumnData(Object[][] inputArray, String colName) throws IOException {
        Reports.info("Running... getColumnData(Object[][] inputArray, String colName)");
        try {
            for (int i = 0; i < inputArray[0].length; i++) {
                if (inputArray[0][i].equals(colName)) {
                    return (String) inputArray[1][i];
                }
            }
        } catch (Exception e) {
            Reports.fail("Exception in getCellData(Object[][] testObjectArray, String colName) Method");
        }
        return null;
    }

    /**
     * Sets the config data.
     * @param keyName is the key name
     * @param keyValue is the key value
     * @throws Exception to the calling place
     */
    public static void setConfigData(String keyName, String keyValue) throws Exception {
        Reports.info("Running... setConfigData(String keyName, String keyValue)");
        String configFile = "Configuration\\Config.properties";

        FileInputStream fis = new FileInputStream(configFile);

        Properties sourceProp = new Properties();
        sourceProp.load(fis);
        HashMap<String, String> configData = new HashMap<String, String>();
        Set<Object> keys = sourceProp.keySet();
        for (Object k : keys) {
            configData.put((String) k, sourceProp.getProperty((String) k));
        }
        fis.close();

        Properties destProp = new Properties();
        for (Object k : keys) {
            destProp.setProperty((String) k, configData.get((String) k));
        }
        destProp.setProperty(keyName, keyValue);
        FileOutputStream fos = new FileOutputStream(configFile);
        destProp.store(fos, "dynamic data");
        fos.close();
    }

    /**
     * Gets the date.
     * @param format is the date format
     * @param days is for the future date (Optional)
     * @return the date value
     */
    public static String getDate(String format, int... days) {
        Reports.info("Running... getDate(String format, int... days)");
        String date = null;
        int numOfDays = 0;
        if (days.length > 0) {
            numOfDays = days[0];
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, numOfDays);
        date = dateFormat.format(calendar.getTime());
        return date;
    }

    /**
     * Gets the date.
     * @param days is for the future date (for current date: days value should be '0')
     * @param format is the date format (Optional)
     * @return the date value
     */
    public static String getDate(int days, String... format) {
        Reports.info("Running... getDate(int days, String... format)");
        DateFormat dateFormat = null;
        if (format.length == 0) {
            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        } else {
            dateFormat = new SimpleDateFormat(format[0]);
        }
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, days);
        Date time = c.getTime();
        String date = dateFormat.format(time);
        return date;
    }

    /**
     * Gets the current date.
     * @return the date value
     */
    public static String getDate() {
        Reports.info("Running... getDate(int days, String... format)");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        Date time = c.getTime();
        String date = dateFormat.format(time);
        return date;
    }

    /**
     * Gets the random email which contains current time stamp.
     * @return the random email
     */
    public static String getRandomEmail() {
        Reports.info("Running... getRandomEmail()");
        String date = getDate("ddMMYYYYhhmmss");
        return "email_" + date + "@gmail.com";
    }

    /**
     * Gets the random IP address.
     * @return the random IP address
     * @throws Exception to the calling place
     */
    public static String getRandomIPAddress() throws Exception {
        Reports.info("Running... getRandomIPAddress()");
        try {
            StringBuilder sb = new StringBuilder();

            Random r = new Random();
            sb.append(r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256));

            return sb.toString();
        } catch (Exception e) {
            Reports.fail("Exceltipn in getRandomIPAddress() Method: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Gets the time stamp.
     * @param format is the time stamp format
     * @param future is for the future time (Optional)
     * @return the time stamp value
     * @throws Exception to the calling place
     */
    public static String getTimeStamp(String format, int... future) throws Exception {
        Reports.info("Running... getTimeStamp(String format, int... future)");
        String strDate = null;
        try {
            int next = 0;
            if (future.length != 0) {
                next = future[0];
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date now = new Date(new Date().getTime() + next * 24 * 60 * 60 * 1000);
            strDate = sdf.format(now);
            return strDate;
        } catch (Exception e) {
            Reports.fail("Exception in getTimeStamp(String format, int... future) method: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Gets the time stamp.
     * @return the time stamp value
     * @throws Exception to the calling place
     */
    public static String getTimeStamp() throws Exception {
        Reports.info("Running... getTimeStamp()");
        String date = null;
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("ddMMYYYYhhmmss");
            Date now = new Date(new Date().getTime());
            date = timeFormat.format(now);
        } catch (Exception e) {
            Reports.fail("Exception in getTimeStamp() method: " + e.getMessage());
            throw e;
        }
        return date;
    }

    /**
     * Replace the present value in a string with new value.
     * @param actualString is the actual string value
     * @param strOldWord is the Old String
     * @param strNewWord is the new String
     * @return the string
     */
    public static String replaceValue(String actualString, String strOldWord, String strNewWord) {
        Reports.info("Running... replaceValue(String actualString, String strOldWord, String strNewWord)");
        String strOutput = actualString.replace(strOldWord, strNewWord);
        return strOutput;
    }

    /**
     * Deletes the file if it is already exists.
     * @param strFilePath is the file path
     */
    public static void deleteFile(String strFilePath) {
        Reports.info("Running... deleteFile(String strFilePath)");
        File file = new File(strFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Creates the file.
     * @param strPath is the file path
     * @param strData is the file data to save
     * @throws Exception to the calling place
     */
    public static void createFile(String strPath, String strData) throws Exception {
        Reports.info("Running... createFile(String strPath, String strData)");
        try {
            FileWriter file = new FileWriter(strPath);
            file.write(strData);
            file.flush();
            file.close();
        } catch (Exception e) {
            Reports.fail("Exception in createFile(String strPath, String strData) Method: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Creates the file.
     * @param strPath is the file path
     * @throws Exception to the calling place
     */
    public static Boolean isFileExist(String strPath) throws Exception {
        Reports.info("Running... createFile(String strPath, String strData)");
        try {
            File file = new File(strPath);
            if (file.exists()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Reports.fail("Exception in createFile(String strPath, String strData) Method: " + e.getMessage());
            throw e;
        }
    }

    /**
     * create Folder If it is Not Exist
     * @param directory
     * @throws Exception
     */
    public static void createFolderIfNotExist(String directory) throws Exception {
        Reports.info("Running... createFolderIfNotExist(String directory)");
        try {
            File fileDirectry = new File(directory);
            if (!fileDirectry.exists()) {
                fileDirectry.mkdir();
            }
        } catch (Exception e) {
            Reports.fail("Exception in createFolderIfNotExist(String directory) Method: " + e.getMessage());
            throw e;
        }
    }

    public static String encryptPwd(String password) throws Exception {
        final byte[] defaultBytes = password.getBytes();
        try {
            final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
            md5MsgDigest.reset();
            md5MsgDigest.update(defaultBytes);
            final byte messageDigest[] = md5MsgDigest.digest();
            final StringBuffer hexString = new StringBuffer();
            for (final byte element : messageDigest) {
                final String hex = Integer.toHexString(0xFF & element);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            password = hexString + "";
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(e.getMessage());
        }
        return password;
    }

    public static String getOutPut(String jobId, String status, String reason) {
        String outputJson = AppData.properties.getProperty("output_json");
        outputJson = outputJson.replace("$jobId", jobId);
        outputJson = outputJson.replace("$status", "Success");
        outputJson = outputJson.replace("$reason", "");
        return outputJson;
    }
}