package com.codecool.pommodel.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;

public class VersionPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(name = "name")
    WebElement nameField;

    @FindBy(xpath = "//div[@class='releases-add__confirm']//button")
    WebElement addBtn;

    @FindBy(linkText = "Version: Test123")
    WebElement createdVersion;

    @FindBy(xpath = "//*[@id=\"versions-table\"]/tbody[2]/tr[1]/td[8]/div/a/span")
    WebElement dots;

    @FindBy(linkText = "Delete")
    WebElement deleteBtn;

    @FindBy(id = "submit")
    WebElement yesBtn;


    public VersionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    private void navigatoToPage(){
        driver.navigate().to("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP1/administer-versions?status=no-filter");
    }

    private void enterName(){
        wait.until(ExpectedConditions.visibilityOf(addBtn));
        nameField.sendKeys("Version: Test123");
    }

    private void clickAdd(){
        addBtn.click();
    }

    public void createNewVersion(){
        navigatoToPage();
        enterName();
        clickAdd();
    }

    public void deleteCreatedIssue(){
        navigatoToPage();
        clickOnDots();
        clickDelete();
        confirmDelete();
    }

    private void clickOnDots(){
        wait.until(ExpectedConditions.visibilityOf(createdVersion));
        dots.click();
    }

    private void clickDelete(){
        deleteBtn.click();
    }

    private void confirmDelete(){
        wait.until(ExpectedConditions.visibilityOf(yesBtn));
        yesBtn.click();
    }
}
