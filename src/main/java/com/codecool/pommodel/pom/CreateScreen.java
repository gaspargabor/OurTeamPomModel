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
    
    @FindBy(id = "quicksearch-menu")
    WebElement quickSearchField;

    @FindBy(id = "opsbar-operations_more")
    WebElement moreBtn;
    
    @FindBy(id = "delete-issue")
    WebElement deleteBtn;
    
    @FindBy(id = "delete-issue-submit")
    WebElement deleteConfirmBtn;
    
    @FindBy(xpath = "//*[@id='aui-flag-container']//a")
    WebElement confirmPopUp;
    
    
    
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
        return assertMessage();
    }
    
    public void jumpToCreatedIssue(String issue) {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/" + issue);
    }
    
    public void cleanUp() {
        wait.until(ExpectedConditions.elementToBeClickable(moreBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(deleteConfirmBtn)).click();
        
    }
    
    public String assertIssue() {
        return wait.until(ExpectedConditions.visibilityOf(issueSummaryText)).getText();
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
                .getAttribute("data-issue-key");
    }
}
