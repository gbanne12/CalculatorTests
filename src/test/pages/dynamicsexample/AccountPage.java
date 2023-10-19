package pages.dynamicsexample;

import webdriver.By;


public class AccountPage {

    public static By SUMMARY_TAB = By.role("tab", "Summary");
    public static By DETAILS_TAB = By.role("tab", "Details");
    public static By CONTACTS_TAB = By.role("tab", "Contacts");
    public static By NOTES_AND_ACTIVITIES_TAB = By.role("tab", "Notes & Activities");
    public static By ACCOUNT_NAME_INPUT = By.label("Account Name");
    public static By INDUSTRY_LIST = By.role("combobox", "Industry");
    public static By SAVE_BUTTON = By.role("menuitem", "Save (CTRL+S)");
    public static By SAVED_STATUS_INDICATOR = By.role("status", "Save status - Saved");
    public static By SAVING_IN_PROGRESS_TEXT = By.text("Saving...");
    public static By FORM_SELECTOR = By.testId("form-selector");
    public static By FORM_LIST_INFORMATION = By.text(Forms.INFORMATION.displayName);
    public static By FORM_LIST_ACCOUNT = By.text((Forms.ACCOUNT.displayName));

    private enum Forms {
        ACCOUNT("Account"),
        INFORMATION("Information");

        String displayName;

        Forms(String displayName) {
            this.displayName = displayName;
        }
    }
}
