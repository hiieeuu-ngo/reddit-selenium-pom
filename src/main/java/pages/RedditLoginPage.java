package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RedditLoginPage extends BasePage {

    private final WebDriver driver;

    // Shadow DOM selectors
    private static final String USERNAME_PATH =
            "shreddit-app > auth-flow-manager > faceplate-partial > auth-flow-login > " +
            "faceplate-tabpanel > faceplate-form#login > auth-flow-modal > faceplate-text-input#login-username > input";

    private static final String PASSWORD_PATH =
            "shreddit-app > auth-flow-manager > faceplate-partial > auth-flow-login > " +
            "faceplate-tabpanel > faceplate-form#login > auth-flow-modal > faceplate-text-input#login-password > input";

    private static final String LOGIN_BUTTON_PATH =
            "shreddit-app > auth-flow-manager > faceplate-partial > auth-flow-login > " +
            "faceplate-tabpanel > faceplate-form#login > auth-flow-modal > div.w-100 > faceplate-tracker";

    public RedditLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Utility to pierce through shadow roots step by step.
     */
    private WebElement getShadowElement(String selectorPath) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script =
                "const selectors = arguments[0].split('>');" +
                "let el = document.querySelector(selectors[0].trim());" +
                "for (let i = 1; i < selectors.length; i++) {" +
                "  el = el.shadowRoot ? el.shadowRoot.querySelector(selectors[i].trim()) : el.querySelector(selectors[i].trim());" +
                "  if (!el) return null;" +
                "}" +
                "return el;";
        WebElement element = (WebElement) js.executeScript(script, selectorPath);
        if (element == null) {
            throw new RuntimeException("Could not find shadow element: " + selectorPath);
        }
        return element;
    }

    // Page actions
    public void enterUsername(String username) {
        WebElement usernameField = getShadowElement(USERNAME_PATH);
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordField = getShadowElement(PASSWORD_PATH);
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = getShadowElement(LOGIN_BUTTON_PATH);
        loginButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}

