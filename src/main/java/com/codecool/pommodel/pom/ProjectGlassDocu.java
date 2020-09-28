package com.codecool.pommodel.pom;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectGlassDocu {
    
    public static final String URL = "projects/PP4?selectedItem=com.codecanvas.glass:glass";
    final WebDriverWait wait;
    
    @FindBy(xpath = "//*[@id='glass-permissions-panel']//th[3]//b")
    WebElement permissionText;
    
    @FindBy(xpath = "//tr[.//b[text()='Browse Projects']]/td[3]/div")
    WebElement browseProjectPermRow;
    
    @FindBy(xpath = "//tr[.//b[text()='Create Issues']]")
    WebElement createIssuePermRow;
    
    @FindBy(xpath = "//tr[.//b[text()='Edit Issues']]")
    WebElement editIssuePermRow;
    
    public ProjectGlassDocu(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }
    
    public String browseProjectPerm() {
        try {
            browseProjectPermRow.isDisplayed();
            return permissionText.getAttribute("innerHTML").split(":")[1];
        } catch (NoSuchElementException nse) {
            return "no";
        }
    }
    
    public String createIssuePerm() {
        try {
            createIssuePermRow.isDisplayed();
            return permissionText.getAttribute("innerHTML").split(":")[1];
        } catch (NoSuchElementException nse) {
            return "no";
        }
    }
    
    public String editIssuePerm() {
        try {
            editIssuePermRow.isDisplayed();
            return permissionText.getAttribute("innerHTML").split(":")[1];
        } catch (NoSuchElementException nse) {
            return "no";
        }
    }
}
