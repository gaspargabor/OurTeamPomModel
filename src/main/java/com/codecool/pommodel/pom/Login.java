package com.codecool.pommodel.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

    WebDriver driver;
    WebDriverWait wait;
    
    @FindBy(xpath = "//*[@id='login-form-username']")
    WebElement userName;
    
    @FindBy(xpath = "//*[@id='login-form-password']")
    WebElement userPass;
    
    @FindBy(xpath = "//*[@id='login-form-submit']")
    WebElement submitBtn;
    
    public Login(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }
    
    public void login(String name, String pass) {
        navigateToLogin();
        setUserName(name);
        setUserPass(pass);
        sendCredentials();
    }

    private void navigateToLogin() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/login.jsp");
    }
    
    private void setUserName(String name) {
        userName.sendKeys(name);
    }
    
    private void setUserPass(String pass) {
        userPass.sendKeys(pass);
    }
    
    private void sendCredentials() {
        submitBtn.click();
    }
}
