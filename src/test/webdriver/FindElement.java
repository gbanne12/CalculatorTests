package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FindElement implements Locatable {

    private WebDriver driver;
    private WebDriverWait wait;

    public FindElement(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Override
    public WebElement getByRole(String role, String innerHTML) {
        String xpathExpression = "//*[contains(@role, '" + role + "') and contains(text(), '" + innerHTML + "')]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
        return driver.findElement(By.xpath(xpathExpression));
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
}
