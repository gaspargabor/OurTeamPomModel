import com.codecool.pommodel.pom.EditIssueScreen;
import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.TestIssueEdit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditIssueCases {
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
    public void voidIssueIsEditable(){
        TestIssueEdit testIssueEdit = new TestIssueEdit(driver);
        testIssueEdit.openEditScreen();
        EditIssueScreen editIssueScreen = new EditIssueScreen(driver);
        editIssueScreen.editSummaryField("MTP_TEST_ISSUE_AFTER_EDIT");
        assertEquals(testIssueEdit.getSummary(), "MTP_TEST_ISSUE_AFTER_EDIT");
        testIssueEdit.clickEditButton();
        editIssueScreen.editSummaryField("MTP_TEST_ISSUE_BEFORE_EDIT");

    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
