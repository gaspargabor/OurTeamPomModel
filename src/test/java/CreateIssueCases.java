import com.codecool.pommodel.pom.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateIssueCases {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        Login login = new Login(driver);
        login.simpleLogin(System.getenv("name"), System.getenv("pass"));
    }

    @Test
    void createIssueTest() {
        String testText = "Issue Creation Test";

        CreateScreen createScreen = new CreateScreen(driver);
        createScreen.openUpEditor();
        createScreen.setSummary(testText);
        createScreen.createIssue();

        BrowsePage browsePage = new BrowsePage(driver);
        String popUpMessage = new MainPage(driver).getIdFromPopUp();

        browsePage.jumpToCreatedIssue(popUpMessage);
        assertEquals(testText, browsePage.assertIssue());
        browsePage.cleanUp();
    }

    @Test
    void createIssueWithoutTextTest() {
        CreateScreen createScreen = new CreateScreen(driver);
        createScreen.openUpEditor();
        createScreen.checkForSubmitBtn();
        createScreen.createIssue();
        assertEquals("You must specify a summary of the issue.", createScreen.getErrorMessage());
    }

    @Test
    void createTOUCANSubTaskTest() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage("https://jira.codecool.codecanvas.hu/browse/TOUCAN-571");
        CreateScreen createScreen = new CreateScreen(driver);
        createScreen.createSubTask();
    }

    @Test
    void createJETISubTaskTest() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage("https://jira.codecool.codecanvas.hu/browse/JETI-393");
        CreateScreen createScreen = new CreateScreen(driver);
        createScreen.createSubTask();
    }

    @Test
    void createCOALASubTaskTest() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage("https://jira.codecool.codecanvas.hu/browse/COALA-599");
        CreateScreen createScreen = new CreateScreen(driver);
        createScreen.createSubTask();
    }

    @Test
    void createIssueTypeTest() {
        CreateScreen createScreen = new CreateScreen(driver);
        createScreen.openUpEditor();
        createScreen.setIssueType("Story");
        createScreen.setIssueType("Task");
        createScreen.setIssueType("Bug");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
