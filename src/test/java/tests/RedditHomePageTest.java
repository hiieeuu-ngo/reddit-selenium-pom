package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RedditHomePage;

public class RedditHomePageTest extends BaseTest {

    @Test
    public void testLoginLinkVisible() {
        RedditHomePage homePage = new RedditHomePage(driver);
        Assert.assertTrue(homePage.getTitle().toLowerCase().contains("reddit"));
    }
}
