package actions;


import webdriver.Locator;

import java.io.FileNotFoundException;

/***
 * Interface for the common actions a user will need to perform.
 * This will keep the {@link tasks.Task} classes from being tightly coupled to the selenium framework.
 */
public interface UserAction {

    void click(Locator locator);

    void fill(Locator locator, String input);

    void select(Locator locator, String text);

    void check(Locator locator);

    void uncheck(Locator locator);

    void navigate(String url);

    String readValue(Locator locator);

    Boolean canSee(Locator locator);
}
