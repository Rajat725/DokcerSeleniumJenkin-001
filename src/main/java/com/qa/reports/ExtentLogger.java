package com.qa.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.qa.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ExtentLogger {
    private ExtentLogger() {
    }

    public static void pass(String pass)
    {

            ExtentManager.getExtentTest().pass(pass);
    }
    public static void fail(String fail)
    {
        ExtentManager.getExtentTest().fail(fail,MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotAsBase64()).build());
    }
    public static void skip(String skip)
    {
        ExtentManager.getExtentTest().skip(skip);
    }
    public static void info(String info)
    {
        ExtentManager.getExtentTest().info(info);
    }

    private static String getScreenshotAsBase64()
    {
        String screenshotAs = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        return screenshotAs;
    }
}
