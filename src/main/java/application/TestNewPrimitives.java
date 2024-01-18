package application;

import math.Color;
import math.Matrix;
import math.Point;
import math.Vector;
import raytracer.Camera;
import raytracer.RayTracer;
import raytracer.Scene;
import raytracer.geometry.Plane;
import raytracer.light.PointLightSource;
import raytracer.sampler.*;

public class TestNewPrimitives {

    public static void main(String[] args) {
        simplePlaneTest();

    }

    private static void simplePlaneTest() {
        var scene = threeSphereScene();

        // plane
        scene.addObject(new Plane());
        scene.getObjects().get(3).getMaterial().setReflectance(0.7);
        scene.getObjects().get(3).getMaterial().setSpecular(0);
        scene.getObjects().get(3).getMaterial().setColor(new Color(1, 0.9, 0.9));
        createImage("simplePlaneTest", scene, new NoSampler(), 1);
    }

    private static Scene threeSphereScene() {
        var scene = Scene.threeSpheresScene();
        scene.addLightSource(new PointLightSource(new Point(-10, 10, -10)));

        scene.getObjects().get(0).getMaterial().setReflectance(0);
        scene.getObjects().get(1).getMaterial().setReflectance(0);
        scene.getObjects().get(2).getMaterial().setReflectance(0.7);

        return scene;
    }

    private static void createImage(String fileName, Scene scene, Sampler sampler, int sampleCount) {
        Matrix viewTransform = Matrix.viewTransform(
                new Point(0, 1.5, -5),
                new Point(0, 1, 0),
                new Vector(0, 1, 0));
        Camera camera = new Camera(800, 500, 60, viewTransform);

        scene.setSampler(sampler);
        RayTracer rt = new RayTracer(scene, camera, sampleCount);
        rt.render();
        rt.getRenderTarget().writeFile("Images/TestNewPrimitives/" + fileName);
    }

}
