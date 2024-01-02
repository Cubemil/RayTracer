package raytracer;

import math.*;
import raytracer.geometry.Sphere;
import raytracer.geometry.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static math.Matrix.scale;

public class Scene {

    /*------------------------------------attributes------------------------------------*/

    private final ArrayList<Shape> objects = new ArrayList<>();

    private final ArrayList<LightSource> lightSources = new ArrayList<>();

    private final ArrayList<Material> materials = new ArrayList<>();

    /*-------------------------------predefined scenes-------------------------------*/

    /**
     * @return a default test scene with:
     * -> normal, non-transformed Sphere
     *      -> has certain custom Material
     * -> Sphere scaled 1/2 in all directions
     *      -> has default Material
     * -> PointLightSource at a default position with intensity of 1 and white
     */
    public static Scene defaultScene() {
        // custom Material
        Material material = new Material();
            material.setColor(new Color(0.8, 1.0, 0.6));
            material.setDiffuse(0.7);
            material.setSpecular(0.2);

        // s1 Sphere with custom Material / no transformation
        Sphere normalSphere = new Sphere();
        normalSphere.setMaterial(material);

        // s2 Sphere with default Material / scaled 0.5 in every direction
        Sphere halfSizedSphere = new Sphere();
        halfSizedSphere.setTransformationMatrix(scale(0.5, 0.5, 0.5));

        // create Scene object and add s1, s2
        Scene defaultScene = new Scene();
        defaultScene.addObject(normalSphere, halfSizedSphere);

        // also add a custom Point Light Source
        defaultScene.addLightSource(new PointLightSource(new Point(-10, 10, -10), new Color(1, 1, 1)));

        return defaultScene;
    }

    /**
     * @return scene with three different spheres for testing
     */
    public static Scene threeSpheresScene() {
        Scene scene = new Scene();
        Sphere[] spheres = new Sphere[]{new Sphere(), new Sphere(), new Sphere()};

        spheres[0].setTransformationMatrix(Matrix.translate(-0.5, 1, 0.5));
        spheres[1].setTransformationMatrix(Matrix.translate(1.5, 0.5, -0.5).mult(scale(0.5, 0.5, 0.5)));
        spheres[2].setTransformationMatrix(Matrix.translate(-1.5, 0.33, -0.75).mult(scale(0.33,0.33,0.33)));

        for (Sphere s : spheres) scene.addObject(s);
        return scene;
    }

    /*--------------------------------calculation methods-------------------------------*/

    /**
     * @param ray current Ray to send through the scene
     * @return a new IntersectionManager
     * -> contains every Intersection of the ray with every object in the scene
     */
    public IntersectionManager traceRay(Ray ray) {
        return IntersectionManager.combineIntersections(objects.stream().map(o -> o.intersect(ray)).collect(Collectors.toCollection(ArrayList::new)));
    }

    /*---------------------------------structure methods--------------------------------*/

    /**
     * @param shape varargs to add any objects to the scene
     */
    public void addObject(Shape... shape) {
        if (shape == null) throw new IllegalArgumentException("shape cannot be null");
        this.objects.addAll(Arrays.asList(shape));
    }

    /**
     * @param light varargs to add any light sources to the scene
     */
    public void addLightSource(LightSource... light) {
        if (light == null) throw new IllegalArgumentException("light cannot be null");
        this.lightSources.addAll(Arrays.asList(light));
    }

    /**
     * @return reference to object ArrayList
     * access to objects in list by .get(index) -> 0 indexed
     */
    public ArrayList<Shape> getObjects() {
        return objects;
    }

    /**
     * @return reference to light sources ArrayList
     * access to lights in list by .get(index) -> 0 indexed
     */
    public ArrayList<LightSource> getLightSources() {
        return lightSources;
    }

}