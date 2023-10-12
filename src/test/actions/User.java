package actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.FindElement;
import webdriver.Locator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class User implements UserAction, BrowserAction {

    WebDriver driver;
    WebDriverWait wait;

    @Override
    public void openBrowser() {
        var waitTime = Duration.ofSeconds(5);
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, waitTime);
    }

    @Override
    public void quitBrowser() {
        driver.quit();
    }

    @Override
    public void takeScreenshot(String filename) {
        try {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path target = Path.of("..\\CalculatorTests\\target\\test-classes\\" + filename + ".png");
            Files.copy(new FileInputStream(file), target, REPLACE_EXISTING);
        } catch (IOException exception) {
            System.out.println("Failed to take screenshot: " + exception);
        }
    }

    @Override
    public void click(Locator locator) {
        findElement(locator).click();
    }

    @Override
    public void fill(Locator locator, String input) {
        WebElement element = findElement(locator);
        element.click();
        element.sendKeys(input);
    }

    @Override
    public void select(Locator locator, String text) {
        WebElement list = findElement(locator);
        new Select(list).selectByVisibleText(text);
    }

    @Override
    public void check(Locator locator) {
        WebElement checkbox = findElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @Override
    public void uncheck(Locator locator) {
        WebElement checkbox = findElement(locator);
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @Override
    public void navigate(String url) {
        driver.get(url);
    }

    @Override
    public String readValue(Locator locator) {
        return findElement(locator).getAttribute("value");
    }

    @Override
    public Boolean canSee(Locator locator) {
        WebElement element = findElement(locator);
        return element.isDisplayed();
    }

    public WebElement findElement(Locator locator) {
        WebElement element = null;
        FindElement findElement = new FindElement(driver, wait);

        switch (locator.method()) {
            case BY_ROLE -> element = findElement.getByRole(locator.value(), locator.additionalValue());
            case BY_LABEL -> element = findElement.getByLabel(locator.value());
            case BY_TEXT -> element = findElement.getByText(locator.value());
            case BY_PLACEHOLDER -> element = findElement.getByPlaceholder(locator.value());
            case BY_TESTID -> element = findElement.getByTestId(locator.value());
            case BY_XPATH -> element = findElement.getByXpath(locator.value());
            case BY_CSS -> element = findElement.getByCssSelector(locator.value());
        }
        return element;
    }

    public WebElement filterElement(WebElement rootElement, Locator locator) {
        WebElement filteredElement = null;
        FindElement findElement = new FindElement(driver, wait);

        switch (locator.method()) {
            case BY_ROLE -> filteredElement = findElement.getByRole(locator.value(), locator.additionalValue());
            case BY_LABEL -> filteredElement = findElement.getByLabel(locator.value());
            case BY_TEXT -> filteredElement = findElement.getByText(locator.value());
            case BY_PLACEHOLDER -> filteredElement = findElement.getByPlaceholder(locator.value());
            case BY_TESTID -> filteredElement = findElement.getByTestId(locator.value());
            case BY_XPATH -> filteredElement = findElement.getByXpath(locator.value());
            case BY_CSS -> filteredElement = findElement.getByCssSelector(locator.value());
        }
        return filteredElement;
    }
}
