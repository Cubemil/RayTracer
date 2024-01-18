package raytracerTest.geometryTest;

import math.Point;
import math.Vector;
import org.junit.jupiter.api.Test;
import raytracer.Ray;
import raytracer.geometry.Plane;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void planeNormal_IsConstantEverywhere_Test() {
        Plane plane = new Plane();
        var n1 = plane.localNormalAt(new Point(0, 0, 0));
        var n2 = plane.localNormalAt(new Point(10, 0, -10));
        var n3 = plane.localNormalAt(new Point(-5, 0, 150));

        assertEquals(new Vector(0, 1, 0), n1);
        assertEquals(new Vector(0, 1, 0), n2);
        assertEquals(new Vector(0, 1, 0), n3);
    }

    @Test
    void intersectWithRay_parallelToPlane_Test() {
        Plane plane = new Plane();
        var ray = new Ray(new Point(0, 10, 0), new Vector(0, 0, 1));
        var xs = plane.localIntersect(ray);

        assertEquals(xs.count(), 0);
    }

    @Test
    void intersectWithCoplanarRay_Test() {
        Plane plane = new Plane();
        var ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        var xs = plane.localIntersect(ray);

        assertEquals(xs.count(), 0);
    }

    @Test
    void rayIntersecting_planeFromAbove_Test() {
        Plane plane = new Plane();
        var ray = new Ray(new Point(0, 1, 0), new Vector(0, -1, 0));
        var xs = plane.localIntersect(ray);

        assertEquals(1, xs.count());
        assertEquals(1, xs.get(0).t());
        assertEquals(plane, xs.get(0).getShape());
    }

    @Test
    void rayIntersecting_planeFromBelow_Test() {
        Plane plane = new Plane();
        var ray = new Ray(new Point(0, -1, 0), new Vector(0, 1, 0));
        var xs = plane.localIntersect(ray);

        assertEquals(1, xs.count());
        assertEquals(1, xs.get(0).t());
        assertEquals(plane, xs.get(0).getShape());
    }

}