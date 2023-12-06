package org.example.utils.application;

import org.example.math.*;
import org.example.objects.*;
import org.example.utils.image.*;

public class RayTracerLauncher {
    public static void main(String[] args) {

        // Sphere
        Sphere sphere = new Sphere();
        sphere.setTransformationMatrix(Matrix.scale(0.5, 0.5, 0.5));
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
        createImage(scene, camera1, "Images/testImage1");

    /*----------------------------------Test 2----------------------------------*/

        // View Transform
        Matrix viewTransform2 = Matrix.viewTransform(
                new Point(0, 0, -10),
                new Point(1, 1, 0),
                new Vector(0, 1, 0));
        // Camera
        Camera camera2 = new Camera(600, 600, 11.4, viewTransform2);

        // Ray Tracer
        createImage(scene, camera2, "Images/testImage2");

    /*----------------------------------Test 3----------------------------------*/

        // View Transform
        Matrix viewTransform3 = Matrix.viewTransform(
                new Point(10,10, -10),
                new Point(0, 0, 0),
                new Vector(0, 1, 0));
        // Camera
        Camera camera3 = new Camera(600, 600, 2.7, viewTransform3);

        // Ray Tracer
        createImage(scene, camera3, "Images/testImage3");

    }

    /*-------------------------------help functions-------------------------------*/

    private static void createImage(Scene scene, Camera camera3, String newName) {
        RayTracer rt = new RayTracer(scene, camera3);
        rt.render();
        rt.getRenderTarget().writeFile(newName);
    }

}
