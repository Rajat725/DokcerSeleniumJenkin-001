package com.qa.reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {
    private ExtentManager() {
    }

    private static final ThreadLocal<ExtentTest> threadLocal=new ThreadLocal<>();

    static void setExtentTest(ExtentTest test)
    {
      threadLocal.set(test);


    }
    public static ExtentTest getExtentTest()
    {
        return threadLocal.get();
    }
    static void unloadExtentTests()
    {
        threadLocal.remove();
    }


}
