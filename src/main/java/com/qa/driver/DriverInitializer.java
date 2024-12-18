package com.qa.driver;

import com.qa.enums.PropertyConfig;
import com.qa.utils.ReadPropertyFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;

public final class DriverInitializer {

    private DriverInitializer() {
    }

    public static void initBrowser(String browser) {

        if (ReadPropertyFile.getValue(PropertyConfig.SELENIUMGRIDENABLED).equalsIgnoreCase("false")) {
            initLocalBrowser(browser);
        } else {
            initRemoteBrowser(browser);
        }

        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        DriverManager.getDriver().manage().deleteAllCookies();
        DriverManager.getDriver().get(ReadPropertyFile.getValue(PropertyConfig.URL));


    }

    private static void initLocalBrowser(String browser) {
        if (Objects.isNull(DriverManager.getDriver())) {
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                DriverManager.setDriver(new ChromeDriver());
            } else if (browser.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                DriverManager.setDriver(new EdgeDriver());
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
    }

    private static void initRemoteBrowser(String browser) {

        if (Objects.isNull(DriverManager.getDriver())) {
            try {
                String urlFormat = ReadPropertyFile.getValue(PropertyConfig.SELENIUMGRIDURLFORMAT);
                String hub = ReadPropertyFile.getValue(PropertyConfig.SELENIUMGRIDHUB);
                String gridURL = String.format(urlFormat, hub);


                if (browser.equalsIgnoreCase("chrome")) {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    DriverManager.setDriver(new RemoteWebDriver(new URL(gridURL), chromeOptions));
                } else if (browser.equalsIgnoreCase("edge")) {
                    EdgeOptions edgeOptions = new EdgeOptions();
                    DriverManager.setDriver(new RemoteWebDriver(new URL(gridURL), edgeOptions));
                } else {
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid Selenium Grid URL: ", e);
            }
        }
    }

    public static void quitBrowser() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unsetDrivers(); // Clean up ThreadLocal
        }
    }
}
