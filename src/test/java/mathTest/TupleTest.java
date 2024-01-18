package mathTest;

import math.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TupleTest {

    @Test
    void reflectingVector45DegreesTest() {
        Vector vector1 = new Vector(1, -1, 0);
        Vector vector2 = new Vector(0, 1, 0);
        Vector reflect = vector1.reflect(vector2);

        assertEquals(new Vector(1, 1, 0), reflect);
    }

}