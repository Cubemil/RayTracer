package org.example.application;

import org.example.math.*;
import org.example.objects.*;
import org.example.utils.image.*;

public class RayTracerLauncher {
    public static void main(String[] args) {

        // Sphere
        Sphere sphere = new Sphere(new Point(0,0,0), 0.5);
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

/*-------------------------------Other Tests--------------------------------*/
        // Three spheres test image
        createThreeSpheresImage();

    }


    /*-------------------------------help functions-------------------------------*/

    private static void createImage(Scene scene, Camera camera, String newName) {
        RayTracer rt = new RayTracer(scene, camera);
        rt.render();
        rt.getRenderTarget().writeFile(newName);
    }

    private static void createThreeSpheresImage() {
        Sphere sphere1 = new Sphere(new Point(-0.5, 1, 0.5), 1);
        Sphere sphere2 = new Sphere(new Point(1.5,0.5,-0.5), 0.5);
        Sphere sphere3 = new Sphere(new Point(-1.5, 0.33, -0.75), 0.33);
        Scene scene = new Scene();

        scene.addObject(sphere1, sphere2, sphere3);
        Camera camera = new Camera(
                800, 400, 60,
                new Point(0.0, 1.5, -5.0),
                new Point(0.0, 1.0, 0.0),
                new Vector(0, 1, 0));

        createImage(scene, camera, "Images/threeSpheresImage");
    }

}
