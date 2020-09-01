import com.codecool.pommodel.pom.Login;
import com.codecool.pommodel.pom.ProjectsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectCases {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        Login login = new Login(driver);
        login.login(System.getenv("name"), System.getenv("pass"));
    }

    @Test
    public void fillSearch() {
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.navigateToProjectsPage();
        projectsPage.fillSearch("MTP");

    }



//    @AfterAll
//    public static void tearDown() {
//        driver.quit();
//    }
}
