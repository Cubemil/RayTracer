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

    @Test
    void intersectSetsObjectOnIntersectionTest() {
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Sphere sphere = new Sphere();

        IntersectionManager xs = sphere.intersect(ray);
        assertEquals(2, xs.count());
        assertEquals(sphere, xs.get(0).shape());
        assertEquals(sphere, xs.get(1).shape());
    }

    @Test
    void rayIntersectsSphereAtATangentTest() {
        Ray ray = new Ray(new Point(0, 1, -5), new Vector(0, 0, 1));
        Sphere sphere = new Sphere();
        IntersectionManager xs = sphere.intersect(ray);

        assertEquals(2, xs.count());
        assertEquals(5.0, xs.get(0).getT());
        assertEquals(5.0, xs.get(1).getT());
    }

    @Test
    void rayMissesSphereTest() {
        Ray ray = new Ray(new Point(0, 2, -5), new Vector(0, 0, 1));
        Sphere sphere = new Sphere();
        IntersectionManager xs = sphere.intersect(ray);

        assertEquals(0, xs.count());
    }

    @Test
    void rayOriginatesInsideSphereTest() {
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Sphere sphere = new Sphere();
        IntersectionManager xs = sphere.intersect(ray);

        assertEquals(2, xs.count());
        assertEquals(-1, xs.get(0).getT());
        assertEquals(1, xs.get(1).getT());
    }

    @Test
    void sphereIsBehindRayTest() {
        Ray ray = new Ray(new Point(0, 0, 5), new Vector(0, 0, 1));
        Sphere sphere = new Sphere();
        IntersectionManager xs = sphere.intersect(ray);

        assertEquals(2, xs.count());
        assertEquals(-6.0, xs.get(0).getT());
        assertEquals(-4.0, xs.get(1).getT());
    }

    @Test
    void normalSpherePointXAxisTest() {
        Sphere sphere = new Sphere();
        Vector n = sphere.normalAt(new Point(1, 0, 0));

        assertEquals(new Vector(1, 0, 0), n);
        assertEquals(n, n.normalized());
    }

    @Test
    void normalSpherePointYAxisTest() {
        Sphere sphere = new Sphere();
        Vector n = sphere.normalAt(new Point(0, 1, 0));

        assertEquals(new Vector(0, 1, 0), n);
        assertEquals(n, n.normalized());
    }

    @Test
    void normalSpherePointTAxisTest() {
        Sphere sphere = new Sphere();
        Vector n = sphere.normalAt(new Point(0, 0, 1));

        assertEquals(new Vector(0, 0, 1), n);
        assertEquals(n, n.normalized());
    }

}