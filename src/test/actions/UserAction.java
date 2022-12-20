package actions;


import java.io.FileNotFoundException;

/***
 * Interface for the common actions a user will need to perform.
 * This will keep the {@link tasks.Task} classes from being tightly coupled to the selenium framework.
 */
public interface UserAction {

    void openBrowser();

    void quitBrowser();

    void takeScreenshot(String filename);

    void click(String locator);

    void fill(String locator, String input);

    void select(String locator, String text);

    void check(String locator);

    void uncheck(String locator);

    void navigate(String url);

    String readValue(String locator);

    Boolean canSee(String locator);
}
