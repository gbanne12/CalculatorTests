package tasks.dynamicsexample;

import actions.User;
import pages.dynamicsexample.LoginPage;
import tasks.Task;
import util.ConfigReader;

import java.time.Duration;

public class Login implements Task {
    private User user;

    public Login(User user) {
        this.user = user;
    }


    @Override
    public void visit() {
        ConfigReader configReader = new ConfigReader();
        user.navigate(configReader.getAppUrl());
    }

    public Login withUsernameAndPassword() {
        var config = new ConfigReader();
        if (user.canSee(LoginPage.USERNAME_INPUT)) {
            user.click(LoginPage.USERNAME_INPUT);
            user.fill(LoginPage.USERNAME_INPUT, config.getUsername());
            user.click(LoginPage.USERNAME_SUBMIT_BUTTON);
            user.fill(LoginPage.PASSWORD_INPUT, config.getPassword());
            user.click(LoginPage.PASSWORD_SUBMIT_BUTTON);
            user.click(LoginPage.STAY_SIGNED_IN_BUTTON);

            user.maxWaitTime(Duration.ofSeconds(20));
            user.click(LoginPage.WELCOME_OVERLAY_TEXT);
            user.click(LoginPage.WELCOME_OVERLAY_SUBMIT_BUTTON);

        }

        return this;
    }
}
