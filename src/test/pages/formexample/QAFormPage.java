package pages.formexample;

import webdriver.By;

public class QAFormPage {

    public static By NAME_TEXT_BOX_LOCATOR = By.placeholder("Name");
    public static By MESSAGE_TEXT_BOX_LOCATOR = By.placeholder("Message");
    public static By SUBMIT_BUTTON_LOCATOR = By.cssSelector("button[type='submit']");

    public static By CONFIRMATION_MESSAGE_LOCATOR = By.text("Thanks for contacting us");

    public static By ERROR_MESSAGE_LOCATOR = By.text("Make sure you fill in all required fields.");


}
