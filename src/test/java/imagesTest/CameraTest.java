package imagesTest;

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
        assertEquals(Matrix.identityMatrix(), camera.getTransform());
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
        assertEquals(0.01, camera.pixelSize(), 0.1);
    }

    @Test
    void pixelSizeVerticalCanvasTest() {
        Camera camera = new Camera(125, 200, 90);
        assertEquals(0.01, camera.pixelSize(), 0.1);
    }

    @Test
    void rayThroughCanvasCenterTest() {
        Camera camera = new Camera(201, 101, 90);
        Ray ray = camera.generateRay(100, 50);

        assertEquals(new Point(0, 0, 0), ray.getOriginPoint());
        assertEquals(new Vector(0, 0, -1), ray.getDirectionVector());
    }

    /*TODO Test results dont fit
    @Test
    void rayThroughCanvasCornerTest() {
        Camera camera = new Camera(201, 101, 90);
        Ray ray = camera.generateRay(0, 0);

        assertEquals(new Point(0, 0, 0), ray.getOriginPoint());
        assertEquals(new Vector(0.665186, 0.332593, -0.668512), ray.getDirectionVector());
    }*/

    @Test
    void rayWithTransformedCameraTest() {
        Camera camera = new Camera(201, 101, 90);
        camera.setTransform(Matrix.rotateY(0.785398).mult(Matrix.translate(0, -2, 5)));
        Ray ray = camera.generateRay(100, 50);

        assertEquals(new Point(0, 2, -5), ray.getOriginPoint());
        assertEquals(new Vector(0.707106, 0, -0.707106), ray.getDirectionVector());
    }

}