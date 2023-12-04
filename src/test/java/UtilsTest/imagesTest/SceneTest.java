package utilsTest.imagesTest;

import org.example.math.Matrix;
import org.example.objects.Sphere;
import org.example.utils.image.Scene;
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

}