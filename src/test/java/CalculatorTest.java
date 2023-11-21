import org.example.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for Calculator.java
class CalculatorTest {

    // simple numbers test for add()
    @Test
    void addSimpleTest() {
        int result = 4;
        int actual = Calculator.add(2, 2);
        assertEquals(result, actual);
    }

    // test using bigger integers for add()
    @Test
    void addBigNumbersTest() {
        assertEquals(517727833, Calculator.add(2115166, 515612667));
    }

    // test using negative numbers for add()
    @Test
    void addNegativeNumbersTest() {
        assertEquals(-202715, Calculator.add(1337, -204052));
    }

}