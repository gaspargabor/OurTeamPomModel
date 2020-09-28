package com.codecool.pommodel.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOut {

    @FindBy(className = "title")
    WebElement logOutTitle;

    final WebDriverWait wait;
    
    public LogOut(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public Boolean getLogOutTitle() {
        wait.until(ExpectedConditions.visibilityOf(logOutTitle));
        return logOutTitle.isDisplayed();
    }
}
