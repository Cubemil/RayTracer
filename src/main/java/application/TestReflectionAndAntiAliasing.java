package application;

import math.Matrix;
import math.Point;
import math.Vector;
import raytracer.Camera;
import raytracer.RayTracer;
import raytracer.Scene;
import raytracer.light.PointLightSource;
import raytracer.sampler.RandomSampler;
import raytracer.sampler.Sampler;

public class TestReflectionAndAntiAliasing {

    public static void main(String[] args) {

        offsetSamplerTestThreeSpheres();

    }

    static Matrix viewTransform = Matrix.viewTransform(
            new Point(0, 1.5, -5),
            new Point(0, 1, 0),
            new Vector(0, 1, 0));
    static Camera camera = new Camera(800, 500, 60, viewTransform);

    private static void offsetSamplerTestThreeSpheres() {
        var scene = Scene.threeSpheresScene();

        scene.getObjects().get(0).getMaterial().setReflectance(0);
        scene.getObjects().get(1).getMaterial().setReflectance(0);

        scene.getObjects().get(2).getMaterial().setReflectance(0.7);
        scene.getObjects().get(3).getMaterial().setReflectance(0.7);

        scene.addLightSource(new PointLightSource(new Point(-10, 10, -10)));
        createImage("randomSampler", scene, camera, new RandomSampler(), 9);
    }

    private static void createImage(String fileName, Scene scene, Camera camera, Sampler sampler, int sampleCount) {
        RayTracer rt = new RayTracer(scene, camera, sampler, sampleCount);
        rt.render();
        rt.getRenderTarget().writeFile("Images/ReflectionsTest/" + fileName);
    }


}
