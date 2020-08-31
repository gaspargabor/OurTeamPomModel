package com.codecool.pommodel.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "header-details-user-fullname")
    WebElement profilePictureBtn;

    @FindBy(id = "log_out")
    WebElement logOutBtn;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public void logOut(){
        profilePictureBtn.click();
        logOutBtn.click();
    }
}
