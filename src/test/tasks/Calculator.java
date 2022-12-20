package tasks;

import actions.User;
import pages.CalculatorPage;
import pages.IndexPage;
import pages.URL;

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
        try {
            return Integer.parseInt(user.readValue(CalculatorPage.ANSWER_FIELD));
        } catch (NumberFormatException exception) {
            System.out.println("Answer not an integer, parsing as double: " + exception.getLocalizedMessage());
            return Double.parseDouble(user.readValue(CalculatorPage.ANSWER_FIELD));
        }
    }

    public boolean isShownInGridView() {
        return user.canSee(IndexPage.CALCULATOR_LINK)
                && user.canSee(IndexPage.CALCULATOR_CAPTION)
                && user.canSee(IndexPage.CALCULATOR_HEADER);
    }

    private void performArithmetic(String operation, int numberA, int numberB) {
        user.fill(CalculatorPage.FIRST_NUMBER, Integer.toString(numberA));
        user.fill(CalculatorPage.SECOND_NUMBER, Integer.toString(numberB));
        user.select(CalculatorPage.OPERATION_LIST, operation);
        user.click(CalculatorPage.CALCULATE_BUTTON);
    }

}
