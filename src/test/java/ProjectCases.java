import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.ProjectsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectCases {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        Login login = new Login(driver);
        login.simpleLogin(System.getenv("name"), System.getenv("pass"));
    }

    @Test
    void allProjectAreBrowsableTest() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage("https://jira.codecool.codecanvas.hu/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=all");
        projectsPage.fillSearch("MTP");
        projectsPage.clickMTP();
        projectsPage.clickSummary();
        assertTrue(projectsPage.checkSummary());
    }

    @Test
    void checkCOALAProjectTest() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage("https://jira.codecool.codecanvas.hu/projects/COALA/summary");
        assertTrue(projectsPage.checkSummary());
    }

    @Test
    void checkJETIProjectTest() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage("https://jira.codecool.codecanvas.hu/projects/JETI/summary");
        assertTrue(projectsPage.checkSummary());
    }

    @Test
    void checkTOUCANProjectTest() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage("https://jira.codecool.codecanvas.hu/projects/TOUCAN/summary");
        assertTrue(projectsPage.checkSummary());
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

}
