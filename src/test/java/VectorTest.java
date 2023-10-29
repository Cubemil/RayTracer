import org.example.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @Test
    void negatingVectorTest() {
        Vector vector = new Vector(0.1, 2.3, 9.6);
        assertTrue(Vector.equals(new Vector(-0.1, -2.3, -9.6), Vector.negate(vector)));
    }

    @Test
    void scalingVectorTest() {
        Vector vector = new Vector(0.1, 0.2, 0.3);

        // Testing different scalars on Points
        assertTrue(Vector.equals(new Vector(0.03, 0.06, 0.09), Vector.multiply(vector, 0.3)));
        assertTrue(Vector.equals(new Vector(0.0, 0.0, 0.0), Vector.multiply(vector, 0.0)));
        assertTrue(Vector.equals(new Vector(-0.03, -0.06, -0.09), Vector.multiply(vector, -0.3)));
        // TODO Take care of floating point precision errors
        assertTrue(Vector.equals(new Vector(0.15000000000000002,0.30000000000000004,0.44999999999999996), Vector.multiply(vector, 1.5)));
        assertTrue(Vector.equals(new Vector(-0.15000000000000002,-0.30000000000000004,-0.44999999999999996), Vector.multiply(vector, -1.5)));
    }

    @Test
    void dividingVectorTest() {
        Vector vector = new Vector(0.1, 0.2, 0.3);

        assertTrue(Vector.equals(new Vector(0.01, 0.02, 0.03), Vector.divide(vector, 10)));
        assertTrue(Vector.equals(new Vector(-0.01, -0.02, -0.03), Vector.divide(vector, -10)));
        // TODO Take care of floating point division
        assertTrue(Vector.equals(new Vector(1.0, 2.0, 2.9999999999999996), Vector.divide(vector, 0.1)));
    }

    @Test
    void illegalDivisionVectorTest() {
        Vector point = new Vector(0.1, 0.2, 0.3);
        boolean thrown = false;
        try {
            Vector.divide(point, 0);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void addTwoVectorsTest() {
        Vector vector1 = new Vector(3.0, -2.0, 5.0);
        Vector vector2 = new Vector(-2.0, 3.0, 1.0);

        assertTrue(Vector.equals(new Vector(1.0, 1.0, 6.0), Vector.add(vector1, vector2)));
    }

    @Test
    void subtractTwoVectorsTest() {
        Vector vector1 = new Vector(3,2,1);
        Vector vector2 = new Vector(5,6,7);

        assertTrue(Vector.equals(new Vector(-2.0, -4.0, -6.0), Vector.subtract(vector1, vector2)));
    }

    @Test
    void magnitudeOfVectorTest() {
        assertEquals(1, Vector.magnitude(new Vector(1, 0, 0)));
        assertEquals(1, Vector.magnitude(new Vector(0, 1, 0)));
        assertEquals(1.0, Vector.magnitude(new Vector(0, 0, 1)));
        assertEquals(3.7416573867739413, Vector.magnitude(new Vector(1, 2, 3)));
        assertEquals(3.7416573867739413, Vector.magnitude(new Vector(-1, -2, -3)));
    }

    @Test
    void squaredMagnitudeOfVectorTest() {
        assertEquals(1, Vector.sqrMagnitude(new Vector(1, 0, 0)));
        assertEquals(1, Vector.sqrMagnitude(new Vector(0, 1, 0)));
        assertEquals(1.0, Vector.sqrMagnitude(new Vector(0, 0, 1)));
        assertEquals(14.0, Vector.sqrMagnitude(new Vector(1, 2, 3)));
        assertEquals(14.0, Vector.sqrMagnitude(new Vector(-1, -2, -3)));
    }

    @Test
    void normalizeVectorTest() {
        assertTrue(Vector.equals(new Vector(1, 0, 0), Vector.normalized(new Vector(4, 0 ,0))));
        assertTrue(Vector.equals(new Vector(0.2672612419124244, 0.5345224838248488, 0.8017837257372732), Vector.normalized(new Vector(1, 2, 3))));
    }

    @Test
    void dotProductTest() {
        Vector vector1 = new Vector(1, 2, 3);
        Vector vector2 = new Vector(2, 3, 4);

        assertEquals(20, Vector.dot(vector1, vector2));
    }

    @Test
    void crossProductTest() {
        Vector vector1 = new Vector(1, 2, 3);
        Vector vector2 = new Vector(2, 3, 4);

        assertTrue(Vector.equals(new Vector(-1, 2, -1), Vector.cross(vector1, vector2)));
        assertTrue(Vector.equals(new Vector(1, -2, 1), Vector.cross(vector2, vector1)));
    }


}