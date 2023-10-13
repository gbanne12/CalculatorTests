package tests;

import elements.AriaRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import webdriver.Locator;

public class RoleTest extends BaseTest {

    @Test
    public void canSubmitFormWhenPopulated(TestInfo testInfo) {
        user.openBrowser();
        user.navigate("https://playwright.dev/java/docs/locators#locate-by-role");

        // find element based on explicit role match and text value
        user.click(Locator.createByRole(AriaRole.BUTTON.getValue(), "Java"));

        // find element based on implicit role match and Aria-label value
        user.click(Locator.createByRole(
                "button",
                "Switch between dark and light mode (currently dark mode)"));


    }


}
