package org.example.utils.image;

import org.example.math.Matrix;
import org.example.objects.Shape;
import org.example.objects.Sphere;

import java.util.ArrayList;
import java.util.Arrays;

public class Scene {

    /*------------------------------------attributes------------------------------------*/

    private ArrayList<Shape> objects = new ArrayList<>();

    /*TODO
    private ArrayList<LightSource> lightSources = new ArrayList<>();
    private ArrayList<Material> materials = new ArrayList<>();
    */

    /*-----------------------------------constructors-----------------------------------*/



    /*--------------------------------calculation methods-------------------------------*/

    

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
        this.objects.addAll(Arrays.asList(newShape));
    }

    /**
     * @return reference to object ArrayList
     */
    public ArrayList<Shape> getObjects() {
        return objects;
    }

}
