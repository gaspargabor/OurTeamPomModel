import com.codecool.pommodel.pom.LogOut;
import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.MainPage;
import com.codecool.pommodel.pom.UserProfile;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.SimpleLogger;
import static java.lang.System.setProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginCases {
    private static final Logger logger = LoggerFactory.getLogger(LoginCases.class);

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");

        logger.info("test started");
    }

    @Test
    public void loginWithValidCredentials() {
        Login login = new Login(driver);
        login.login(System.getenv("name"), System.getenv("pass"));
        UserProfile userProfile = new UserProfile(driver);
        assertEquals(System.getenv("name"), userProfile.getUserName().trim());
    }

    @Test
    public void logOut() {
        Login login = new Login(driver);
        login.login(System.getenv("name"), System.getenv("pass"));
        MainPage mainPage = new MainPage(driver);
        mainPage.logOut();
        LogOut logOut = new LogOut(driver);
        assertTrue(logOut.getLogOutTitle());
    }

    @Test
    public void loginWithInvalidUsername() {
        Login login = new Login(driver);
        login.login("TestUser123", System.getenv("pass"));
        assertTrue(login.errorMessage());
    }

    @Test
    public void loginWithInvalidPassword() {
        Login login = new Login(driver);
        login.login(System.getenv("name"), "TestPassword123");
        assertTrue(login.errorMessage());
    }

    @Test
    public void loginWithEmptyCredentials() {
        Login login = new Login(driver);
        login.login("", "");
        assertTrue(login.errorMessage());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}