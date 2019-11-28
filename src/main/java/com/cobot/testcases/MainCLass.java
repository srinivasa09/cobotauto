package com.cobot.testcases;

import java.util.HashMap;
import java.util.Map;

public class MainCLass {
    public static void main(String[] args) throws Exception {
        Map<String, Object> inputData = new HashMap<String, Object>();
        inputData.put("url", "https://riteams.atlassian.net/jira/your-work2");
        inputData.put("adminUserName", "mana.santosh9991@gmail.com");
        inputData.put("adminPassword", "Forgot@12345");
        inputData.put("newUser", "punnamsatyasai1998@gmail.com");
        inputData.put("jobId", "123");
        JiraAddUserTest jira = new JiraAddUserTest();
        //jira.test(inputData, "123");

        // DesktopOptions option = new DesktopOptions();
        //
        // option.setApplicationPath("C:\\Windows\\System32\\calc.exe");
        //
        // WiniumDriver driver = new WiniumDriver(new URL("http://localhost:9999"), option);
        //
        // Thread.sleep(5000);
        //
        // driver.findElement(By.name("Seven")).click();
        // Thread.sleep(2000);
        //
        // driver.findElement(By.name("Plus")).click();
        // Thread.sleep(2000);
        //
        // driver.findElement(By.name("Seven")).click();
        // Thread.sleep(2000);
        //
        // driver.findElement(By.name("Equals")).click();
        //
        // Thread.sleep(5000);
        //
        // String output = driver.findElement(By.id("CalculatorResults")).getAttribute("Name");
        //
        // System.out.println("Result after addition is: " + output);
        //
        // driver.quit();

    }

}
