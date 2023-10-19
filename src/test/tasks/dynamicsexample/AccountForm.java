package tasks.dynamicsexample;

import actions.User;
import pages.dynamicsexample.AccountPage;
import tasks.Task;
import util.ConfigReader;

import java.time.Duration;

public class AccountForm implements Task {

    private User user;

    public AccountForm(User user) {
        this.user = user;
    }

    @Override
    public void visit() {
        user.navigate(new ConfigReader().getAppUrl() + "&pagetype=entityrecord&etn=account");
    }

    public void addBrokersAccount(String accountName) {

        user.maxWaitTime(Duration.ofSeconds(5));
        if (user.canSee(AccountPage.CONTACTS_TAB)) {
            switchToAccountForm();
            user.click(AccountPage.SUMMARY_TAB);
        }


        user.maxWaitTime(Duration.ofSeconds(2));
        user.click(AccountPage.ACCOUNT_NAME_INPUT);
        user.fill(AccountPage.ACCOUNT_NAME_INPUT, accountName);
        user.click(AccountPage.DETAILS_TAB);
        user.select(AccountPage.INDUSTRY_LIST, "Brokers");
        user.click(AccountPage.SAVE_BUTTON);
    }

    public void allowSavingToFinish() {
        user.canSee(AccountPage.SAVING_IN_PROGRESS_TEXT);
        user.cannotSee(AccountPage.SAVING_IN_PROGRESS_TEXT);
        user.canSee(AccountPage.SAVED_STATUS_INDICATOR);
    }

    public void switchToInformationForm() {
        user.click(AccountPage.FORM_SELECTOR);
        user.click(AccountPage.FORM_LIST_INFORMATION);
    }

    public void switchToAccountForm() {
        user.click(AccountPage.FORM_SELECTOR);
        user.click(AccountPage.FORM_LIST_ACCOUNT);
    }


}
