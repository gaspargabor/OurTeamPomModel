package com.codecool.pommodel.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "header-details-user-fullname")
    WebElement profilePictureBtn;

    @FindBy(id = "log_out")
    WebElement logOutBtn;
    
    @FindBy(id = "find_link")
    WebElement issuesBtn;
    
    @FindBy(id = "issues_new_search_link_lnk")
    WebElement searchForIssuesBtn;
    
    @FindBy(xpath = "//*[@id='aui-flag-container']//a")
    WebElement confirmPopUp;
    
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public void logOut(){
        wait.until(ExpectedConditions.visibilityOf(profilePictureBtn));
        profilePictureBtn.click();
        logOutBtn.click();
    }
    
    public String getIdFromPopUp() {
        return wait
                .until(ExpectedConditions.visibilityOf(confirmPopUp))
                .getAttribute("data-issue-key");
    }
    
    public void driveToSearchSite() {
        wait.until(ExpectedConditions.elementToBeClickable(issuesBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(searchForIssuesBtn)).click();
    }
}
