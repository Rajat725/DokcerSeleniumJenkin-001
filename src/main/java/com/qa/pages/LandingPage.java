package com.qa.pages;

import com.qa.driver.DriverManager;
import com.qa.reports.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LandingPage {
    private static final Logger logger= LoggerFactory.getLogger(LandingPage.class);

    WebDriverWait wait;
    public LandingPage() {
        wait=new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(7));
    }

    public String getTitleOfPage() {
        final String title = DriverManager.getDriver().getTitle();
        ExtentLogger.pass("Title :: "+title);
        logger.info("Title :: {}",title);
        return title;
    }

    public String getURLOfPage() {
        final String currentUrl = DriverManager.getDriver().getCurrentUrl();
        ExtentLogger.pass("URL :: "+currentUrl);
        logger.info("URL :: {}",currentUrl);
        return currentUrl;
    }

    public int getFeaturedProductCount() {
        final List<WebElement> elements = DriverManager.getDriver().findElements(By.cssSelector(".product-layout"));
        ExtentLogger.pass("Total Count  :: "+elements.size());
        logger.info("Total Count  :: {}",elements.size());
        return elements.size();
    }

    public List<String> getAllLinksStatusCodes() {
        List<WebElement> elements = DriverManager.getDriver().findElements(By.tagName("a"));

        // Extract href attributes and filter out null or empty values
        List<String> urlList = elements.stream()
                .map(el -> el.getAttribute("href"))
                .filter(url -> url != null && !url.isEmpty())
                .collect(Collectors.toList());

        // Use thread-safe collection for parallel processing
        List<String> statusList = new ArrayList<>();

        urlList.stream().forEach(url -> {
            try {
                if(Objects.nonNull(url) && (!url.isEmpty()) && (!url.isBlank())){
                String statusString = getStatusCode(url);
                if(!statusString.isEmpty()) {
                    statusList.add(statusString);
                }
                }
            } catch (IOException e) {
                logger.error("Error fetching status for URL: {} {}",url,e.getMessage());

            }
        });

        return statusList;
    }

    private String getStatusCode(String url) throws IOException
    {
        HttpURLConnection connection = null;
        try {
            URL link = new URL(url);
            connection = (HttpURLConnection) link.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("HEAD");
            connection.connect();

            int responseCode = connection.getResponseCode();
            ExtentLogger.pass("LINK :: "+url+" : Status Code :: "+responseCode);
            logger.info("LINK :: {} Status Code :: {}",url,responseCode);
            if (responseCode > 400) {
            logger.error("URL {} :: Status Code :: {}" ,url,responseCode);
                return url + " :: Status Code :: " + responseCode;
            }
        } catch (IOException e) {
            throw new IOException("Failed to get status for URL: " + url, e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return ""; // Return empty string for valid URLs with no issues
    }


    public SignInPage navigateToSignInPage()
    {

        DriverManager.getDriver().findElement(By.cssSelector(".caret")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Login"))).click();
        ExtentLogger.pass("Navigated to Sign In Page");
        logger.info("Navigated to Sign In Page");
        return new SignInPage();
    }

}
