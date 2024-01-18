package raytracerTest.geometryTest;

import math.Point;
import math.Vector;
import org.junit.jupiter.api.Test;
import raytracer.Ray;
import raytracer.geometry.Cube;

import static org.junit.jupiter.api.Assertions.*;

class CubeTest{

    @Test
    void rayIntersectsCubeTest() {
        Point[] origins = {
                new Point(5, 0.5, 0),
                new Point(-5, 0.5, 0),
                new Point(0.5, 5, 0),
                new Point(0.5, -5, 0),
                new Point(0.5, 0, 5),
                new Point(0.5, 0, -5),
                new Point(0, 0.5, 0)};
        Vector[] directions = {
                new Vector(-1, 0, 0),
                new Vector(1, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 1, 0),
                new Vector(0, 0, -1),
                new Vector(0, 0, 1),
                new Vector(0, 0, 1)};
        double[] t1 = {
                4, 4, 4, 4, 4, 4, -1};
        double[] t2 = {
                6, 6, 6, 6, 6, 6, 1};

        Cube cube = new Cube();
        for (int i = 0; i < origins.length; i++) {
            var ray = new Ray(origins[i], directions[i]);
            var xs = cube.localIntersect(ray);

            assertEquals(2, xs.count());
            assertEquals(t1[i], xs.get(0).t());
            assertEquals(t2[i], xs.get(1).t());
        }
    }

    @Test
    void rayMissesCubeTest() {
        Point[] origins = {
                new Point(-2, 0, 0),
                new Point(0, -2, 0),
                new Point(0, 0, -2),
                new Point(2, 0, 2),
                new Point(0, 2, 2),
                new Point(2, 2, 0)};

        Vector[] directions = {
                new Vector(0.2673, 0.5345, 0.8018),
                new Vector(0.8018, 0.2673, 0.5345),
                new Vector(0.5345, 0.8018, 0.2673),
                new Vector(0, 0, -1),
                new Vector(0, -1, 0),
                new Vector(-1, 0, 0),};

        Cube cube = new Cube();

        for (int i = 0; i < origins.length; i++) {
            var ray = new Ray(origins[i], directions[i]);
            var xs = cube.localIntersect(ray);

            assertEquals(0, xs.count());
        }
    }

    @Test
    void normalOnSurfaceOfCubeTest() {
        Point[] origins = {
                new Point(1, 0.5, -0.8),
                new Point(-1, -0.2, 0.9),
                new Point(-0.4, 1, -0.1),
                new Point(0.3, -1, -0.7),
                new Point(-0.6, 0.3, 1),
                new Point(0.4, 0.4, -1),
                new Point(1, 1, 1),
                new Point(-1, -1, -1)};

        Vector[] normals = {
                new Vector(1, 0, 0),
                new Vector(-1, 0, 0),
                new Vector(0, 1, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, 1),
                new Vector(0, 0, -1),
                new Vector(1, 0, 0),
                new Vector(-1, 0, 0)};

        Cube cube = new Cube();

        for (int i = 0; i < origins.length; i++) {
            var normal = cube.localNormalAt(origins[i]);
            assertEquals(normals[i], normal);
        }
    }

}
