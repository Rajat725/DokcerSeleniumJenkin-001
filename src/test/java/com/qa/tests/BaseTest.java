package com.qa.tests;

import com.qa.driver.DriverInitializer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {


    @Parameters("browser")
    @BeforeMethod
    public void initSetup(String browser)
    {

        DriverInitializer
                .initBrowser(browser);


    }


    @AfterMethod
    public void tearDown()
    {
        DriverInitializer.quitBrowser();
    }



}
