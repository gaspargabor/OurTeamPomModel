package com.codecool.pommodel.pom;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.*;
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

    @FindBy(xpath = "//h1[@id='summary-val']//span")
    WebElement pencilIcon;

    @FindBy(id = "summary")
    WebElement bigSummaryField;

    public TestIssueEdit(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    private void navigateToPage(String string) {
        driver.navigate().to(string);
    }

    private void clickPencilIcon() {
        pencilIcon.click();
    }

    private void clearSummary() {
        bigSummaryField.clear();
    }

    private void fillSummary()  {
        bigSummaryField.sendKeys("MTP_TEST_ISSUE_AFTER_EDIT");
    }


    public void clickEditButton() {
        wait.until(ExpectedConditions.visibilityOf(editBtn));
        editBtn.click();
    }

    private void pressEnter() {
        bigSummaryField.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(summary));
    }

    public void openEditScreen() {
        navigateToPage("https://jira.codecool.codecanvas.hu/browse/MTP-1523");
        clickEditButton();
    }

    public void inlineEdit() {
        navigateToPage("https://jira.codecool.codecanvas.hu/browse/MTP-1523");
        wait.until(ExpectedConditions.visibilityOf(summary));
        clickPencilIcon();
        clearSummary();
        fillSummary();
        pressEnter();
    }

    public String getSummaryAfterInlineEdit() {
        while (!summary.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        }
        return summary.getText();
    }

    public String getSummary(String string) {
        while (!summary.getText().equals(string)) {
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        }
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/MTP-1523");
        wait.until(ExpectedConditions.visibilityOf(summary));
        return summary.getText();
    }

    public Boolean testEditOnYetiProject() {
        navigateToPage("https://jira.codecool.codecanvas.hu/browse/JETI-2");
        boolean present = false;
        try {
            present = editBtn.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Edit button is missing.");
        }
        return present;
    }

    public Boolean testEditOnToucanProject() {
        navigateToPage("https://jira.codecool.codecanvas.hu/browse/TOUCAN-124");
        boolean present = false;
        try {
            present = editBtn.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Edit button is missing.");
        }
        return present;
    }

    public Boolean testEditOnCoalaProject() {
        navigateToPage("https://jira.codecool.codecanvas.hu/browse/COALA-2");
        boolean present = false;
        try {
            present = editBtn.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Edit button is missing.");
        }
        return present;
    }

}
