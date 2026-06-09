package main.java.ro.ulbs.proiectaresoftware.lab6.advanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoubleCalculatorTest {

    private DoubleCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new DoubleCalculator();
        calculator.init();
    }

    @Test
    public void testAddPositive() {
        calculator.add(5.5);
        assertEquals(5.5, calculator.result(), 0.001);
    }

    @Test
    public void testAddNegatives() {
        calculator.add(-5.5);
        assertEquals(-5.5, calculator.result(), 0.001);
    }

    @Test
    public void testSubtractPositives() {
        calculator.subtract(3.3);
        assertEquals(-3.3, calculator.result(), 0.001);
    }

    @Test
    public void testSubtractNegatives() {
        calculator.subtract(-3.3);
        assertEquals(3.3, calculator.result(), 0.001);
    }

    @Test
    public void testMultiplyPositives() {
        calculator.add(4.0).multiply(2.5);
        assertEquals(10.0, calculator.result(), 0.001);
    }

    @Test
    public void testMultiplyNegatives() {
        calculator.add(4.0).multiply(-2.5);
        assertEquals(-10.0, calculator.result(), 0.001);
    }

    @Test
    public void testMultiplyBy0() {
        calculator.add(4.5).multiply(0.0);
        assertEquals(0.0, calculator.result(), 0.001);
    }

    @Test
    public void testDividePositives() {
        calculator.add(10.0).divide(2.5);
        assertEquals(4.0, calculator.result(), 0.001);
    }

    @Test
    public void testDivideNegatives() {
        calculator.add(10.0).divide(-2.5);
        assertEquals(-4.0, calculator.result(), 0.001);
    }

    @Test
    public void testDivideBy0() {
        calculator.add(10.0);
        // Double division by zero in Java returns Infinity, not throwing an exception.
        calculator.divide(0.0);
        assertTrue(Double.isInfinite(calculator.result()));
    }
}