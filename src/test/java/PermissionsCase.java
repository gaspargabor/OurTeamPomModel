import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.Navigator;
import com.codecool.pommodel.pom.ProjectGlassDocu;
import com.codecool.pommodel.pom.ProjectSettingsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.SimpleLogger;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.setProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PermissionsCase {
    
    private static final Logger logger = LoggerFactory.getLogger(PermissionsCase.class);
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
    void comparePermissions_projectSettings_glassDocumentation() {
        Map<String, String> permissions = new HashMap<>();
        Navigator navigator = new Navigator(driver);
    
        new Login(driver).simpleLogin(System.getenv("name"), System.getenv("pass"));
        
        navigator.navigateTo(ProjectSettingsPage.URL);
        
        ProjectSettingsPage projectSettings = new ProjectSettingsPage(driver);
        
        permissions.put("browse project", projectSettings.browseProjectPermission());
        permissions.put("create issue", projectSettings.createIssuePermission());
        permissions.put("edit issue", projectSettings.editIssuePermission());
        
        navigator.navigateTo(ProjectGlassDocu.URL);
    
        ProjectGlassDocu projectGlassDocu = new ProjectGlassDocu(driver);
        
        assertEquals(permissions.get("browse project").trim(), projectGlassDocu.browseProjectPerm().trim());
        assertEquals(permissions.get("create issue").trim(), projectGlassDocu.createIssuePerm().trim());
        assertEquals(permissions.get("edit issue").trim(), projectGlassDocu.editIssuePerm().trim());
    }
}
