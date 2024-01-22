package application;

import math.Color;
import math.Matrix;
import math.Point;
import math.Vector;
import raytracer.Camera;
import raytracer.RayTracer;
import raytracer.Scene;
import raytracer.geometry.Cube;
import raytracer.geometry.Plane;
import raytracer.geometry.Shape;
import raytracer.geometry.Sphere;
import raytracer.light.PointLightSource;
import raytracer.sampler.*;

public class TestNewPrimitives {

    public static void main(String[] args) {
//        simplePlaneTest();
        cubePlaneAndSpheresSceneTest();
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

    private static void cubePlaneAndSpheresSceneTest() {
        var scene = cubePlaneAndSpheresScene();
        createImage("cubePlaneAndSpheresSceneTest", scene, new RandomSampler(), 9);
    }

    private static Scene threeSphereScene() {
        var scene = Scene.threeSpheresScene();
        scene.addLightSource(new PointLightSource(new Point(-10, 10, -10)));
        scene.getObjects().get(0).getMaterial().setReflectance(0);
        scene.getObjects().get(1).getMaterial().setReflectance(0);
        scene.getObjects().get(2).getMaterial().setReflectance(0.7);
        return scene;
    }

    private static Scene cubePlaneAndSpheresScene() {
        var scene = new Scene();
        Shape[] shapes = new Shape[]{new Sphere(), new Sphere(), new Cube(), new Plane()};

        // small yellow sphere
        shapes[0].setTransformationMatrix(
                Matrix.translate(-1.5, 0.33, -0.75)
                .mult(Matrix.scale(0.33,0.33,0.33)));
        shapes[0].getMaterial().setColor(Color.YELLOW);
        shapes[0].getMaterial().setReflectance(0.7);

        // big cyan sphere
        shapes[1].setTransformationMatrix(
                Matrix.translate(-0.5, 1, 0.5));
        shapes[1].getMaterial().setColor(Color.CORNFLOWERBLUE);
        shapes[1].getMaterial().setReflectance(0);

        // half sized green cube
        shapes[2].setTransformationMatrix(
                Matrix.translate(1.5, 0.5, -0.5)
                .mult(Matrix.scale(0.5, 0.5, 0.5))
                .mult(Matrix.rotateY(15)));
        shapes[2].getMaterial().setColor(Color.GREEN);
        shapes[2].getMaterial().setReflectance(0);

        // white ground plane
        shapes[3].getMaterial().setReflectance(0.7);
        shapes[3].getMaterial().setSpecular(0);
        shapes[3].getMaterial().setColor(new Color(1, 0.9, 0.9));

        // add objects
        for (Shape s : shapes) scene.addObject(s);
        // add light
        scene.addLightSource(new PointLightSource(new Point(-10, 10, -10)));
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
