package application;

import math.*;
import raytracer.*;
import raytracer.light.*;

public class TestShadowsPhongLighting {

    public static void main(String[] args) {
//        // test values vary - do not run all at once //
//        threeSpherePhongSceneWithShadows();
//
     /*   double[] intensities = {0.5, 1, 10};
        for (double i : intensities) {
////            directionalLightSourceThreeSpheres(i);
            spotLightThreeSpheres(i);
////            attenuatedPointLightThreeSpheres(i);
        }*/

        multipleLightsThreeSpheres();

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

    private static void spotLightThreeSpheres(double intensity) {
        Point position = new Point(-10.0, 10.0, -10.0);
        var scene = Scene.threeSpheresScene();

        scene.addLightSource(new SpotLightSource(
            position,
            Point.ZERO.sub(position),
            Color.WHITE, intensity,
            30,
            10));

        createImage("spotLightSourceThreeSpheres" + intensity + "_", scene, camera);
    }

    private static void attenuatedPointLightThreeSpheres(double intensity) {
        var scene = Scene.threeSpheresScene();
        scene.addLightSource(new PointLightSource(new Point(-10, 10, -10), intensity));

        createImage("attenuatedPointLightThreeSpheres" + intensity + "-", scene, camera);
    }

    private static void multipleLightsThreeSpheres() {
        var scene = Scene.threeSpheresScene();
        scene.addLightSource(
            new DirectionalLightSource(
                new Vector(0, -10, 10), Color.WHITE, 0.6),
            new SpotLightSource(
                new Point(-10, 10, -2),
                new Point(-1.5, 0.33, -0.75).sub(new Point(-10, 10, -2)),
                new Color(Color.BLUE), 1, 10, 7),
            new SpotLightSource(
                new Point(10, 10, 5),
                new Point(1.5, 0.5, -0.5).sub(new Point(10, 10, 5)),
                new Color(Color.ORANGE), 1, 10, 7));

        createImage("multipleLightsThreeSpheres", scene, camera);
    }

    private static void createImage(String fileName, Scene scene, Camera camera) {
        RayTracer rt = new RayTracer(scene, camera);
        rt.render();
        rt.getRenderTarget().writeFile("Images/ShadowsTest/" + fileName);
    }

}
