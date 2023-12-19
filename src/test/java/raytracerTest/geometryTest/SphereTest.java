package raytracerTest.geometryTest;

import math.Matrix;
import math.Point;
import raytracer.Ray;
import math.Vector;
import raytracer.geometry.Sphere;
import raytracer.IntersectionManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void sphereIntersectionAtTwoPointsTest() {
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Sphere sphere = new Sphere();
        IntersectionManager xs = sphere.intersect(ray);

        assertEquals(2, xs.count());
        assertEquals(4.0, xs.get(0).t());
        assertEquals(6.0, xs.get(1).t());
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
        assertEquals(5.0, xs.get(0).t());
        assertEquals(5.0, xs.get(1).t());
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
        assertEquals(-1, xs.get(0).t());
        assertEquals(1, xs.get(1).t());
    }

    @Test
    void sphereIsBehindRayTest() {
        Ray ray = new Ray(new Point(0, 0, 5), new Vector(0, 0, 1));
        Sphere sphere = new Sphere();
        IntersectionManager xs = sphere.intersect(ray);

        assertEquals(2, xs.count());
        assertEquals(-6.0, xs.get(0).t());
        assertEquals(-4.0, xs.get(1).t());
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

    /*---------------------------Testing new normalAt---------------------------*/

    @Test
    void computingNormalOnTranslatedSphere() {
        Sphere sphere = new Sphere();
        sphere.setTransformationMatrix(Matrix.translate(0, 1, 0));
        Vector n = sphere.normalAt(new Point(0, 1.707106, -0.707106));

        assertEquals(new Vector(0, 0.707106, -0.707106), n);
    }

    @Test
    void computingNormalOnTransformedSphere() {
        Sphere sphere = new Sphere();
        sphere.setTransformationMatrix(Matrix.scale(1, 0.5, 1).mult(Matrix.rotateZ(0.62831)));
        Vector n = sphere.normalAt(new Point(0, 0.70711, -0.70711));

        assertEquals(new Vector(0, 0.970142, -0.242536), n);
    }

}