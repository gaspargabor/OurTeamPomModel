import com.codecool.pommodel.driver.DriverFactory;
import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CaptchaCases {

    private static WebDriver driver;
    
    @BeforeAll
    public static void setUp() {
       driver = DriverFactory.getDriver();
    }
    
    @Test
    void captchaIsShownTest() {
        Login login = new Login(driver);
        login.simpleLogin(System.getProperty("coolcanvasusername"), System.getProperty("coolcanvaspassword"));
        MainPage mainPage = new MainPage(driver);
        mainPage.logOut();
        login.simpleLogin(System.getProperty("coolcanvasusername"), "TestPassword123");
        login.errorMessage();
        login.simpleLogin(System.getProperty("coolcanvasusername"), "TestPassword123");
        login.errorMessage();
        login.simpleLogin(System.getProperty("coolcanvasusername"), "TestPassword123");
        assertTrue(login.captcha());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
