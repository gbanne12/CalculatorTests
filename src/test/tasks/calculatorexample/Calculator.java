package tasks.calculatorexample;

import actions.User;
import pages.URL;
import pages.calculatorexample.CalculatorPage;
import pages.calculatorexample.IndexPage;
import tasks.Task;

/*** Handle tasks the user might perform with and related to the calculator.
 * Note: these tasks can happen on multiple pages.
 */
public class Calculator implements Task {

    User user;

    public Calculator(User user) {
        this.user = user;
    }

    public void visit() {
        user.navigate(URL.CALCULATOR_URL);
    }

    public void add(int numberA, int numberB) {
        performArithmetic("Add", numberA, numberB);
    }

    public void subtract(int numberA, int numberB) {
        performArithmetic("Subtract", numberA, numberB);
    }

    public void divide(int numberA, int numberB) {
        performArithmetic("Divide", numberA, numberB);
    }

    public void multiply(int numberA, int numberB) {
        performArithmetic("Multiply", numberA, numberB);
    }

    public Number getAnswer() {
        String value = user.readValue(CalculatorPage.ANSWER_FIELD_LOCATOR);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            System.out.println("Answer not an integer, parsing as double: " + exception.getLocalizedMessage());
            return Double.parseDouble(value);
        }
    }

    public boolean isShownInGridView() {
        return user.canSee(IndexPage.CALCULATOR_LINK_LOCATOR)
                && user.canSee(IndexPage.CALCULATOR_CAPTION_LOCATOR)
                && user.canSee(IndexPage.CALCULATOR_HEADER_LOCATOR);
    }

    private void performArithmetic(String operation, int numberA, int numberB) {
        user.fill(CalculatorPage.FIRST_NUMBER_LOCATOR, Integer.toString(numberA));
        user.fill(CalculatorPage.SECOND_NUMBER_LOCATOR, Integer.toString(numberB));
        user.select(CalculatorPage.OPERATION_LIST_LOCATOR, operation);
        user.click(CalculatorPage.CALCULATE_BUTTON_LOCATOR);
    }

}
