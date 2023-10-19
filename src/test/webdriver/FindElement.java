package webdriver;

import elements.ElementTag;
import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


public class FindElement implements Locatable {

    private WebDriver driver;
    private WebDriverWait wait;

    public FindElement(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /***
     * Finds an element based on the role and accessible name
     * Will firstly look for a matching role attribute on the element, if none are found will attempt
     * to find a matching element by inferring the element tag as defined in  {@link elements.ElementTag AriaRole}
     * Roles are inferred as defined in the W3 spec https://w3c.github.io/html-aam/#html-element-role-mappings
     * Other roles can be passed in but if they do not match the role attribute no further location attempt
     * will be made.
     *
     * Found element list is filtered in order by text content, aria label then title to find one matching element
     *
     * @param role - where no explicit role is defined for the element the function will attempt to infer
     *             the following roles;
     * @param name - value that matches (in order) text content, aria-label, aria-labelledby title, or value of the element
     * @return - the first found matching element
     */
    @Override
    public WebElement getByRole(String role, String name) {
        boolean elementFound = false;
        WebElement matchedByName = null;

        // Try to find elements with the specified role attribute first
        List<WebElement> matchesBySpecifiedRole = findByRoleAttribute(role);
        if (matchesBySpecifiedRole != null) {
            matchedByName = filterByAccessibleName(matchesBySpecifiedRole, name);
            if (matchedByName != null) {
                elementFound = true;
                System.out.println("Matching element found by specified role :".concat(matchedByName.getTagName()));
            }
        }

        if (!elementFound) {
            // Try to find elements of a type implied by the role value
            List<WebElement> matchesByInferredRole = findByRolesAssociatedTags(role);
            if (matchesByInferredRole.size() > 0) {
                matchedByName = filterByAccessibleName(matchesByInferredRole, name);

                if (matchedByName != null) {
                    elementFound = true;
                    System.out.println("Matching element found by inferring from role :".concat(matchedByName.getTagName()));
                }
            }
        }

        if (elementFound) {
            return matchedByName;
        } else {
            throw new NoSuchElementException("No element found using getByRole selector: role - " + role + " , name - " + name);
        }
    }

    /***
     * Finds an element by its label.  Will firstly look for only a label element that matches the supplied text value.
     * If none are found, will then look for an element of any type with a matching aria-label value
     * @param label the text value of the label element or the aria label value
     * @return the first found matching element
     */
    @Override
    public WebElement getByLabel(String label) {
        WebElement elementToReturn;

        try {
            String xpathLabelSelector = "//label[text()='" + label + "']";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLabelSelector)));
            WebElement labelElement = driver.findElement(By.xpath(xpathLabelSelector));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(labelElement.getAttribute("for"))));
            elementToReturn = driver.findElement(By.id(labelElement.getAttribute("for")));

        } catch (NoSuchElementException | TimeoutException e) {
            String ariaLabelSelector = "[aria-label='" + label + "']";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ariaLabelSelector)));
            elementToReturn = driver.findElement(By.cssSelector(ariaLabelSelector));
        }
        return elementToReturn;
    }

    /***
     *  Finds an element of any type based on the text value
     * @param textValue the text value of the element
     * @return the first found matching element
     */
    @Override
    public WebElement getByText(String textValue) {
        String xpath = "//*[text()='" + textValue + "']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath));
    }

    /***
     *  Find an element of any type based on the placeholder value
     * @param placeholderValue the placeholder value of the element before it is focused
     * @return the first found web element
     */
    @Override
    public WebElement getByPlaceholder(String placeholderValue) {
        String cssPlaceholderSelector = "[placeholder='" + placeholderValue + "']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssPlaceholderSelector)));
        return driver.findElement(By.cssSelector(cssPlaceholderSelector));
    }

    /***
     * Finds an element of any type based on the data-id attribute
     * @param id the data-id value of the element
     * @return the first matching element found
     */
    @Override
    public WebElement getByDataId(String id) {
        String cssTestIdSelector = "[data-id='" + id + "']";
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

    private WebElement filterByAccessibleName(List<WebElement> elements, String name) {
        Optional<WebElement> matchingElement = elements.stream()
                .filter(element -> {
                    String text = element.getText();
                    String ariaLabel = element.getAttribute("aria-label");
                    String ariaLabelledBy = element.getAttribute("aria-labelledby");
                    String title = element.getAttribute("title");
                    String value = element.getAttribute("value");
                    return (text != null && text.equals(name)) ||
                            (ariaLabel != null && ariaLabel.equals(name)) ||
                            (ariaLabelledBy != null && ariaLabelledBy.equals(name)) ||
                            (title != null && title.equals(name)) ||
                            (value != null && value.equals(name));
                })
                .findFirst();

        return matchingElement.orElse(null);
    }

    private List<WebElement> findByRoleAttribute(String role) {
        List<WebElement> elements = null;

        String xpathRoleExpression = "//*[contains(@role, '" + role + "')]";

        // Look for elements with a role attribute that matches the role param
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRoleExpression)));
            elements = driver.findElements(By.xpath(xpathRoleExpression));
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("No matching roles found, will attempt to infer");
        }


        if (elements != null) {
            String elementSize = Integer.toString(elements.size());
            logElementInfo("Total elements found matching role = "
                    .concat(role)
                    .concat(" : ")
                    .concat(elementSize), elements);
        }

        return elements;
    }

    private List<WebElement> findByRolesAssociatedTags(String role) {
        // Look for element type which match the role value exactly
        List<WebElement> elementsWithInferredRole = new ArrayList<>();
        for (ElementTag tag : ElementTag.values()) {
            for (String tagRole : tag.getRoles()) {

                if (role.equalsIgnoreCase(tagRole)) {
                    String inputTypeSelector = "input[type='" + tagRole + "']";
                    String elementTagSelector = tag.toString().toLowerCase(Locale.ROOT);
                    String cssSelector = (tag == ElementTag.INPUT) ? inputTypeSelector : elementTagSelector;


                    elementsWithInferredRole.addAll(driver.findElements(By.cssSelector(cssSelector)));
                }
            }
        }


        if (elementsWithInferredRole.size() > 0) {
            String elementSize = Integer.toString(elementsWithInferredRole.size());
            logElementInfo("Total elements found by inferring match  from role = "
                    .concat(role)
                    .concat(" : ")
                    .concat(elementSize), elementsWithInferredRole);
        }


        return elementsWithInferredRole;
    }

    private void logElementInfo(String elementPurpose, List<WebElement> elements) {
        //log the elements
        System.out.println("---------------------------------");
        System.out.println(elementPurpose);
        System.out.println("---------------------------------");
        for (WebElement element : elements) {
            String newLine = System.getProperty("line.separator");
            String id = element.getAttribute("id") == null ? "" : element.getAttribute("id");
            String logInfo = "Element tag " + element.getTagName()
                    .concat(newLine)
                    .concat("Attribute 'id': " + id)
                    .concat(newLine)
                    .concat("Is Displayed: " + element.isDisplayed());

            System.out.println(logInfo);
        }
    }

}
