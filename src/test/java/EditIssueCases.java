import com.codecool.pommodel.pom.EditIssueScreen;
import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.TestIssueEdit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditIssueCases {
    private static WebDriver driver;
    private TestIssueEdit testIssueEdit = new TestIssueEdit(driver);
    private EditIssueScreen editIssueScreen = new EditIssueScreen(driver);

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
    public void issueIsEditableTest() {
        testIssueEdit.openEditScreen();
        editIssueScreen.editSummaryField("MTP_TEST_ISSUE_AFTER_EDIT");
        assertEquals("MTP_TEST_ISSUE_AFTER_EDIT", testIssueEdit.getSummary("MTP_TEST_ISSUE_AFTER_EDIT"));
        testIssueEdit.clickEditButton();
        editIssueScreen.editSummaryField("MTP_TEST_ISSUE_BEFORE_EDIT");
        assertEquals("MTP_TEST_ISSUE_BEFORE_EDIT", testIssueEdit.getSummary("MTP_TEST_ISSUE_BEFORE_EDIT"));
    }

    @Test
    public void issueIsCancelableTest() {
        testIssueEdit.openEditScreen();
        editIssueScreen.changeSummaryAndCancelEdit("MTP_TEST_ISSUE_AFTER_EDIT");
        assertEquals("MTP_TEST_ISSUE_BEFORE_EDIT", testIssueEdit.getSummary("MTP_TEST_ISSUE_BEFORE_EDIT"));

    }

    @Test
    public void issueIsNotEditableWithEmptySummaryTest() {
        testIssueEdit.openEditScreen();
        editIssueScreen.changeSummaryToEmptyAndClickUpdate();
        assertTrue(editIssueScreen.errorMessageIsShown());
        editIssueScreen.cancelPopUp();
    }

    @Test
    public void editIssueOnYetiTest() {
        assertTrue(testIssueEdit.testEditOnYetiProject());
    }

    @Test
    public void editIssueOnCoalaTest() {
        assertTrue(testIssueEdit.testEditOnCoalaProject());
    }

    @Test
    public void editIssueOnToucanTest() {
        assertTrue(testIssueEdit.testEditOnToucanProject());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
