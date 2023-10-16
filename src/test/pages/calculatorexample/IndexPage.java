package pages.calculatorexample;

import webdriver.By;

public class IndexPage {

    public static By CALCULATOR_LINK_LOCATOR = By.cssSelector("#gotoBasicCalc");

    public static By CALCULATOR_HEADER_LOCATOR = By.xpath("//div//h4[text()='Basic Calculator']");

    public static By CALCULATOR_CAPTION_LOCATOR = By.xpath(
            "//div/p[text()='A basic page to practice your test automation.']");
}
