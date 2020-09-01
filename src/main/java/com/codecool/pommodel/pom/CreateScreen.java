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

    public CreateScreen(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 8);
        PageFactory.initElements(driver, this);
    }
    
    public String createIssueFromEditorScreen(String issueText) {
        logger.info("create issue started");
        openUpEditor();
        setSummary(issueText);
        createIssue();
        return new MainPage(driver).assertMessage();
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
}
