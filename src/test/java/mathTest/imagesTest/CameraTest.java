package mathTest.imagesTest;

import org.example.math.*;
import org.example.utils.image.Camera;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CameraTest {

    @Test
    void creatingDefaultCameraTest() {
        Camera camera = new Camera(160, 120, 90);

        assertEquals(160, camera.getWidth());
        assertEquals(120, camera.getHeight());
        assertEquals(90, camera.getFov());
        assertEquals(Matrix.identityMatrix(new Matrix()), camera.getTransform());
    }

    @Test
    void creatingExternalViewTransformCameraTest() {
        Matrix transform = Matrix.viewTransform(new Point(0, 1, 0), new Point(2, 0, 0), new Vector(0, 0, 1));
        Camera camera = new Camera(20, 10, 90, transform);

        assertEquals(20, camera.getWidth());
        assertEquals(10, camera.getHeight());
        assertEquals(90, camera.getFov());
        assertEquals(transform, camera.getTransform());
    }

    @Test
    void creatingInternalViewTransformCameraTest() {
        Point position = new Point(0, 1, 0);
        Point lookAt = new Point(2, 0, 0);
        Vector up = new Vector(0, 0, 1);

        Camera camera = new Camera(20, 10, 90, position, lookAt, up);

        assertEquals(20, camera.getWidth());
        assertEquals(10, camera.getHeight());
        assertEquals(90, camera.getFov());
        assertEquals(Matrix.viewTransform(position, lookAt, up), camera.getTransform());
    }

    @Test
    void pixelSizeHorizontalCanvasTest() {
        Camera camera = new Camera(200, 125, 90);

        assertEquals(0.01, camera.pixelSize());
    }

    @Test
    void pixelSizeVerticalCanvasTest() {
        Camera camera = new Camera(125, 200, 90);

        assertEquals(0.01, camera.pixelSize());
    }

}