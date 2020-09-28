import com.codecool.pommodel.driver.DriverFactory;
import com.codecool.pommodel.pom.EditIssueScreen;
import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.TestIssueEdit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditIssueCases {
    private static WebDriver driver;
    private final TestIssueEdit testIssueEdit = new TestIssueEdit(driver);
    private final EditIssueScreen editIssueScreen = new EditIssueScreen(driver);

    @BeforeAll
    public static void setUp() {
        driver = DriverFactory.getDriver();

        Login login = new Login(driver);
        login.simpleLogin(System.getProperty("coolcanvasusername"), System.getProperty("coolcanvaspassword"));
    }

    @Test
    void issueIsEditableTest() {
        testIssueEdit.openEditScreen();
        editIssueScreen.editSummaryField("MTP_TEST_ISSUE_AFTER_EDIT");
        assertEquals("MTP_TEST_ISSUE_AFTER_EDIT", testIssueEdit.getSummary("MTP_TEST_ISSUE_AFTER_EDIT"));
        testIssueEdit.clickEditButton();
        editIssueScreen.editSummaryField("MTP_TEST_ISSUE_BEFORE_EDIT");
        assertEquals("MTP_TEST_ISSUE_BEFORE_EDIT", testIssueEdit.getSummary("MTP_TEST_ISSUE_BEFORE_EDIT"));
    }

    @Test
    void issueIsCancelableTest() {
        testIssueEdit.openEditScreen();
        editIssueScreen.changeSummaryAndCancelEdit("MTP_TEST_ISSUE_AFTER_EDIT");
        assertEquals("MTP_TEST_ISSUE_BEFORE_EDIT", testIssueEdit.getSummary("MTP_TEST_ISSUE_BEFORE_EDIT"));

    }

    @Test
    void issueIsNotEditableWithEmptySummaryTest() {
        testIssueEdit.openEditScreen();
        editIssueScreen.changeSummaryToEmptyAndClickUpdate();
        assertTrue(editIssueScreen.errorMessageIsShown());
        editIssueScreen.cancelPopUp();
    }

    @Test
    void editIssueOnYetiTest() {
        assertTrue(testIssueEdit.testEditOnYetiProject());
    }

    @Test
    void editIssueOnCoalaTest() {
        assertTrue(testIssueEdit.testEditOnCoalaProject());
    }

    @Test
    void editIssueOnToucanTest() {
        assertTrue(testIssueEdit.testEditOnToucanProject());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
