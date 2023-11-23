import org.example.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void sphereIntersectionAtTwoPointsTest() {
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Sphere sphere = new Sphere();

        IntersectionManager xs = sphere.intersect(ray);

        assertEquals(2, xs.count());
        assertEquals(4.0, xs.get(0).getT());
        assertEquals(6.0, xs.get(1).getT());


    }

}