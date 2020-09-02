import com.codecool.pommodel.pom.EditIssueScreen;
import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.TestIssueEdit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
    public void issueIsEditable() throws InterruptedException {
        testIssueEdit.openEditScreen();
        editIssueScreen.editSummaryField("MTP_TEST_ISSUE_AFTER_EDIT");
        assertEquals("MTP_TEST_ISSUE_AFTER_EDIT", testIssueEdit.getSummary("MTP_TEST_ISSUE_AFTER_EDIT"));
        testIssueEdit.clickEditButton();
        editIssueScreen.editSummaryField("MTP_TEST_ISSUE_BEFORE_EDIT");

    }

    @Test
    public void issueIsCancelable() {
        testIssueEdit.openEditScreen();
        editIssueScreen.changeSummaryAndCancelEdit("MTP_TEST_ISSUE_AFTER_EDIT");
        assertEquals("MTP_TEST_ISSUE_BEFORE_EDIT", testIssueEdit.getSummary("MTP_TEST_ISSUE_BEFORE_EDIT"));

    }

    @Test
    public void issueIsNotEditableWithEmptySummary() {
        testIssueEdit.openEditScreen();
        editIssueScreen.changeSummaryToEmptyAndClickUpdate();
        assertTrue(editIssueScreen.errorMessageIsShown());
    }

    @Test
    public void editIssueOnYeti() {
        assertTrue(testIssueEdit.testEditOnYetiProject());
    }

    @Test
    public void editIssueOnCoala() {
        assertTrue(testIssueEdit.testEditOnCoalaProject());
    }

    @Test
    public void editIssueOnToucan() {
        assertTrue(testIssueEdit.testEditOnToucanProject());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
