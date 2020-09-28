package com.codecool.pommodel.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditIssueScreen {
    final WebDriver driver;
    final WebDriverWait wait;

    @FindBy(id = "summary")
    WebElement summaryField;

    @FindBy(id = "edit-issue-submit")
    WebElement updateBtn;

    @FindBy(linkText = "Cancel")
    WebElement cancelBtn;

    @FindBy(className = "error")
    WebElement errorMessage;

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
        updateBtn.click();
    }

    private void clickCancelBtn(){
        cancelBtn.click();
    }

    private void acceptPopUp(){
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void changeSummaryAndCancelEdit(String string){
        clearSummaryField();
        fillSummaryField(string);
        clickCancelBtn();
        acceptPopUp();
    }

    public void changeSummaryToEmptyAndClickUpdate(){
        clearSummaryField();
        clickUpdateBtn();
    }

    public Boolean errorMessageIsShown(){
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.isDisplayed();
    }

    public void cancelPopUp(){
        clickCancelBtn();
        acceptPopUp();
    }


    public void editSummaryField(String string) {
        clearSummaryField();
        fillSummaryField(string);
        clickUpdateBtn();
    }
}
