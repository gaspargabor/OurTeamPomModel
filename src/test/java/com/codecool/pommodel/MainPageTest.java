package com.codecool.pommodel;

import com.codecool.pommodel.pom.Login;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainPageTest {
    
    public static WebDriver driver;
    
    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
    
    @Test
    public void openPage() {
        Login login = new Login(driver);
        
        login.login(System.getenv("name"), System.getenv("pass"));
        
        assertEquals("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa", driver.getCurrentUrl());
    }
    
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}