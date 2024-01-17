package application;

import math.Matrix;
import math.Point;
import math.Vector;
import raytracer.Camera;
import raytracer.RayTracer;
import raytracer.Scene;
import raytracer.light.PointLightSource;
import raytracer.sampler.NoSampler;
import raytracer.sampler.OffsetSampler;
import raytracer.sampler.RandomSampler;
import raytracer.sampler.Sampler;

public class TestReflectionAndAntiAliasing {

    public static void main(String[] args) {

//        noSamplerThreeSpheres();
//        offsetSamplerScene();
        randomSamplerScene();

    }

    static Matrix viewTransform = Matrix.viewTransform(
            new Point(0, 1.5, -5),
            new Point(0, 1, 0),
            new Vector(0, 1, 0));
    static Camera camera = new Camera(800, 500, 60, viewTransform);

    public static void noSamplerThreeSpheres() {
        var scene = threeSphereScene();
        createImage("noSampler", scene, camera, new NoSampler(), 1);
    }

    public static void offsetSamplerScene() {
        var scene = threeSphereScene();
        createImage("offsetSampler", scene, camera, new OffsetSampler(), 9);
    }

    private static void randomSamplerScene() {
        var scene = threeSphereScene();
        createImage("randomSampler", scene, camera, new RandomSampler(), 9);
    }

    private static void createImage(String fileName, Scene scene, Camera camera, Sampler sampler, int sampleCount) {
        scene.setSampler(sampler);
        RayTracer rt = new RayTracer(scene, camera, sampleCount);
        rt.render();
        rt.getRenderTarget().writeFile("Images/ReflectionsTest/" + fileName);
    }

    private static Scene threeSphereScene() {
        var scene = Scene.threeSpheresScene();
        scene.addLightSource(new PointLightSource(new Point(-10, 10, -10)));

        scene.getObjects().get(0).getMaterial().setReflectance(0);
        scene.getObjects().get(1).getMaterial().setReflectance(0);
        scene.getObjects().get(2).getMaterial().setReflectance(0.7);
        scene.getObjects().get(3).getMaterial().setReflectance(0.7);

        return scene;
    }

}
