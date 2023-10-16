package pages.calculatorexample;

import webdriver.By;

/***
 * CSS locators for the Calculator Page
 * {@link <a href="https://testsheepnz.github.io/BasicCalculator.html">Calculator Page URL</a> }
 */
public class CalculatorPage {

 // public static Locator FIRST_NUMBER_LOCATOR = Locator.createByCss("input[name='number1']");
 public static By FIRST_NUMBER_LOCATOR = By.label("First number");

 public static By SECOND_NUMBER_LOCATOR = By.cssSelector("input[name='number2']");

 public static By OPERATION_LIST_LOCATOR = By.cssSelector("select[name='selectOperation']");

 public static By CALCULATE_BUTTON_LOCATOR = By.cssSelector("#calculateButton");

 public static By ANSWER_FIELD_LOCATOR = By.cssSelector("#numberAnswerField");

 public static By PAGE_HEADER_LOCATOR = By.cssSelector("div .intro-heading");

}
