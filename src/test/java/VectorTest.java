import org.example.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @Test
    void negatingVectorTest() {
        Vector vector = new Vector(0.1, 2.3, 9.6);
        assertEquals(new Vector(-0.1, -2.3, -9.6), vector.neg());
    }


    @Test
    void scalingVectorTest() {
        Vector vector = new Vector(0.1, 0.2, 0.3);

        // Testing different scalars on Points
        assertEquals(new Vector(0.03, 0.06, 0.09), vector.mult(0.3));
        assertEquals(new Vector(0.0, 0.0, 0.0), vector.mult(0.0));
        assertEquals(new Vector(-0.03, -0.06, -0.09), vector.mult(-0.3));
        assertEquals(new Vector(0.15, 0.3, 0.45), vector.mult(1.5));
        assertEquals(new Vector(-0.15, -0.3, -0.45), vector.mult(-1.5));
    }

    @Test
    void dividingVectorTest() {
        Vector vector = new Vector(0.1, 0.2, 0.3);

        assertEquals(new Vector(0.01, 0.02, 0.03), vector.div(10));
        assertEquals(new Vector(-0.01, -0.02, -0.03), vector.div(-10));
        assertEquals(new Vector(1.0, 2.0, 3.0), vector.div(0.1));
    }

    @Test
    void illegalDivisionVectorTest() {
        Vector vector = new Vector(0.1, 0.2, 0.3);
        boolean thrown = false;
        try {
            vector.div(0);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void addTwoVectorsTest() {
        Vector vector1 = new Vector(3.0, -2.0, 5.0);
        Vector vector2 = new Vector(-2.0, 3.0, 1.0);

        assertEquals(new Vector(1.0, 1.0, 6.0), vector1.add(vector2));
    }

    @Test
    void subtractTwoVectorsTest() {
        Vector vector1 = new Vector(3,2,1);
        Vector vector2 = new Vector(5,6,7);

        assertEquals(new Vector(-2.0, -4.0, -6.0), vector1.sub(vector2));
    }
    @Test
    void magnitudeOfVectorTest() {
        assertEquals(1, new Vector(1, 0, 0).magnitude());
        assertEquals(1, new Vector(0, 1, 0).magnitude());
        assertEquals(1.0, new Vector(0, 0, 1).magnitude());
        //TODO adjust floating point precision
        assertEquals(3.7416573867739413, new Vector(1, 2, 3).magnitude());
        assertEquals(3.7416573867739413, new Vector(-1, -2, -3).magnitude());
    }


    @Test
    void squaredMagnitudeOfVectorTest() {
        assertEquals(1, new Vector(1, 0, 0).sqrMagnitude());
        assertEquals(1, new Vector(0, 1, 0).sqrMagnitude());
        assertEquals(1.0, new Vector(0, 0, 1).sqrMagnitude());
        assertEquals(14.0, new Vector(1, 2, 3).sqrMagnitude());
        assertEquals(14.0, new Vector(-1, -2, -3).sqrMagnitude());
    }

    @Test
    void normalizeVectorTest() {
        assertEquals(new Vector(1, 0, 0), new Vector(4, 0, 0).normalized());
        assertEquals(new Vector(0.2672612419124244, 0.5345224838248488, 0.8017837257372732), new Vector(1, 2, 3).normalized());
    }

    @Test
    void dotProductTest() {
        Vector vector1 = new Vector(1, 2, 3);
        Vector vector2 = new Vector(2, 3, 4);

        assertEquals(20, vector1.dot(vector2));
    }

    @Test
    void crossProductTest() {
        Vector vector1 = new Vector(1, 2, 3);
        Vector vector2 = new Vector(2, 3, 4);

        assertEquals(new Vector(-1, 2, -1), vector1.cross(vector2));
        assertEquals(new Vector(1, -2, 1),vector2.cross(vector1));
    }


    @Test
    void toStringTest() {
        Vector vector = new Vector(1, 2, 3);
        System.out.println(vector.toString());
    }

}