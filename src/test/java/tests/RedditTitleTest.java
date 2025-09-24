package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.RedditLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RedditTitleTest {

    private WebDriver driver;
    private RedditLoginPage loginPage;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.get("https://www.reddit.com/login/");
        loginPage = new RedditLoginPage(driver);
    }

    @Test
    public void testPageTitle() {
        String title = loginPage.getTitle();
        System.out.println("Page title: " + title);
        Assert.assertTrue(title.toLowerCase().contains("reddit"), "Title should contain 'reddit'");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
