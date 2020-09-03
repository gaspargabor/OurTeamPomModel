package com.codecool.pommodel.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;

public class GlassVersionPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "aui-uid-2")
    WebElement versionsBtn;

    @FindBy(linkText = "Version: Test123")
    WebElement createdVersion;

    public GlassVersionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }


    private void clickVersions(){
        wait.until(ExpectedConditions.visibilityOf(versionsBtn));
        versionsBtn.click();
    }

    public Boolean checkIfVersionIsDisplayed(){
        navigateToPage();
        clickVersions();
        return createdVersion.isDisplayed();
    }




    private void navigateToPage(){
        driver.navigate().to("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass");
    }


}
