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

    private ArrayList<Shape> objects = new ArrayList<>();

    /*TODO
    private ArrayList<LightSource> lightSources = new ArrayList<>();
    private ArrayList<Material> materials = new ArrayList<>();
    */

    /*-----------------------------------constructors-----------------------------------*/



    /*--------------------------------calculation methods-------------------------------*/

    /**
     * @param ray current Ray to send through the scene
     * @return a new IntersectionManager
     * -> contains every Intersection of the ray with every object in the scene
     */
    public IntersectionManager traceRay(Ray ray) {
        assert objects != null : "Objects list not initialized";

        var intersectionList = objects.stream().map(o -> o.intersect(ray)).collect(Collectors.toCollection(ArrayList::new));
        return combineIntersections(intersectionList);
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