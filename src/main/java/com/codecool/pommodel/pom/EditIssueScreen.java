package com.codecool.pommodel.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EditIssueScreen {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "summary")
    WebElement summaryField;

    @FindBy(id = "edit-issue-submit")
    WebElement updateBtn;


    public EditIssueScreen(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    private void clearSummaryField() {
        wait.until(ExpectedConditions.visibilityOf(summaryField));
        summaryField.clear();
    }

    private void fillSummaryField(String string) {
        summaryField.sendKeys(string);
    }

    private void clickUpdateBtn() {
        summaryField.sendKeys(Keys.ENTER);
    }


    public void
    editSummaryField(String string) {
        clearSummaryField();
        fillSummaryField(string);
        clickUpdateBtn();
    }
}
