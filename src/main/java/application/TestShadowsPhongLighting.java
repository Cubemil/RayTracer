package application;

import math.Matrix;
import math.Point;
import math.Vector;
import raytracer.Camera;
import raytracer.RayTracer;
import raytracer.Scene;

public class TestShadowsPhongLighting {

    public static void main(String[] args) {
        threeSpherePhongSceneWithShadows("threeSphereShadowedScene");
    }

    private static void threeSpherePhongSceneWithShadows(String fileName) {
        Matrix viewTransform = Matrix.viewTransform(
                new Point(0, 1.5, -5),
                new Point(0, 1, 0),
                new Vector(0, 1, 0));
        Camera camera = new Camera(800, 400, 90, viewTransform);

        Scene scene = Scene.threeSpheresScene();
        createImage(fileName, scene, camera);
    }

    private static void createImage(String fileName, Scene scene, Camera camera) {
        RayTracer rt = new RayTracer(scene, camera);
        rt.render();
        rt.getRenderTarget().writeFile("Images/ShadowsTest/" + fileName);
    }


}
