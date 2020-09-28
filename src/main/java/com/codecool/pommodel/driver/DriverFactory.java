package com.codecool.pommodel.driver;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.impl.SimpleLogger;

import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.setProperty;

public class DriverFactory {
    
    private static WebDriver driver = null;
    
    public static WebDriver getDriver() {
        if (driver == null) setUpDriver();
        return driver;
    }
    
    private DriverFactory() {
    }
    
    private static void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO");
    
        try {
            driver = new RemoteWebDriver(new URL("https://selenium:CoolCanvas19.@seleniumhub.codecool.codecanvas.hu/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
    }
}
