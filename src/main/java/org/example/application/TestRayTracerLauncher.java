package org.example.application;

import org.example.math.*;
import org.example.objects.*;
import org.example.utils.image.*;

public class TestRayTracerLauncher {
    public static void main(String[] args) {

        // Sphere
        Sphere sphere = new Sphere();
        sphere.setTransformationMatrix(Matrix.scale(0.5,0.5,0.5));

        // Scene
        Scene scene = new Scene();
        scene.addObject(sphere);

/*----------------------------------Test 1----------------------------------*/

        // Camera
        Matrix viewTransform1 = Matrix.viewTransform(
                new Point(0, 0, -10),
                new Point(0, 0, 0),
                new Vector(0, 1, 0));
        Camera camera1 = new Camera(800, 600, 7.7, viewTransform1);

        // Ray Tracer
        createImage(scene, camera1, "Images/TestRayTracer/testImage1");

/*----------------------------------Test 2----------------------------------*/

        // View Transform
        Matrix viewTransform2 = Matrix.viewTransform(
                new Point(0, 0, -10),
                new Point(1, 1, 0),
                new Vector(0, 1, 0));
        // Camera
        Camera camera2 = new Camera(600, 600, 11.4, viewTransform2);

        // Ray Tracer
        createImage(scene, camera2, "Images/TestRayTracer/testImage2");

/*----------------------------------Test 3----------------------------------*/

        // View Transform
        Matrix viewTransform3 = Matrix.viewTransform(
                new Point(10,10, -10),
                new Point(0, 0, 0),
                new Vector(0, 1, 0));
        // Camera
        Camera camera3 = new Camera(600, 600, 2.7, viewTransform3);

        // Ray Tracer
        createImage(scene, camera3, "Images/TestRayTracer/testImage3");

/*-------------------------------Other Tests--------------------------------*/

        // Three spheres test image
        createThreeSpheresImage();

        createSphereGridImage();

    }

    /*-------------------------------help functions-------------------------------*/

    private static void createImage(Scene scene, Camera camera, String newName) {
        RayTracer rt = new RayTracer(scene, camera);
        rt.render();
        rt.getRenderTarget().writeFile(newName);
    }

    private static void createThreeSpheresImage() {
        Scene scene = Scene.threeSpheresScene();

        Camera camera = new Camera(
                800, 400, 60,
                new Point(0.0, 1.5, -5.0),
                new Point(0.0, 1.0, 0.0),
                new Vector(0, 1, 0));

        createImage(scene, camera, "Images/TestRayTracer/threeSpheresImage");
    }

    private static void createSphereGridImage() {
        Scene scene = new Scene();

        for (int y = -3; y <= 3; y++) {
            for (int x = -3; x <= 3; x++) {
                Sphere sphere = new Sphere();
                sphere.setTransformationMatrix(Matrix.translate(x, y, 0).mult(Matrix.scale(0.4,0.4,0.4)));
                scene.addObject(sphere);
            }
        }

        Camera camera = new Camera(
                400, 400, 90,
                new Point(0, 0, -5),
                new Point(0, 0, 0),
                new Vector(0, 1, 0));

        createImage(scene, camera, "Images/TestRayTracer/sphereGridImage");
    }

}
