package pages.formexample;

import webdriver.Locator;

public class QAFormPage {

    public static Locator NAME_TEXT_BOX_LOCATOR = Locator.createByPlaceholder("Name");
    public static Locator MESSAGE_TEXT_BOX_LOCATOR = Locator.createByPlaceholder("Message");
    public static Locator SUBMIT_BUTTON_LOCATOR = Locator.createByCss("button[type='submit']");

    public static Locator CONFIRMATION_MESSAGE_LOCATOR = Locator.createByText("Thanks for contacting us");

    public static Locator ERROR_MESSAGE_LOCATOR = Locator.createByText("Make sure you fill in all required fields.");


}
