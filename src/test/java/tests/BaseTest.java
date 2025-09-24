package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest {

    protected WebDriver driver;

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
        driver.get("https://www.reddit.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
