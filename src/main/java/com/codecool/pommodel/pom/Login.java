package com.codecool.pommodel.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.codecool.pommodel.wait.WaitForPageLoad;


public class Login {
    
    private static final String URL = "https://jira.codecool.codecanvas.hu/login.jsp";
    final WebDriver driver;
    final WebDriverWait wait;
    
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
        this.wait = new WebDriverWait(driver, 60);
        PageFactory.initElements(driver, this);
    }
    
    public void simpleLogin(String name, String pass) {
        loginForLoginTests(name, pass);
        wait.until(ExpectedConditions.attributeToBe(metaTag, "content", System.getProperty("coolcanvasusername")));
    }
    
    public void loginForLoginTests(String name, String pass) {
        navigateToLogin();
        setUserName(name);
        setUserPass(pass);
        sendCredentials();
    }
    
    private void navigateToLogin() {
        driver.navigate().to(URL);
        wait.until(new WaitForPageLoad());
    }
    
    private void setUserName(String name) {
        wait.until(ExpectedConditions.visibilityOf(userName));
        userName.sendKeys(name);
    }
    
    private void setUserPass(String pass) {
        wait.until(ExpectedConditions.visibilityOf(userPass));
        userPass.sendKeys(pass);
    }
    
    private void sendCredentials() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitBtn.click();
    }
    
    public Boolean errorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.isDisplayed();
    }
    
    public boolean captcha() {
        wait.until(ExpectedConditions.visibilityOf(captcha));
        return captcha.isDisplayed();
    }
}
