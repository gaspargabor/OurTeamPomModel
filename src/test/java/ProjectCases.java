import com.codecool.pommodel.driver.DriverFactory;
import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.ProjectsPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectCases {
    
    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        driver = DriverFactory.getDriver();
        new Login(driver).simpleLogin(System.getProperty("coolcanvasusername"), System.getProperty("coolcanvaspassword"));
    }

    @Test
    void allProjectAreBrowsableTest() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage(ProjectsPage.SEARCH_QUERY);
        projectsPage.fillSearch("MTP");
        projectsPage.clickMTP();
        projectsPage.clickSummary();
        assertTrue(projectsPage.checkSummary());
    }

    @Test
    void checkCOALAProjectTest() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage(ProjectsPage.URL + "/COALA/summary");
        assertTrue(projectsPage.checkSummary());
    }

    @Test
    void checkJETIProjectTest() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage(ProjectsPage.URL + "/JETI/summary");
        assertTrue(projectsPage.checkSummary());
    }

    @Test
    void checkTOUCANProjectTest() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage(ProjectsPage.URL + "/TOUCAN/summary");
        assertTrue(projectsPage.checkSummary());
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

}
