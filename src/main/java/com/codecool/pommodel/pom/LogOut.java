package com.codecool.pommodel.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOut {
    WebDriver driver;
    WebDriverWait wait;

    public LogOut(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")
    WebElement logOutTitle;

    public Boolean getLogOutTitle() {
        wait.until(ExpectedConditions.visibilityOf(logOutTitle));
        return logOutTitle.isDisplayed();
    }
}
