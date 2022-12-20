package tests;


import actions.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

/***
 * Handle the launching and closing of the browser only
 */

class BaseTest {

    User user = new User();

    @BeforeEach
    public void setup() {
        user.openBrowser();
    }

    @AfterEach
    public void teardown(TestInfo info) {
        user.takeScreenshot(info.getDisplayName());
        user.quitBrowser();
    }
}
