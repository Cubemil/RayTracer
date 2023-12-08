package org.example.utils.image;

import org.example.math.*;
import org.example.objects.*;
import org.example.utils.intersections.IntersectionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.example.utils.intersections.IntersectionManager.combineIntersections;

public class Scene {

    /*------------------------------------attributes------------------------------------*/

    private final ArrayList<Shape> objects = new ArrayList<>();

    /*TODO
    private ArrayList<LightSource> lightSources = new ArrayList<>();
    private ArrayList<Material> materials = new ArrayList<>();
    */

    /*--------------------------------calculation methods-------------------------------*/

    /**
     * @param ray current Ray to send through the scene
     * @return a new IntersectionManager
     * -> contains every Intersection of the ray with every object in the scene
     */
    public IntersectionManager traceRay(Ray ray) {
        return combineIntersections(objects.stream().map(o -> o.intersect(ray)).collect(Collectors.toCollection(ArrayList::new)));
    }

    /*---------------------------------structure methods--------------------------------*/

    /**
     * @return a default test scene with:
     * -> normal, non-transformed Sphere
     * -> Sphere scaled 1/2 in all directions
     */
    public static Scene defaultScene() {
        var defaultScene = new Scene();
        var normalSphere = new Sphere();
        var halfSizedSphere = new Sphere();
        halfSizedSphere.setTransformationMatrix(Matrix.scale(0.5, 0.5, 0.5));

        defaultScene.addObject(normalSphere, halfSizedSphere);
        return defaultScene;
    }

    /**
     * @return scene with three different spheres for testing
     */
    public static Scene threeSpheresScene() {
        Scene scene = new Scene();

        Sphere sphere1 = new Sphere(new Point(-0.5, 1, 0.5), 1);
        Sphere sphere2 = new Sphere(new Point(1.5,0.5,-0.5), 0.5);
        Sphere sphere3 = new Sphere(new Point(-1.5, 0.33, -0.75), 0.33);

        scene.addObject(sphere1, sphere2, sphere3);
        return scene;
    }

    /**
     * @param newShape varargs to add any objects to the scene
     */
    public void addObject(Shape... newShape) {
        if (newShape == null) throw new IllegalArgumentException("newShape cannot be null");
        this.objects.addAll(Arrays.asList(newShape));
    }

    /**
     * @return reference to object ArrayList
     */
    public ArrayList<Shape> getObjects() {
        return objects;
    }

}