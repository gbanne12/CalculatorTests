package tests;


import actions.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

/***
 * Handle the setup and teardown actions for a test.
 * Makes a {@link User} object available to the test classes.
 * However, {@link tasks.Task} implementations should be preferred for performing actions
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
