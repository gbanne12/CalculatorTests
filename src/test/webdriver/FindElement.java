package webdriver;

import elements.AriaRole;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;


public class FindElement implements Locatable {

    private WebDriver driver;
    private WebDriverWait wait;

    public FindElement(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /***
     * Find an element based on the role and accessible name
     * Will firstly look for a matching role attribute on the element, if none are found will attempt
     * to find a matching element by inferring the role for elements defined in  {@link elements.AriaRole AriaRole}
     * Roles are inferred as defined in the W3 spec https://w3c.github.io/html-aam/#html-element-role-mappings
     * Other roles can be passed in but if they do not match the role attribute no further location attempt
     * will be made.
     *
     * Found element list is filtered in order by text content, aria label then title to find one matching element
     *
     * @param role - where no explicit role is defined for the element the function will attempt to infer
     *             the following roles;
     * @param name - value that matches (in order) text content, aria-label or title of the element
     * @return
     */
    @Override
    public WebElement getByRole(String role, String name) {
        WebElement matchingElement = null;

        String xpathRoleExpression = "//*[contains(@role, '" + role + "')]";

        // Look for elements with a specified role attribute that matches the role param
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRoleExpression)));
            List<WebElement> elementsWithRoleAttribute = driver.findElements(By.xpath(xpathRoleExpression));
            matchingElement = filterByAccessibleName(elementsWithRoleAttribute, name);

        } catch (NoSuchElementException e) {
            System.out.println("No matching roles found, will attempt to infer");
        }

        if (matchingElement != null) {
            return matchingElement;
        } else {
            for (AriaRole ariaRole : AriaRole.values()) {
                if (ariaRole.getValue().equalsIgnoreCase(role)) {
                    matchingElement = getByRole(ariaRole, name);
                }
            }
        }

        if (matchingElement == null) {
            throw new NoSuchElementException("No matching role attribute was found or able to be inferred");
        } else {
            return matchingElement;
        }
    }

    @Override
    public WebElement getByLabel(String label) {
        String xpathLabelSelector = "//label[text()='" + label + "']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLabelSelector)));
        WebElement labelElement = driver.findElement(By.xpath(xpathLabelSelector));


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(labelElement.getAttribute("for"))));
        return driver.findElement(By.id(labelElement.getAttribute("for")));
    }

    @Override
    public WebElement getByText(String textValue) {
        String xpath = "//*[text()='" + textValue + "']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath));
    }

    @Override
    public WebElement getByPlaceholder(String placeholderValue) {
        String cssPlaceholderSelector = "[placeholder='" + placeholderValue + "']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssPlaceholderSelector)));
        return driver.findElement(By.cssSelector(cssPlaceholderSelector));
    }

    @Override
    public WebElement getByTestId(String testId) {
        String cssTestIdSelector = "[data-testid='" + testId + "']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssTestIdSelector)));
        return driver.findElement(By.cssSelector(cssTestIdSelector));
    }

    @Override
    public WebElement getByCssSelector(String cssSelector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
        return driver.findElement(By.cssSelector(cssSelector));
    }

    @Override
    public WebElement getByXpath(String xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath));
    }


    private WebElement getByRole(AriaRole role, String name) {
        WebElement matchingElement = null;

        String roleString = role.getValue();

        // Look for elements with a specified role attribute that matches the role param
        try {
            List<WebElement> elementsOfType = driver.findElements(By.cssSelector(roleString));
            matchingElement = filterByAccessibleName(elementsOfType, name);

        } catch (NoSuchElementException e) {
            System.out.println("No elements of the type were found on the page");
        }

        if (matchingElement != null) {
            return matchingElement;
        }

        return null;
    }

    private WebElement filterByAccessibleName(List<WebElement> elements, String name) {
        Optional<WebElement> matchingElement = elements.stream()
                .filter(element -> {
                    String text = element.getText();
                    String ariaLabel = element.getAttribute("aria-label");
                    String title = element.getAttribute("title");
                    return (text != null && text.equals(name)) ||
                            (ariaLabel != null && ariaLabel.equals(name)) ||
                            (title != null && title.equals(name));
                })
                .findFirst();

        return matchingElement.orElse(null);
    }

}
