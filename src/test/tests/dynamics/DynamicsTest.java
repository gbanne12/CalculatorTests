package tests.dynamics;

import actions.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.TimeoutException;
import tasks.dynamicsexample.Login;
import util.ConfigReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DynamicsTest {
    protected static User loginUser;

    @BeforeAll
    public static void authenticatedSessionSetup() {
        loginUser = new User();
        loginUser.openBrowser();
        loginUser.navigate(new ConfigReader().getAppUrl());
        try {
            //login if required
            var login = new Login(loginUser);
            login.withUsernameAndPassword();

        } catch (TimeoutException e) {
            System.out.println("No login process presented, assuming tests are already authenticated");

        } finally {
            loginUser.closeBrowser();
        }
    }


    @AfterAll
    public static void userDirectoryCleanUp() {
        var config = new ConfigReader();
        if (!config.useAuthenticatedProfile()) {

            Path folderPath = Path.of(loginUser.getChromeProfileDirectory());
            try {
                // Walk through the directory and delete its contents
                Files.walk(folderPath)
                        .sorted((a, b) -> -a.compareTo(b)) // Sort in reverse order (to delete files before directories)
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                // Finally, delete the empty directory
                Files.delete(folderPath);

                System.out.println("Folder and its contents removed: " + folderPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
