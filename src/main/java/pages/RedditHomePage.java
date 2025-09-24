package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RedditHomePage extends BasePage {

    @FindBy(xpath = "//a[contains(@href, '/login')]")
    private WebElement loginLink;

    public RedditHomePage(WebDriver driver) {
        super(driver);
    }

    public void clickLogin() {
        loginLink.click();
    }
}
