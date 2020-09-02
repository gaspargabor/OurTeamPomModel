package com.codecool.pommodel.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectSettingsPage {
    public static final String URL = "plugins/servlet/project-config/PP4/permissions";
    WebDriver driver;
    WebDriverWait wait;
    
    @FindBy(xpath = "//tr[@data-permission-key='BROWSE_PROJECTS']//dd")
    WebElement browseProjectPerm;
    
    @FindBy(xpath = "//tr[@data-permission-key='CREATE_ISSUES']//dd")
    WebElement createIssuePerm;
    
    @FindBy(xpath = "//tr[@data-permission-key='EDIT_ISSUES']//dd")
    WebElement editIssuePerm;
    
    public ProjectSettingsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }
    
    public String browseProjectPermission() {
        return browseProjectPerm.getText();
    }
    
    public String createIssuePermission() {
        return createIssuePerm.getText();}
    
    public String editIssuePermission() {
        return editIssuePerm.getText();}
    
}
