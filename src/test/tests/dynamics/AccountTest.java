package tests.dynamics;

import actions.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.dynamicsexample.AccountPage;
import tasks.dynamicsexample.AccountForm;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest extends DynamicsTest {

    User user;

    @BeforeEach
    public void openBrowser() {
        user = new User();
        user.openBrowser();
    }

    @AfterEach
    public void closeBrowser() {
        user.closeBrowser();
    }

    @Test
    public void canAddAccount() {
        String accountName = String.valueOf(LocalDateTime.now());

        var accountForm = new AccountForm(user);
        accountForm.visit();
        accountForm.addBrokersAccount(accountName);
        accountForm.allowSavingToFinish();

        assertTrue(user.canSee(AccountPage.SAVED_STATUS_INDICATOR));
    }

    @Test
    public void canSwitchForms() {
        var accountForm = new AccountForm(user);
        accountForm.visit();
        accountForm.switchToInformationForm();

        assertTrue(user.canSee(AccountPage.CONTACTS_TAB));
        assertTrue(user.canSee(AccountPage.NOTES_AND_ACTIVITIES_TAB));
    }

}
