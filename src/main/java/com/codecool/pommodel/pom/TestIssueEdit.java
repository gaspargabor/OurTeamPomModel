package com.codecool.pommodel.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestIssueEdit {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "edit-issue")
    WebElement editBtn;

    @FindBy(id = "summary-val")
    WebElement summary;

    public TestIssueEdit(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    private void navigateToPage(){
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/MTP-1523");
    }

    public void clickEditButton(){
        wait.until(ExpectedConditions.visibilityOf(editBtn));
        editBtn.click();
    }

    public void openEditScreen(){
        navigateToPage();
        clickEditButton();
    };

    public String getSummary() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(summary));
        while (!summary.getText().equals("MTP_TEST_ISSUE_AFTER_EDIT")){
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        }
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/MTP-1523");
        wait.until(ExpectedConditions.visibilityOf(summary));
        return summary.getText();
    }

}
