package raytracerTest;

import math.*;
import raytracer.PointLightSource;
import raytracer.Ray;
import raytracer.geometry.Sphere;
import raytracer.Scene;
import raytracer.IntersectionManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SceneTest {

    @Test
    void sceneCreationTest() {
        Scene scene = new Scene();
        // scene should contain no objects
        assertEquals(0, scene.getObjects().size());
    }

    @Test
    void addingUnnamedObjectsToSceneTest() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        scene.addObject(sphere);

        assertTrue(scene.getObjects().contains(sphere));
        assertEquals(sphere, scene.getObjects().get(0));
    }

    @Test
    void defaultSceneTest() {
        Scene scene = Scene.defaultScene();
        var normalSphere = new Sphere();
        var halfSizedSphere = new Sphere();
        halfSizedSphere.setTransformationMatrix(Matrix.scale(0.5,0.5,0.5));

        assertEquals(scene.getObjects().get(0), normalSphere);
        assertEquals(scene.getObjects().get(1), halfSizedSphere);
    }

    @Test
    void intersectSceneWithRayTest() {
        Scene scene = Scene.defaultScene();
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        IntersectionManager xs = scene.traceRay(ray);

        assertEquals(4, xs.count());
        assertEquals(4, xs.get(0).t());
        assertEquals(4.5, xs.get(1).t());
        assertEquals(5.5, xs.get(2).t());
        assertEquals(6, xs.get(3).t());
    }

    /*---------------------------Light sources testing---------------------------*/

    @Test
    void sceneWithLightsTest() {
        Scene scene = new Scene();

        assertTrue(scene.getObjects().isEmpty());
        assertTrue(scene.getLightSources().isEmpty());
    }

    @Test
    void addingLightsToSceneTest() {
        Scene scene = new Scene();
        PointLightSource light = new PointLightSource(new Point(-10, 10, -10), new Color(1, 1, 1));

        scene.addLightSource(light);
        assertTrue(scene.getLightSources().contains(light));
    }

}