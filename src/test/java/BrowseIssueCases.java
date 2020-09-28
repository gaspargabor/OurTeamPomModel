import com.codecool.pommodel.driver.DriverFactory;
import com.codecool.pommodel.pom.BrowsePage;
import com.codecool.pommodel.pom.CreateScreen;
import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.MainPage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BrowseIssueCases {
    
    private static WebDriver driver;
    private static final String TEXT = "testing creating issue";
    
    @BeforeAll
    public static void setUp() {
        driver = DriverFactory.getDriver();
        new Login(driver).simpleLogin(System.getProperty("coolcanvasusername"), System.getProperty("coolcanvaspassword"));
    }
    
    @Test
    void browseIssue_issueBrowsableByKeyTest() {
        BrowsePage browsePage = new BrowsePage(driver);
        String createdMessage = new CreateScreen(driver).createIssueFromEditorScreen(TEXT);
        
        browsePage.jumpToCreatedIssue(createdMessage);
        
        assertEquals(TEXT, browsePage.assertIssue());
        
        browsePage.cleanUp();
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"COALA", "JETI", "TOUCAN"})
    void browseIssue_withID_oneTwoThreeTest(String projectName) {
        new MainPage(driver).driveToSearchSite();
        BrowsePage browsePage = new BrowsePage(driver);
        
        browsePage.orderByKeyASC(projectName);
        List<WebElement> result = browsePage.getFromList();
        
        assertEquals(3, result.size());
    }
    
    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
