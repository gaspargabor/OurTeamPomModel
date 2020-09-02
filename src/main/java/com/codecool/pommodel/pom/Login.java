package com.codecool.pommodel.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

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
        this.wait = new WebDriverWait(driver, 8);
        PageFactory.initElements(driver, this);
    }

    public void simpleLogin(String name, String pass) {
     loginForLoginTests(name, pass);
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//meta[@name='ajs-remote-user']"), "content", System.getenv("name")));
    }

    public void loginForLoginTests(String name, String pass) {
        navigateToLogin();
        setUserName(name);
        setUserPass(pass);
        sendCredentials();
    }

    private void navigateToLogin() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.visibilityOf(userName));
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

    public Boolean errorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.isDisplayed();
    }

    public boolean captcha() {
        wait.until(ExpectedConditions.visibilityOf(captcha));
        return captcha.isDisplayed();
    }

}
