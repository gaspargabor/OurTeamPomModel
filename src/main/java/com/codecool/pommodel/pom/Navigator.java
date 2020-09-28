package com.codecool.pommodel.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Navigator {
    
    private static final String URL = "https://jira.codecool.codecanvas.hu/";
    final WebDriver driver;
    final WebDriverWait wait;
    
    public Navigator(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    public void navigateTo(String page) {
        driver.navigate().to(URL + page);
        wait.until(ExpectedConditions.urlToBe(URL + page));
    }
}
