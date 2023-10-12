package actions;

import elements.ElementFinder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.FindElement;
import webdriver.Locator;
import webdriver.LocatorMethod;

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
    public void click(String locator) {
        findElement(locator).click();
    }

    @Override
    public void fill(String locator, String input) {
        WebElement element = findElement(locator);
        element.click();
        element.sendKeys(input);
    }

    @Override
    public void select(String locator, String text) {
        WebElement list = findElement(locator);
        new Select(list).selectByVisibleText(text);
    }

    @Override
    public void check(String locator) {
        WebElement checkbox = findElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @Override
    public void uncheck(String locator) {
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
    public String readValue(String locator) {
        return findElement(locator).getAttribute("value");
    }

    @Override
    public Boolean canSee(String locator) {
        WebElement element = findElement(locator);
        return element.isDisplayed();
    }

    private WebElement findElement(String locator) {
        WebElement element = null;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
            element = driver.findElement(By.cssSelector(locator));
        } catch (TimeoutException e) {
            System.out.println("Could not find using CSS, assuming it is an xpath expression");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            element = driver.findElement(By.xpath(locator));
        }
        return element;
    }
}
