package main.java.ro.ulbs.proiectaresoftware.lab6.advanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NewIntCalculatorTest {

    private NewIntCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new NewIntCalculator();
        calculator.init();
    }

    @Test
    public void testAddPositive() {
        calculator.add(5);
        assertEquals(5, calculator.result());
    }

    @Test
    public void testAddNegatives() {
        calculator.add(-5);
        assertEquals(-5, calculator.result());
    }

    @Test
    public void testSubtractPositives() {
        calculator.subtract(3);
        assertEquals(-3, calculator.result());
    }

    @Test
    public void testSubtractNegatives() {
        calculator.subtract(-3);
        assertEquals(3, calculator.result());
    }

    @Test
    public void testMultiplyPositives() {
        calculator.add(4).multiply(2);
        assertEquals(8, calculator.result());
    }

    @Test
    public void testMultiplyNegatives() {
        calculator.add(4).multiply(-2);
        assertEquals(-8, calculator.result());
    }

    @Test
    public void testMultiplyBy0() {
        calculator.add(4).multiply(0);
        assertEquals(0, calculator.result());
    }

    @Test
    public void testDividePositives() {
        calculator.add(10).divide(2);
        assertEquals(5, calculator.result());
    }

    @Test
    public void testDivideNegatives() {
        calculator.add(10).divide(-2);
        assertEquals(-5, calculator.result());
    }

    @Test
    public void testDivideBy0() {
        calculator.add(10);
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(0);
        });
    }
}