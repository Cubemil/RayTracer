package raytracerTest.geometryTest;

import math.Matrix;
import math.Point;
import math.Vector;
import org.junit.jupiter.api.Test;
import raytracer.Material;
import raytracer.Ray;
import raytracer.geometry.Shape;
import raytracer.geometry.TestShape;

import static org.junit.jupiter.api.Assertions.*;

class TestShapeTest {

    @Test
    void defaultTransformationTest() {
        Shape shape = new TestShape();

        assertEquals(Matrix.identityMatrix(), shape.getTransformationMatrix());
    }

    @Test
    void assigningTransformationTest() {
        Shape shape = new TestShape();
        shape.setTransformationMatrix(Matrix.translate(2, 3, 4));

        assertEquals(Matrix.translate(2, 3, 4), shape.getTransformationMatrix());
    }

    @Test
    void defaultMaterialTest() {
        Shape shape = new TestShape();
        Material material = shape.getMaterial();

        assertEquals(new Material(), shape.getMaterial());
    }

    @Test
    void assigningMaterialTest() {
        Shape shape = new TestShape();
        Material material = new Material();
        material.setAmbient(1.0);
        shape.setMaterial(material);

        assertEquals(material, shape.getMaterial());
    }

    @Test
    void intersectingScaledShapeWithRayTest() {
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        TestShape shape = new TestShape();
        shape.setTransformationMatrix(Matrix.scale(2, 2, 2));
        var xs = shape.intersect(ray);

        assertEquals(new Point(0, 0, -2.5), shape.getSavedRay().getOriginPoint());
        assertEquals(new Vector(0, 0, 0.5), shape.getSavedRay().getDirectionVector());
    }

    @Test
    void intersectingTranslatedShapeWithRayTest() {
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        TestShape shape = new TestShape();
        shape.setTransformationMatrix(Matrix.translate(5, 0, 0));
        var xs = shape.intersect(ray);

        assertEquals(new Point(-5, 0, -5), shape.getSavedRay().getOriginPoint());
        assertEquals(new Vector(0, 0, 1), shape.getSavedRay().getDirectionVector());
    }

    @Test
    void computingNormalOnTranslatedShapeTest() {
        Shape shape = new TestShape();
        shape.setTransformationMatrix(Matrix.translate(0, 1, 0));
        var normal = shape.normalAt(new Point(0, 1.70711, -0.70711));

        assertEquals(new Vector(0, 0.707106, -0.707106), normal);
    }

    @Test
    void computingNormalOnTransformedShapeTest() {
        Shape shape = new TestShape();
        var m = Matrix.scale(1, 0.5, 1).mult(Matrix.rotateZ(0.628318));
        shape.setTransformationMatrix(m);
        var normal = shape.normalAt(new Point(0, 0.707106, -0.707106));

        assertEquals(new Vector(0, 0.9701425, -0.2425356), normal);
    }

}
