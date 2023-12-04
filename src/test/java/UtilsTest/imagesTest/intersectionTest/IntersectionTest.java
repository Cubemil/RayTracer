package utilsTest.imagesTest.intersectionTest;

import org.example.utils.intersections.IntersectionManager;
import org.example.objects.Sphere;
import org.example.utils.intersections.Intersection;
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

}