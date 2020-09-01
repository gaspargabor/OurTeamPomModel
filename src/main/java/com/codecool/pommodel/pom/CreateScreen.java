package com.codecool.pommodel.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateScreen {
    
    Logger logger = LoggerFactory.getLogger(CreateScreen.class);
    WebDriver driver;
    WebDriverWait wait;
    
    @FindBy(id = "create_link")
    WebElement createBtn;
    
    @FindBy(id = "summary")
    WebElement summaryField;
    
    @FindBy(id = "create-issue-submit")
    WebElement createScreenSubmitBtn;
    
    @FindBy(id = "find_link")
    WebElement issuesBtn;
    
    
    @FindBy(id = "summary-val")
    WebElement issueSummaryText;
    
    @FindBy(xpath = ("//*[@id='issues_history_main']/ul/li[1]"))
    WebElement recentIssues;
    
    @FindBy(xpath = "//*[@id='aui-flag-container']/div/div")
    WebElement confirmPopUp;
    
    public CreateScreen(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }
    
    public String createIssueFromEditorScreen(String issueText) {
        logger.info("create issue started");
        openUpEditor();
        setSummary(issueText);
        createIssue();
        return assertMessage();
    }
    
    public void jumpToCreatedIssue() {
        issues();
        wait.until(ExpectedConditions.visibilityOf(recentIssues)).click();
    }
    
    public String assertIssue() {
        return wait.until(ExpectedConditions.visibilityOf(issueSummaryText)).getText();
    }
    
    private void issues() {
        issuesBtn.click();
    }
    
    private void openUpEditor() {
        createBtn.click();
    }
    
    private void setSummary(String issueText) {
        wait.until(ExpectedConditions.visibilityOf(summaryField));
        summaryField.click();
        summaryField.sendKeys(Keys.DELETE);
        summaryField.sendKeys(issueText);
    }
    
    private void createIssue() {
        createScreenSubmitBtn.click();
    }
    
    private String assertMessage() {
        return wait
                .until(ExpectedConditions.visibilityOf(confirmPopUp))
                .getText();
    }
}
