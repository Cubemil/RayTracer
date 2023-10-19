import org.example.Point;
import org.example.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

class PointTest {

    @Test
    void subtractTwoPointsTest() {
        Point point1 = new Point(3, 2, 1);
        Point point2 = new Point(5, 6, 7);
        // Man muss mit Vector.equals testen, sonst sind die Hash Codes verschieden
        assertTrue(Vector.equals(new Vector(-2.0, -4.0, -6.0), Point.subtract(point1, point2)));
    }

    @Test
    void addVectorToPointTest() {
        Point point = new Point(3.0, -2.0, 5.0);
        Vector vector = new Vector(-2.0, 3.0, 1.0);
        assertTrue(Point.equals(new Point(1.0, 1.0, 6.0), Point.add(point, vector)));

    }

    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = {},
            glue = {""})

    public class RunCucumberTest{

    }

    @Test
    void multiply() {

    }

    @Test
    void testMultiply() {
    }

    @Test
    void divide() {
    }

    @Test
    void add() {
    }

    @Test
    void testSubtract() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void min() {
    }

    @Test
    void max() {
    }
}