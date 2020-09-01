import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CaptchaCase {

    private static WebDriver driver;


    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }


    @Test
    public void captchaIsShown() {
        Login login = new Login(driver);
        login.login(System.getenv("name"), System.getenv("pass"));
        MainPage mainPage = new MainPage(driver);
        mainPage.logOut();
        login.login(System.getenv("name"), "TestPassword123");
        login.errorMessage();
        login.login(System.getenv("name"), "TestPassword123");
        login.errorMessage();
        login.login(System.getenv("name"), "TestPassword123");
        assertTrue(login.captcha());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
