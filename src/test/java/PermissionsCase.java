import com.codecool.pommodel.driver.DriverFactory;
import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.Navigator;
import com.codecool.pommodel.pom.ProjectGlassDocu;
import com.codecool.pommodel.pom.ProjectSettingsPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PermissionsCase {
    
    private static WebDriver driver;
    
    @BeforeAll
    public static void setUp() {
        driver = DriverFactory.getDriver();
    }
    
    @Test
    void comparePermissions_projectSettings_glassDocumentationTest() {
        Map<String, String> permissions = new HashMap<>();
        Navigator navigator = new Navigator(driver);
        
        new Login(driver).simpleLogin(System.getProperty("coolcanvasusername"), System.getProperty("coolcanvaspassword"));
        
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
