package tests;

import pages.URL;
import tasks.Calculator;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorTests extends BaseTest {

    @Test
    public void canDisplayCalculatorOnIndexPage() {
        user.navigate(URL.INDEX_URL);
        var calculator = new Calculator(user);
        assertTrue(calculator.isShownInGridView());
    }

    @Test
    public void canAddTwoIntegers() {
        var number1 = 55;
        var number2 = 500;
        var expectedResult = 555;

        var calculator = new Calculator(user);
        calculator.visit();
        calculator.add(number1, number2);

        assertEquals(expectedResult, calculator.getAnswer());
    }

    @Test
    public void canMultiplyTwoIntegers() {
        var number1 = 55;
        var number2 = 500;
        var expectedResult = 27500;

        var calculator = new Calculator(user);
        calculator.visit();
        calculator.multiply(number1, number2);

        assertEquals(expectedResult, calculator.getAnswer());
    }

    @Test
    public void canSubtractOneIntegerFromAnother() {
        var number1 = 55;
        var number2 = 500;
        var expectedResult = -445;

        var calculator = new Calculator(user);
        calculator.visit();
        calculator.subtract(number1, number2);

        assertEquals(expectedResult, calculator.getAnswer());
    }

    @Test
    public void canDivideOneIntegerByAnother() {
        var number1 = 99;
        var number2 = 5;
        var expectedResult = 19.8;

        var calculator = new Calculator(user);
        calculator.visit();
        calculator.divide(number1, number2);

        assertEquals(expectedResult, calculator.getAnswer());
    }

}
