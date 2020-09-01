package com.codecool.pommodel.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

    private static final String URL = "https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa";
    WebDriver driver;
    WebDriverWait wait;
    
    @FindBy(xpath = "//meta[@name='ajs-remote-user']")
    WebElement metaTag;
    
    @FindBy(xpath = "//*[@id='login-form-username']")
    WebElement userName;
    
    @FindBy(xpath = "//*[@id='login-form-password']")
    WebElement userPass;
    
    @FindBy(xpath = "//*[@id='login']")
    WebElement submitBtn;

    @FindBy(id = "usernameerror")
    WebElement errorMessage;

    @FindBy(id = "captchaimg")
    WebElement captcha;
    
    @FindBy(id = "header-details-user-fullname")
    WebElement loggedInIcon;
    
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

        wait.until(ExpectedConditions.attributeToBe(metaTag, "content", System.getenv("name")));
    }

    private void navigateToLogin() {
        driver.navigate().to(URL);
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

    public Boolean errorMessage(){
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.isDisplayed();
    }

    public boolean captcha(){
        wait.until(ExpectedConditions.visibilityOf(captcha));
        return captcha.isDisplayed();
    }

}
