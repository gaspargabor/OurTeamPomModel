package com.codecool.pommodel.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserProfile {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "up-d-username")
    WebElement username;

    @FindBy(xpath = "//meta[@name='ajs-remote-user']")
    WebElement metaTag;
    
    private void navigateToUserProfile() {
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
