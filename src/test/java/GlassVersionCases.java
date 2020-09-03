import com.codecool.pommodel.pom.GlassVersionPage;
import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.VersionPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GlassVersionCases {

    private static WebDriver driver;
    VersionPage versionPage = new VersionPage(driver);
    GlassVersionPage glassVersionPage = new GlassVersionPage(driver);

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
    public void glassVersionTest(){
        versionPage.createNewVersion();
        Assertions.assertTrue(glassVersionPage.checkIfVersionIsDisplayed());
        versionPage.deleteCreatedIssue();

    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
