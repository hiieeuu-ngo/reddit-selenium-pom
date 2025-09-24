package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RedditLoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public RedditLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /** 
     * Generic shadow DOM element getter with null checks.
     * Usage: getShadowElement("shreddit-app > auth-flow-manager > ... > input#login-username");
     */
    public WebElement getShadowElement(String selectorChain) {
        // Wait for the top-level shadow host
        wait.until((ExpectedCondition<Boolean>) d ->
                ((JavascriptExecutor) d)
                        .executeScript("return document.querySelector(arguments[0]) != null", selectorChain.split(">")[0].trim())
                        .equals(Boolean.TRUE)
        );

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement el = (WebElement) js.executeScript(
                """
                const selectors = arguments[0].split('>');
                let el = document.querySelector(selectors[0].trim());
                for (let i = 1; i < selectors.length; i++) {
                    if (!el) return null;
                    el = el.shadowRoot ? el.shadowRoot.querySelector(selectors[i].trim()) : el.querySelector(selectors[i].trim());
                }
                return el;
                """, selectorChain
        );

        if (el == null) {
            throw new RuntimeException("Could not find shadow element: " + selectorChain);
        }
        return el;
    }

    // --- Page Elements ---
    public WebElement getUsernameField() {
        return getShadowElement(
                "shreddit-app > auth-flow-manager > faceplate-partial > auth-flow-login > faceplate-tabpanel > faceplate-form#login > auth-flow-modal > faceplate-text-input#login-username > input"
        );
    }

    public WebElement getPasswordField() {
        return getShadowElement(
                "shreddit-app > auth-flow-manager > faceplate-partial > auth-flow-login > faceplate-tabpanel > faceplate-form#login > auth-flow-modal > faceplate-text-input#login-password > input"
        );
    }

    public WebElement getLoginButton() {
        return getShadowElement(
                "shreddit-app > auth-flow-manager > faceplate-partial > auth-flow-login > faceplate-tabpanel > faceplate-form#login > auth-flow-modal > div.w-100 > faceplate-tracker"
        );
    }

    // --- Page Actions ---
    public void enterUsername(String username) {
        WebElement el = getUsernameField();
        el.clear();
        el.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement el = getPasswordField();
        el.clear();
        el.sendKeys(password);
    }

    public void clickLogin() {
        WebElement el = getLoginButton();
        el.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
