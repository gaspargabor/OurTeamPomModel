import com.codecool.pommodel.pom.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateIssueCases {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        Login login = new Login(driver);
        login.login(System.getenv("name"), System.getenv("pass"));
    }

    @Test
    public void createIssueTest() {
        String testText = "Issue Creation Test";

        CreateScreen createScreen = new CreateScreen(driver);
        createScreen.openUpEditor();
        createScreen.setSummary(testText);
        createScreen.createIssue();

        BrowsePage browsePage = new BrowsePage(driver);
        String alertPopup = new MainPage(driver).assertMessage();

        browsePage.jumpToCreatedIssue(alertPopup);
        assertEquals(testText, browsePage.assertIssue());
        browsePage.cleanUp();
    }

    @Test
    public void createIssueWithoutTextTest() {
        CreateScreen createScreen = new CreateScreen(driver);
        createScreen.openUpEditor();
        createScreen.checkForSubmitBtn();
        createScreen.createIssue();
        assertEquals("You must specify a summary of the issue.", createScreen.getErrorMessage());
    }

    @Test
    public void createTOUCANSubTaskTest() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage("https://jira.codecool.codecanvas.hu/browse/TOUCAN-571");
        CreateScreen createScreen = new CreateScreen(driver);
        createScreen.createSubTask();
    }

    @Test
    public void createJETISubTaskTest() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage("https://jira.codecool.codecanvas.hu/browse/JETI-393");
        CreateScreen createScreen = new CreateScreen(driver);
        createScreen.createSubTask();
    }

    @Test
    public void createCOALASubTaskTest() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage("https://jira.codecool.codecanvas.hu/browse/COALA-599");
        CreateScreen createScreen = new CreateScreen(driver);
        createScreen.createSubTask();
    }
}
