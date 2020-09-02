import com.codecool.pommodel.pom.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.SimpleLogger;

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
    }
    
    @Test
    void browseIssue_issueBrowsableByKey() {
        String testText = "testing creating issue";
        
        new Login(driver).simpleLogin(System.getenv("name"), System.getenv("pass"));
        BrowsePage browsePage = new BrowsePage(driver);
        
        String createdMessage = new CreateScreen(driver).createIssueFromEditorScreen(testText);
        browsePage.jumpToCreatedIssue(createdMessage);
        
        assertEquals(testText, browsePage.assertIssue());
        
        browsePage.cleanUp();
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"COALA", "TOUCAN", "JETI"})
    void browseIssue_withID_oneTwoThree(String projectName) throws InterruptedException {
        logger.info("params: {}", projectName);
        
        new Login(driver).simpleLogin(System.getenv("name"), System.getenv("pass"));
        new MainPage(driver).driveToSearchSite();
        new BrowsePage(driver).orderByKeyASC(projectName);
        new BrowsePage(driver).getFromList();
    }
    
}
