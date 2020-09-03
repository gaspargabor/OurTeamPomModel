import com.codecool.pommodel.pom.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.SimpleLogger;
import java.util.List;

import static java.lang.System.setProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BrowseIssueCases {
    
    private static final Logger logger = LoggerFactory.getLogger(BrowseIssueCases.class);
    
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
        new Login(driver).simpleLogin(System.getenv("name"), System.getenv("pass"));
        logger.info("login");
    
    }
    
    @Test
    void browseIssue_issueBrowsableByKey() {
        String testText = "testing creating issue";
        
    //    new Login(driver).simpleLogin(System.getenv("name"), System.getenv("pass"));
        BrowsePage browsePage = new BrowsePage(driver);
        
        String createdMessage = new CreateScreen(driver).createIssueFromEditorScreen(testText);
        browsePage.jumpToCreatedIssue(createdMessage);
        
        assertEquals(testText, browsePage.assertIssue());
        
        browsePage.cleanUp();
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"COALA", "JETI", "TOUCAN"})
    void browseIssue_withID_oneTwoThree(String projectName) {
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
