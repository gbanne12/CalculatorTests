package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import webdriver.By;

public class RoleTest extends BaseTest {

    @Test
    public void canSubmitFormWhenPopulated(TestInfo testInfo) {
        user.openBrowser();
        user.navigate("https://playwright.dev/java/docs/locators#locate-by-role");

        // find element based on explicit role defined on the element and text value
        user.click(By.role("button", "Java"));

        // find element using the role to find related element tags and then Aria-label value
        user.click(By.role(
                "button",
                "Switch between dark and light mode (currently dark mode)"));


    }


}
