package application;

import math.Matrix;
import math.Point;
import math.Vector;
import raytracer.Camera;
import raytracer.RayTracer;
import raytracer.Scene;
import raytracer.geometry.Shape;
import raytracer.light.PointLightSource;

public class TestReflectionAndAntiAliasing {

    public static void main(String[] args) {

        reflectiveSpheres();

    }

    static Matrix viewTransform = Matrix.viewTransform(
            new Point(0, 1.5, -5),
            new Point(0, 1, 0),
            new Vector(0, 1, 0));
    static Camera camera = new Camera(800, 500, 60, viewTransform);

    private static void reflectiveSpheres() {
        var scene = Scene.threeSpheresScene();

        for (Shape s : scene.getObjects()) s.getMaterial().setReflectance(1);

        scene.addLightSource(new PointLightSource(new Point(-10, 10, -10)));
        createImage("reflectiveSpheres", scene, camera);
    }

    private static void createImage(String fileName, Scene scene, Camera camera) {
        RayTracer rt = new RayTracer(scene, camera);
        rt.render();
        rt.getRenderTarget().writeFile("Images/ReflectionsTest/" + fileName);
    }


}
