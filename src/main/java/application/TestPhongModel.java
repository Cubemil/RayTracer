package application;

import math.*;
import raytracer.*;
import raytracer.geometry.Sphere;
import raytracer.light.PointLightSource;

public class TestPhongModel {

    public static void main(String[] args) {

        /*----------------------------phong parts images--------------------------*/

        var light = new PointLightSource(new Point(-10, 10, -10), new Color(1, 1, 1));


        Material mat = new Material();
        mat.setColor(new Color(1, 0.2, 1));

        // only ambient
        mat.setDiffuse(0);
        mat.setSpecular(0);
        singleSpherePhong(mat, "ambientPhong");

        // only diffuse
        mat.setAmbient(0);
        mat.setDiffuse(0.9);
        singleSpherePhong(mat, "diffusePhong");

        // only specular
        mat.setDiffuse(0);
        mat.setSpecular(0.9);
        singleSpherePhong(mat, "specularPhong");

        // all parts added
        mat.setAmbient(0.1);
        mat.setDiffuse(0.9);
        singleSpherePhong(mat, "normalPhong");

        /*----------------------------scenes with lighting--------------------------*/

        threeSpherePhongScene("threeSpheresNoMaterial");

        Material yellow = new Material();
        yellow.setColor(Color.YELLOW);
        Material green = new Material();
        green.setColor(Color.GREEN);
        Material lime = new Material();
        lime.setColor(Color.NEON_GREEN);
        threeSpherePhongScene("threeSpheresYellowGreenLime", green, lime, yellow);

    }

    /*----------------------------help functions--------------------------*/

    private static void singleSpherePhong(Material mat, String imgName) {
        Scene scene = new Scene();
        scene.addLightSource(new PointLightSource(new Point(-10, 10, -10), new Color(1, 1, 1), 1));

        Sphere sphere = new Sphere();
        sphere.setMaterial(mat);
        scene.addObject(sphere);

        var viewTransform = Matrix.viewTransform(new Point(0, 0, -2), new Point(0, 0, 0), new Vector(0, 1, 0));
        Camera camera = new Camera(800, 800, 90, viewTransform);

        createImage(scene, camera, imgName);
    }

    private static void threeSpherePhongScene(String imgName, Material... materials) {
        Scene scene = Scene.threeSpheresScene();
        scene.addLightSource(new PointLightSource(new Point(-10, 10, -10), Color.WHITE, 1));

        if (materials.length != 0) {
            scene.getObjects().get(0).setMaterial(materials[0]);
            scene.getObjects().get(1).setMaterial(materials[1]);
            scene.getObjects().get(2).setMaterial(materials[2]);
        }

        Camera camera = new Camera(
                800, 400, 60,
                new Point(0.0, 1.5, -5.0),
                new Point(0.0, 1.0, 0.0),
                new Vector(0, 1, 0));

        createImage(scene, camera, imgName);
    }

    private static void createImage(Scene scene, Camera camera, String imgName) {
        RayTracer rt = new RayTracer(scene, camera);
        rt.render();
        rt.getRenderTarget().writeFile("Images/PhongModelTest/" + imgName);
    }

}
