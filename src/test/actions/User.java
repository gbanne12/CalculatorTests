package actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConfigReader;
import webdriver.By;
import webdriver.FindElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.UUID;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class User implements UserAction, BrowserAction {

    private WebDriver driver;
    private WebDriverWait wait;

    private String chromeProfileDirectory;

    @Override
    public void openBrowser() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();

        String baseDirectory = System.getProperty("user.dir");
        var config = new ConfigReader();
        chromeProfileDirectory = (config.useAuthenticatedProfile()
                ? baseDirectory + config.getChromeProfileDirectory()
                : baseDirectory + "\\chromeprofile\\" + UUID.randomUUID());

        ChromeOptions userOptions = new ChromeOptions();
        userOptions.addArguments("--user-data-dir=" + chromeProfileDirectory);

        driver = new ChromeDriver(userOptions);

        wait = new WebDriverWait(driver, Duration.ofSeconds(2));

    }

    @Override
    public void closeBrowser() {
        driver.close();
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
    public void click(By locator) {
        WebElement element = findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        findElement(locator).click();
    }

    @Override
    public void fill(By locator, String input) {
        WebElement element = findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.sendKeys(input);
    }

    @Override
    public void select(By locator, String text) {
        WebElement list = findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(list));
        new Select(list).selectByVisibleText(text);
    }

    @Override
    public void check(By locator) {
        WebElement checkbox = findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @Override
    public void uncheck(By locator) {
        WebElement checkbox = findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @Override
    public void navigate(String url) {
        driver.get(url);
    }

    @Override
    public String readValue(By locator) {
        WebElement element = findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute("value");
    }

    @Override
    public Boolean canSee(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOf(findElement(locator)));
        } catch (NoSuchElementException | TimeoutException exception) {
            return false;
        }
        WebElement element = findElement(locator);
        return element.isDisplayed();
    }

    public Boolean cannotSee(By locator) {
        WebElement element;
        try {
            element = findElement(locator);
        } catch (NoSuchElementException exception) {
            return true;
        }
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void maxWaitTime(Duration duration) {
        wait.withTimeout(duration);
    }


    public WebElement findElement(By locator) {
        WebElement element = null;
        FindElement findElement = new FindElement(driver, wait);

        switch (locator.method()) {
            case BY_ROLE -> element = findElement.getByRole(locator.value(), locator.additionalValue());
            case BY_LABEL -> element = findElement.getByLabel(locator.value());
            case BY_TEXT -> element = findElement.getByText(locator.value());
            case BY_PLACEHOLDER -> element = findElement.getByPlaceholder(locator.value());
            case BY_TESTID -> element = findElement.getByDataId(locator.value());
            case BY_XPATH -> element = findElement.getByXpath(locator.value());
            case BY_CSS -> element = findElement.getByCssSelector(locator.value());
        }
        return element;
    }

    public WebElement filterElement(WebElement rootElement, By locator) {
        WebElement filteredElement = null;
        FindElement findElement = new FindElement(driver, wait);

        switch (locator.method()) {
            case BY_ROLE -> filteredElement = findElement.getByRole(locator.value(), locator.additionalValue());
            case BY_LABEL -> filteredElement = findElement.getByLabel(locator.value());
            case BY_TEXT -> filteredElement = findElement.getByText(locator.value());
            case BY_PLACEHOLDER -> filteredElement = findElement.getByPlaceholder(locator.value());
            case BY_TESTID -> filteredElement = findElement.getByDataId(locator.value());
            case BY_XPATH -> filteredElement = findElement.getByXpath(locator.value());
            case BY_CSS -> filteredElement = findElement.getByCssSelector(locator.value());
        }
        return filteredElement;
    }

    public String getChromeProfileDirectory() {
        return chromeProfileDirectory;
    }
}
