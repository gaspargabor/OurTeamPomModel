import com.codecool.pommodel.driver.DriverFactory;
import com.codecool.pommodel.pom.LogOut;
import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.MainPage;
import com.codecool.pommodel.pom.UserProfile;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginCases {
    
    private static WebDriver driver;
    private static Login login;
    
    @BeforeAll
    public static void setUp() {
        driver = DriverFactory.getDriver();
        login = new Login(driver);
    }
    
    @Test
    void loginWithValidCredentialsTest() {
        login.simpleLogin(System.getProperty("coolcanvasusername"), System.getProperty("coolcanvaspassword"));
        UserProfile userProfile = new UserProfile(driver);
        assertEquals(System.getProperty("coolcanvasusername"), userProfile.getUserName().trim());
        MainPage mainPage = new MainPage(driver);
        mainPage.logOut();
    }
    
    @Test
    void logOutTest() {
        login.loginForLoginTests(System.getProperty("coolcanvasusername"), System.getProperty("coolcanvaspassword"));
        MainPage mainPage = new MainPage(driver);
        mainPage.logOut();
        LogOut logOut = new LogOut(driver);
        assertTrue(logOut.getLogOutTitle());
    }
    
    @Test
    void loginWithInvalidUsernameTest() {
        login.loginForLoginTests("TestUser123", System.getProperty("coolcanvaspassword"));
        assertTrue(login.errorMessage());
    }
    
    @Test
    void loginWithInvalidPasswordTest() {
        login.loginForLoginTests(System.getProperty("coolcanvasusername"), "TestPassword123");
        assertTrue(login.errorMessage());
    }
    
    @Test
    void loginWithEmptyCredentialsTest() {
        login.loginForLoginTests("", "");
        assertTrue(login.errorMessage());
    }
    
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}