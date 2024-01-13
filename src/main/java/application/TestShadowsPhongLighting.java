package application;

import math.*;
import raytracer.*;
import raytracer.light.*;

public class TestShadowsPhongLighting {

    public static void main(String[] args) {
//        threeSpherePhongSceneWithShadows();

        double[] intensities = {0.5, 1, 10};
        for (double i : intensities) {
//            directionalLightSourceThreeSpheres(i);
            spotLightSourceThreeSpheres(i);
        }

    }

    static Matrix viewTransform = Matrix.viewTransform(
        new Point(0, 1.5, -5),
        new Point(0, 1, 0),
        new Vector(0, 1, 0));
    static Camera camera = new Camera(800, 800, 60, viewTransform);

    private static void threeSpherePhongSceneWithShadows() {
        var scene = Scene.threeSpheresScene();
        scene.addLightSource(new PointLightSource(
                new Point(-10, 10, -10),
                new Color(1, 1, 1)));

        createImage("correctedThreeSphereShadowedScene", scene, camera);
    }

    private static void directionalLightSourceThreeSpheres(double intensity) {
        var scene = Scene.threeSpheresScene();
        scene.addLightSource(new DirectionalLightSource(
                new Vector(10.0, -10.0, 10.0),
                intensity));
        createImage("directionalLightSourceThreeSpheres" + intensity + "_", scene, camera);
    }

    private static void spotLightSourceThreeSpheres(double intensity) {
       var scene = Scene.threeSpheresScene();
        scene.addLightSource(new SpotLightSource(
            new Point(-10.0, 10.0, -10.0),
            new Vector(10, -10.0, 10),
            Color.WHITE, intensity,
            30,
            10));
        createImage("spotLightSourceThreeSpheres" + intensity + "_", scene, camera);
    }

    private static void createImage(String fileName, Scene scene, Camera camera) {
        RayTracer rt = new RayTracer(scene, camera);
        rt.render();
        rt.getRenderTarget().writeFile("Images/ShadowsTest/" + fileName);
    }

}
