import org.example.Point;
import org.example.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PointTest {

    /************ Points and Vectors Tests ****************/

    // Point - Point = Point
    @Test
    void subtractTwoPointsTest() {
        Point point1 = new Point(3, 2, 1);
        Point point2 = new Point(5, 6, 7);
        // Man muss mit Vector.equals testen, sonst sind die Hash Codes verschieden
        assertTrue(Vector.equals(new Vector(-2.0, -4.0, -6.0), Point.subtract(point1, point2)));
    }

    // Point + Vector = Point
    @Test
    void addVectorToPointTest() {
        Point point = new Point(3.0, -2.0, 5.0);
        Vector vector = new Vector(-2.0, 3.0, 1.0);
        assertTrue(Point.equals(new Point(1.0, 1.0, 6.0), Point.add(point, vector)));

    }

    // Point - Vector = Point
    @Test
    void subtractVectorFromPointTest() {
        Point point = new Point(3, 2, 1);
        Vector vector = new Vector(5, 6, 7);
        assertTrue(Point.equals(new Point(-2.0,-4.0,-6.0), Point.subtract(point, vector)));
    }

    /************ Point Tests ****************/

    // Point * scalar = Point
    @Test
    void pointScalingTest() {
        Point point = new Point(0.1, 0.2, 0.3);

        // Testing different scalars on Points
        assertTrue(Point.equals(new Point(0.03, 0.06, 0.09), Point.multiply(point, 0.3)));
        assertTrue(Point.equals(new Point(0.0, 0.0, 0.0), Point.multiply(point, 0.0)));
        assertTrue(Point.equals(new Point(-0.03, -0.06, -0.09), Point.multiply(point, -0.3)));
        // TODO Take care of floating point precision errors
        assertTrue(Point.equals(new Point(0.15000000000000002,0.30000000000000004,0.44999999999999996), Point.multiply(point, 1.5)));
        assertTrue(Point.equals(new Point(-0.15000000000000002,-0.30000000000000004,-0.44999999999999996), Point.multiply(point, -1.5)));
    }

    // Point / scalar = Point
    @Test
    void pointDividingTest() {
        Point point = new Point(0.1, 0.2, 0.3);

        assertTrue(Point.equals(new Point(0.01, 0.02, 0.03), Point.divide(point, 10)));
        assertTrue(Point.equals(new Point(-0.01, -0.02, -0.03), Point.divide(point, -10)));
        // TODO Take care of floating point division
        assertTrue(Point.equals(new Point(1.0, 2.0, 2.9999999999999996), Point.divide(point, 0.1)));
    }

    // Tests for thrown IllegalArgumentException for division by zero
    @Test
    void illegalDivisionByZeroTest() {
        Point point = new Point(0.1, 0.2, 0.3);
        boolean thrown = false;
        try {
            Point.divide(point, 0);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void minimumOfTwoPointsTest() {
        Point point1 = new Point(1,2,3);
        Point point2 = new Point(1,2,3);
        Point point3 = new Point(3,2,1);

        assertTrue(Point.equals(new Point(1, 2, 3), Point.min(point1, point2)));
        assertTrue(Point.equals(new Point(1, 2, 1), Point.min(point1, point3)));
    }

    @Test
    void maximumOfTwoPointsTest() {
        Point point1 = new Point(1,2,3);
        Point point2 = new Point(1,2,3);
        Point point3 = new Point(3,2,1);

        assertTrue(Point.equals(new Point(1, 2, 3), Point.max(point1, point2)));
        assertTrue(Point.equals(new Point(3, 2, 3), Point.max(point1, point3)));
    }

}