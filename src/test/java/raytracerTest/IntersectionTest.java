package raytracerTest;

import raytracer.IntersectionManager;
import raytracer.geometry.Sphere;
import raytracer.Intersection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntersectionTest {

    @Test
    void intersectionStructureTest() {
        Sphere sphere = new Sphere();
        Intersection i1 = new Intersection(3.5, sphere);
        assertEquals(3.5, i1.t());
        assertEquals(sphere, i1.getShape());
    }

    @Test
    void comparingIntersectionsTest() {
        Sphere sphere = new Sphere();
        Intersection i1 = new Intersection(3.5, sphere);
        Intersection i2 = new Intersection(0.3, sphere);
        assertTrue(i1.t() > i2.t());
    }

    @Test
    void aggregatingIntersectionsTest() {
        Sphere sphere = new Sphere();
        Intersection i1 = new Intersection(1, sphere);
        Intersection i2 = new Intersection(2, sphere);
        IntersectionManager xs = new IntersectionManager(i1, i2);
        assertEquals(2, xs.count());
        assertEquals(1.0, xs.get(0).t());
        assertEquals(2.0, xs.get(1).t());
    }

    @Test
    void hitAllPositiveTValueTest() {
        var sphere = new Sphere();
        var i1 = new Intersection(1, sphere);
        var i2 = new Intersection(2, sphere);
        var xs = new IntersectionManager(i2, i1);
        assertEquals(i1, xs.hit());
    }

    @Test
    void hitSomeNegativeTValueTest() {
        var sphere = new Sphere();
        var i1 = new Intersection(-1, sphere);
        var i2 = new Intersection(1, sphere);
        var xs = new IntersectionManager(i2, i1);
        assertEquals(i2, xs.hit());
    }

    @Test
    void hitAllNegativeTValueTest() {
        var sphere = new Sphere();
        var i1 = new Intersection(-2, sphere);
        var i2 = new Intersection(-1, sphere);
        var xs = new IntersectionManager(i2, i1);
        assertNull(xs.hit());
    }

    @Test
    void hitLowestNonNegativeTest() {
        var sphere = new Sphere();
        var i1 = new Intersection(5, sphere);
        var i2 = new Intersection(7, sphere);
        var i3 = new Intersection(-3, sphere);
        var i4 = new Intersection(2, sphere);
        var xs = new IntersectionManager(i1, i2, i3, i4);
        assertEquals(i4, xs.hit());
    }

}