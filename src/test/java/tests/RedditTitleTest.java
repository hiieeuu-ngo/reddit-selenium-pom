package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RedditHomePage;

public class RedditTitleTest extends BaseTest {

    @Test
    public void testPageTitle() {
        RedditHomePage homePage = new RedditHomePage(driver);
        String title = homePage.getTitle();
        System.out.println("Page title: " + title);
        Assert.assertTrue(title.toLowerCase().contains("reddit"), "Title should contain 'reddit'");
    }
}

