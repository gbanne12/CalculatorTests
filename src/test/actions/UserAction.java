package actions;


import webdriver.By;

/***
 * Interface for the common actions a user will need to perform.
 * This will keep the {@link tasks.Task} classes from being tightly coupled to the selenium framework.
 */
public interface UserAction {

    void click(By locator);

    void fill(By locator, String input);

    void select(By locator, String text);

    void check(By locator);

    void uncheck(By locator);

    void navigate(String url);

    String readValue(By locator);

    Boolean canSee(By locator);
}
