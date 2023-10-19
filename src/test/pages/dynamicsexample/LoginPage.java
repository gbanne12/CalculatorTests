package pages.dynamicsexample;

import webdriver.By;

public class LoginPage {

    public static By USERNAME_INPUT = By.placeholder("Email address, phone number or Skype");
    public static By PASSWORD_INPUT = By.placeholder("Password");
    public static By USERNAME_SUBMIT_BUTTON = By.role("submit", "Next");
    public static By PASSWORD_SUBMIT_BUTTON = By.role("submit", "Sign in");  //inferred role using 'Input' tag and type attribute
    public static By STAY_SIGNED_IN_BUTTON = By.role("submit", "Yes");

    // Displayed on 1st log in (i.e. for a new chrome profile)
    public static By WELCOME_OVERLAY_TEXT = By.text("Do not show this Welcome Screen next time");
    public static By WELCOME_OVERLAY_SUBMIT_BUTTON = By.role("button", "Continue"); //inferred role using Element tag

}
