import org.example.Point;
import org.example.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PointTest {

    /************ Points and Vectors Tests ****************/

    // Point - Point = Vector
    @Test
    void subtractTwoPointsTest() {
        Point point1 = new Point(3, 2, 1);
        Point point2 = new Point(5, 6, 7);
        assertEquals(new Vector(-2.0, -4.0, -6.0), point1.sub(point2));
    }

    // Point + Vector = Point
    @Test
    void addVectorToPointTest() {
        Point point = new Point(3.0, -2.0, 5.0);
        Vector vector = new Vector(-2.0, 3.0, 1.0);
        assertEquals(new Point(1.0, 1.0, 6.0), point.add(vector));
    }
    // Point - Vector = Point
    @Test
    void subtractVectorFromPointTest() {
        Point point = new Point(3, 2, 1);
        Vector vector = new Vector(5, 6, 7);
        assertEquals(new Point(-2.0, -4.0, -6.0), point.sub(vector));
    }

    /************ Point Tests ****************/

    // Point * scalar = Point
    @Test
    void pointScalingTest() {
        Point point = new Point(0.1, 0.2, 0.3);

        // Testing different scalars on Points
        assertEquals(new Point(0.03, 0.06, 0.09), point.mult(0.3));
        assertEquals(new Point(0.0, 0.0, 0.0), point.mult(0.0));
        assertEquals(new Point(-0.03, -0.06, -0.09), point.mult(-0.3));
        assertEquals(new Point(0.15, 0.3, 0.45), point.mult(1.5));
        assertEquals(new Point(-0.15, -0.3, -0.45), point.mult(-1.5));
    }

    // Point / scalar = Point
    @Test
    void pointDividingTest() {
        Point point = new Point(0.1, 0.2, 0.3);

        assertEquals(new Point(0.01, 0.02, 0.03), point.div(10));
        assertEquals(new Point(-0.01, -0.02, -0.03), point.div(-10));
        assertEquals(new Point(1.0, 2.0, 3.0), point.div(0.1));
    }

    // Tests for thrown IllegalArgumentException for division by zero
    @Test
    void illegalDivisionByZeroTest() {
        Point point = new Point(0.1, 0.2, 0.3);
        boolean thrown = false;
        try {
            point.div(0);
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

        assertEquals(new Point(1, 2, 3), point1.min(point2));
        assertEquals(new Point(1, 2, 1), point1.min(point3));
    }

    @Test
    void maximumOfTwoPointsTest() {
        Point point1 = new Point(1,2,3);
        Point point2 = new Point(1,2,3);
        Point point3 = new Point(3,2,1);

        assertEquals(new Point(1, 2, 3), point1.max(point2));
        assertEquals(new Point(3, 2, 3), point1.max(point3));
    }

    @Test
    void toStringTest() {
        Point point = new Point(1, 2, 3);
        assertEquals("Point[x=1.0, y=2.0, z=3.0, w=1.0]",point.toString());
    }
}
