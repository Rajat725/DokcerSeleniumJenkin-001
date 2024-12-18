package com.qa.pages;

import com.qa.driver.DriverManager;
import com.qa.reports.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SignInPage {
    private static final Logger logger= LoggerFactory.getLogger(SignInPage.class);

    WebDriverWait wait;
    public SignInPage() {
        wait=new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(7));
    }

    public void enterUserEmail(String userEmail)
    {
        ExtentLogger.pass("Username  :: "+userEmail);
        logger.info("Username :: {}",userEmail);
        DriverManager.getDriver().findElement(By.id("input-email")).sendKeys(userEmail);
    }
    public void enterPassword(String passowrd)
    {
        ExtentLogger.pass("Password  :: "+passowrd);
        logger.info("Password :: {}",passowrd);
        DriverManager.getDriver().findElement(By.id("input-password")).sendKeys(passowrd);
    }

    public void clickLoginbtn()
    {
        ExtentLogger.pass("Clicked Login btn");
        logger.info("Clicked Login btn");
        DriverManager.getDriver().findElement(By.cssSelector("input[value='Login']")).click();
    }

    public String getErrorMessage()
    {
        final WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-danger.alert-dismissible")));
        final String text = el.getText();
        ExtentLogger.pass("Error Message Grabbed  :: "+text);
        logger.info("Error message is {}",text);
        return text;
    }


}
