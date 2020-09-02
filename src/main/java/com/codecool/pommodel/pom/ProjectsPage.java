package com.codecool.pommodel.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectsPage {
    
    private static final String URL = "https://jira.codecool.codecanvas.hu/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=all";
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "project-filter-text")
    WebElement searchField;

    @FindBy(xpath = "//*[@id='projects']//a[@href='/browse/MTP']")
    WebElement mtpLink;

    @FindBy(xpath = "//a[@href='/projects/MTP/summary']")
    WebElement mtpSummary;

    @FindBy(xpath = "//*[@id=\"summary-subnav-title\"]/span")
    WebElement activityTitle;

    public ProjectsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

<<<<<<< HEAD
    public void navigateToProjectsPage() {
        driver.navigate().to(URL);
=======
    public void navigateToProjectsPage(String url) {
        driver.navigate().to(url);
>>>>>>> 0fdcbf1655c119ddd7b498b443eb4f14b5a2b184
    }

    public void clickMTP() {
        mtpLink.click();
    }

    public void clickSummary() {
        mtpSummary.click();
    }

    public void fillSearch(String name) {
        searchField.sendKeys(name);
    }

    public Boolean checkSummary() {
        wait.until(ExpectedConditions.attributeToBe(activityTitle, "title", "Activity"));
        return activityTitle.isDisplayed();
    }
}
