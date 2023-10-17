import org.example.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for Calculator.java
class CalculatorTest {

    // simple numbers test for add()
    @Test
    void addSimpleTest() {
        Calculator calc = new Calculator();
        assertEquals(2, calc.add(1, 1));
    }

    // test using bigger integers for add()
    @Test
    void addBigNumbersTest() {
        Calculator calc = new Calculator();
        assertEquals(517727833, calc.add(2115166, 515612667));
    }

    // test using negative numbers for add()
    @Test
    void addNegativeNumbersTest() {
        Calculator calc = new Calculator();
        assertEquals(-202715, calc.add(1337, -204052));
    }

}