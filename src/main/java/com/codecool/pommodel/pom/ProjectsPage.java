package com.codecool.pommodel.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectsPage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(id = "project-filter-text")
    WebElement searchField;

    public ProjectsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public void navigateToProjectsPage() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=all");
    }

    public void fillSearch(String name) {
        searchField.sendKeys(name);
    }
}
