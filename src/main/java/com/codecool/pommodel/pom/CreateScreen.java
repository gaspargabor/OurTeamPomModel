package com.codecool.pommodel.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateScreen {

    WebDriver driver;
    WebDriverWait wait;
    
    @FindBy(id = "create_link")
    WebElement createBtn;
    
    @FindBy(id = "summary")
    WebElement summaryField;
    
    @FindBy(id = "create-issue-submit")
    WebElement createScreenSubmitBtn;

    @FindBy(className = "error")
    WebElement errorMessage;

    @FindBy(id = "opsbar-operations_more")
    WebElement moreBtn;

    @FindBy(id = "create-subtask")
    WebElement createSubTask;

    @FindBy(id = "create-subtask-dialog")
    WebElement createSubTaskModal;

    @FindBy(id = "issuetype-field")
    WebElement issueTypeField;

    public CreateScreen(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 8);
        PageFactory.initElements(driver, this);
    }

    public void checkForSubmitBtn () {
        wait.until(ExpectedConditions.visibilityOf(createScreenSubmitBtn));
    }
    
    public String createIssueFromEditorScreen(String issueText) {
        openUpEditor();
        setSummary(issueText);
        createIssue();
        return new MainPage(driver).assertMessage();
    }
    
    public void openUpEditor() {
        wait.until(ExpectedConditions.visibilityOf(createBtn));
        createBtn.click();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public void createSubTask() {
        wait.until(ExpectedConditions.elementToBeClickable(moreBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(createSubTask)).click();
        wait.until(ExpectedConditions.visibilityOf(createSubTaskModal));
    }
    
    public void setSummary(String issueText) {
        wait.until(ExpectedConditions.visibilityOf(summaryField));
        summaryField.click();
        summaryField.sendKeys(Keys.DELETE);
        summaryField.sendKeys(issueText);
    }

    public void setIssueType() {
        wait.until(ExpectedConditions.elementToBeClickable(issueTypeField)).click();
    }
    
    public void createIssue() {
        createScreenSubmitBtn.click();
    }
}
