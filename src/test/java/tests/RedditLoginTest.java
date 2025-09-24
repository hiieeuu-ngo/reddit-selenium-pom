package tests;

import org.testng.annotations.Test;
import pages.RedditHomePage;
import pages.RedditLoginPage;

public class RedditLoginTest extends BaseTest {

    @Test
    public void testLogin() {
        RedditHomePage homePage = new RedditHomePage(driver);
        homePage.clickLogin();

        RedditLoginPage loginPage = new RedditLoginPage(driver);
        loginPage.enterUsername("your_username");
        loginPage.enterPassword("your_password");
        loginPage.clickLoginButton();

        // TODO: Add assertion after login
    }
}
