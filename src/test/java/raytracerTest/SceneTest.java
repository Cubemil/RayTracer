package raytracerTest;

import math.*;
import raytracer.*;
import raytracer.geometry.Sphere;
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

    @Test
    void defaultWorldTest() {
        PointLightSource light = new PointLightSource(new Point(-10, 10, -10), new Color(1, 1, 1));

        Material material = new Material();
            material.setColor(new Color(0.8, 1.0, 0.6));
            material.setDiffuse(0.7);
            material.setSpecular(0.2);

        Sphere s1 = new Sphere();
        s1.setMaterial(material);

        Sphere s2 = new Sphere();
        s2.setTransformationMatrix(Matrix.scale(0.5, 0.5, 0.5));

        Scene scene = Scene.defaultScene();

        assertEquals(light, scene.getLightSources().get(0));
        assertTrue(scene.getObjects().contains(s1));
        assertTrue(scene.getObjects().contains(s2));
    }

}