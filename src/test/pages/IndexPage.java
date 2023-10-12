package pages;

import webdriver.Locator;

public class IndexPage {

    public static Locator CALCULATOR_LINK_LOCATOR = Locator.createByCss("#gotoBasicCalc");

    public static Locator CALCULATOR_HEADER_LOCATOR = Locator.createByXpath("//div//h4[text()='Basic Calculator']");

    public static Locator CALCULATOR_CAPTION_LOCATOR = Locator.createByXpath(
            "//div/p[text()='A basic page to practice your test automation.']");
}
