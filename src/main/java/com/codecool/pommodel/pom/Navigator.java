package com.codecool.pommodel.pom;

import org.openqa.selenium.WebDriver;

public class Navigator {
    
    private static final String URL = "https://jira.codecool.codecanvas.hu/";
    WebDriver driver;
    
    public Navigator(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo(String page) {
        driver.navigate().to(URL + page);
    }
}
