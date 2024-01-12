package application;

import math.Color;
import math.Matrix;
import math.Point;
import math.Vector;
import raytracer.Camera;
import raytracer.RayTracer;
import raytracer.Scene;
import raytracer.light.DirectionalLightSource;
import raytracer.light.PointLightSource;

public class TestShadowsPhongLighting {

    public static void main(String[] args) {
        threeSpherePhongSceneWithShadows();

        double[] intensities = {0.5, 1, 10};
        for (double i : intensities) directionalLightSourceThreeSpheres(i);

    }

    private static void threeSpherePhongSceneWithShadows() {
        Matrix viewTransform = Matrix.viewTransform(
                new Point(0, 1.5, -5),
                new Point(0, 1, 0),
                new Vector(0, 1, 0));
        Camera camera = new Camera(800, 400, 90, viewTransform);
        createImage("correctedThreeSphereShadowedScene", Scene.threeSpheresScene(new PointLightSource(new Point(-10, 10, -10), new Color(1, 1, 1))), camera);
    }

    private static void directionalLightSourceThreeSpheres(double intensity) {
        Matrix viewTransform = Matrix.viewTransform(
                new Point(0, 1.5, -5),
                new Point(0, 1, 0),
                new Vector(0, 1, 0));
        Camera camera = new Camera(800, 800, 60, viewTransform);
        createImage("directionalLightSourceThreeSpheres" + intensity, Scene.threeSpheresScene(new DirectionalLightSource(new Vector(10.0, -10.0, 10.0), intensity)), camera);
    }

    private static void createImage(String fileName, Scene scene, Camera camera) {
        RayTracer rt = new RayTracer(scene, camera);
        rt.render();
        rt.getRenderTarget().writeFile("Images/ShadowsTest/" + fileName);
    }

}
