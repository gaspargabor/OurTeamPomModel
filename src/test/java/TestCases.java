import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.UserProfile;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestCases {
    
    public static WebDriver driver;
    
    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
    
    @Test
    public void openPage() {
        Login login = new Login(driver);
        login.login(System.getenv("name"), System.getenv("pass"));
        UserProfile userProfile = new UserProfile(driver);
        Assertions.assertEquals(System.getenv("name"), userProfile.getUserName().trim());

        //assertEquals("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa", driver.getCurrentUrl());
    }
    
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}