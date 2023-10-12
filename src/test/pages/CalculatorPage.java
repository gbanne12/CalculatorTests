package pages;

import webdriver.Locator;

/***
 * CSS locators for the Calculator Page
 * {@link <a href="https://testsheepnz.github.io/BasicCalculator.html">Calculator Page URL</a> }
 */
public class CalculatorPage {

 // public static Locator FIRST_NUMBER_LOCATOR = Locator.createByCss("input[name='number1']");
 public static Locator FIRST_NUMBER_LOCATOR = Locator.createByLabel("First number");

 public static Locator SECOND_NUMBER_LOCATOR = Locator.createByCss("input[name='number2']");

 public static Locator OPERATION_LIST_LOCATOR = Locator.createByCss("select[name='selectOperation']");

 public static Locator CALCULATE_BUTTON_LOCATOR = Locator.createByCss("#calculateButton");

 public static Locator ANSWER_FIELD_LOCATOR = Locator.createByCss("#numberAnswerField");

 public static Locator PAGE_HEADER_LOCATOR = Locator.createByCss("div .intro-heading");

}
