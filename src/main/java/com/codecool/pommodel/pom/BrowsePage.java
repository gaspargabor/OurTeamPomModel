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

import java.util.List;
import java.util.stream.Collectors;

public class BrowsePage {
    
    public static final String URL = "https://jira.codecool.codecanvas.hu/browse/";
    private static final Logger logger = LoggerFactory.getLogger(BrowsePage.class);
    
    WebDriver driver;
    WebDriverWait wait;
    
    @FindBy(id = "opsbar-operations_more")
    WebElement moreBtn;
    
    @FindBy(id = "delete-issue")
    WebElement deleteBtn;
    
    @FindBy(id = "delete-issue-submit")
    WebElement deleteConfirmBtn;
    
    @FindBy(id = "summary-val")
    WebElement issueSummaryText;
    
    @FindBy(id = "advanced-search")
    WebElement searchField;
    
    @FindBy(xpath = "//li[@data-key]")
    List<WebElement> listItem;
    
    public BrowsePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 14);
        PageFactory.initElements(driver, this);
    }
    
    public void cleanUp() {
        wait.until(ExpectedConditions.elementToBeClickable(moreBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(deleteConfirmBtn)).click();
    }
    
    public void jumpToCreatedIssue(String issue) {
        driver.navigate().to(URL + issue);
    }

    public String assertIssue() {
        return wait.until(ExpectedConditions.visibilityOf(issueSummaryText)).getText();
    }
    
    public void orderByKeyASC(String projectName) {
        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.sendKeys("project =  \"" + projectName + "\" ORDER BY key ASC");
        searchField.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.urlContains("ASC"));
    }
    
    public List<WebElement> getFromList() {
        return listItem.parallelStream().limit(3).collect(Collectors.toList());
    }
}
