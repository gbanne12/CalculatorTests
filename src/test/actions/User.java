package actions;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class User implements UserAction {

    WebDriver driver;
    WebDriverWait wait;

    @Override
    public void openBrowser() {
        var waitTime = Duration.ofSeconds(5);
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
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
        driver.findElement(By.cssSelector(locator)).click();
    }

    @Override
    public void fill(String locator, String input) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
        WebElement element = driver.findElement(By.cssSelector(locator));
        element.click();
        element.sendKeys(input);
    }

    @Override
    public void select(String locator, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
        WebElement list = driver.findElement(By.cssSelector(locator));
        new Select(list).selectByVisibleText(text);
    }

    @Override
    public void check(String locator) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
        WebElement checkbox = driver.findElement(By.cssSelector(locator));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @Override
    public void uncheck(String locator) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
        WebElement checkbox = driver.findElement(By.cssSelector(locator));
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
        return driver.findElement(By.cssSelector(locator)).getAttribute("value");
    }

    @Override
    public Boolean canSee(String locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
        return driver.findElement(By.cssSelector(locator)).isDisplayed();
    }

}
