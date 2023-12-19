package raytracerTest.geometryTest;

import math.Matrix;
import math.Point;
import raytracer.Ray;
import math.Vector;
import raytracer.geometry.Sphere;
import raytracer.IntersectionManager;
import org.junit.jupiter.api.Test;
import static math.Matrix.*;
import static org.junit.jupiter.api.Assertions.*;

class SphereTransformationTest {

    @Test
    void sphereDefaultTransformationTest() {
        Sphere sphere = new Sphere();
        Matrix identityMatrix = identityMatrix();
        assertEquals(identityMatrix, sphere.getTransformationMatrix());
    }

    @Test
    void changingSphereTransformationTest() {
        Sphere sphere = new Sphere();
        Matrix transform = translate(2, 3, 4);
        sphere.setTransformationMatrix(transform);

        assertEquals(transform, sphere.getTransformationMatrix());
    }

    @Test
    void rayIntersectionWithScaledSphereTest() {
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Sphere sphere = new Sphere();
        Matrix transform = scale(2, 2, 2);
        sphere.setTransformationMatrix(transform);
        IntersectionManager xs = sphere.intersect(ray);

        assertEquals(2, xs.count());
        assertEquals(3, xs.get(0).t());
        assertEquals(7, xs.get(1).t());
    }

    @Test
    void rayIntersectionWithTransSphereTest() {
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Sphere sphere = new Sphere();
        Matrix transform = translate(5, 0, 0);
        sphere.setTransformationMatrix(transform);
        IntersectionManager xs = sphere.intersect(ray);

        assertEquals(0, xs.count());
    }

}