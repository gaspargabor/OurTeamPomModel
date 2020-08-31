import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.MainPage;
import com.codecool.pommodel.pom.UserProfile;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestCases {
    static Logger logger = LoggerFactory.getLogger(TestCases.class);

    public static WebDriver driver;
    
    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        logger.info("aopsjfmpas");
    }
    
    @Test
    public void loginWithValidCredentials() {
        Login login = new Login(driver);
        login.login(System.getenv("name"), System.getenv("pass"));
        UserProfile userProfile = new UserProfile(driver);
        assertEquals(System.getenv("name"), userProfile.getUserName().trim());
        MainPage mainPage = new MainPage(driver);
        mainPage.logOut();
    }

    @Test
    public void loginWithInvalidUsername(){
        Login login = new Login(driver);
        login.login("TestUser123", System.getenv("pass"));
        assertTrue(login.errorMessage());
    }

    @Test
    public void loginWithInvalidPassword(){
        Login login = new Login(driver);
        login.login(System.getenv("name"), "TestPassword123");
        assertTrue(login.errorMessage());
    }

    @Test
    public void captchaIsShown(){
        Login login = new Login(driver);
        login.login(System.getenv("name"), "TestPassword123");
        login.login(System.getenv("name"), "TestPassword123");
        login.login(System.getenv("name"), "TestPassword123");
        assertTrue(login.captcha());
    }
    
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}