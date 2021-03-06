package com.codecool.pommodel.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserProfile {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "up-d-username")
    WebElement username;
    
    private void navigateToUserProfile() {
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//meta[@name='ajs-remote-user']"), "content", System.getenv("name")));
        driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/ViewProfile.jspa");
    }

    public UserProfile(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }


    public String getUserName(){
        navigateToUserProfile();
        wait.until(ExpectedConditions.visibilityOf(username));
        return username.getAttribute("innerHTML");
    }
}
