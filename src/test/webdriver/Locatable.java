package webdriver;

import org.openqa.selenium.WebElement;

public interface Locatable {

    WebElement getByRole(String role, String innerHtml);

    WebElement getByLabel(String label);

    WebElement getByText(String text);

    WebElement getByPlaceholder(String placeholder);

    WebElement getByDataId(String testId);

    WebElement getByCssSelector(String cssSelector);

    WebElement getByXpath(String xpath);
}
