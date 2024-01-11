package raytracer;

import math.*;
import raytracer.geometry.*;
import raytracer.light.LightSource;
import raytracer.light.PointLightSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Scene {

    /*------------------------------------attributes------------------------------------*/

    private final ArrayList<Shape> objects = new ArrayList<>();

    private final ArrayList<LightSource> lightSources = new ArrayList<>();

    private final ArrayList<Material> materials = new ArrayList<>();

    /*-------------------------------predefined scenes-------------------------------*/

    /**
     * @return a default test scene with:
     * -> hitNormal, non-transformed Sphere
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
        halfSizedSphere.setTransformationMatrix(Matrix.scale(0.5, 0.5, 0.5));

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
        Sphere[] spheres = new Sphere[]{new Sphere(), new Sphere(), new Sphere(), new Sphere()};

        spheres[0].setTransformationMatrix(Matrix.translate(-0.5, 1, 0.5));
        spheres[1].setTransformationMatrix(Matrix.translate(1.5, 0.5, -0.5).mult(Matrix.scale(0.5, 0.5, 0.5)));
        spheres[2].setTransformationMatrix(Matrix.translate(-1.5, 0.33, -0.75).mult(Matrix.scale(0.33,0.33,0.33)));
        spheres[3].setTransformationMatrix(Matrix.scale(10, 0.01, 10));

        // also add a custom Point Light Source
        scene.addLightSource(new PointLightSource(new Point(-10, 10, -10), new Color(1, 1, 1)));

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
        return IntersectionManager
            .combineIntersections(objects.stream()
            .map(o -> o.intersect(ray))
            .collect(Collectors
            .toCollection(ArrayList::new)));
    }

    /**
     * @param info HitInfo from an Intersection
     * @return Color from calling Material.phongLighting,
     * but with checking if 'point' is shadowed
     * -> point > hit + normalVector * epsilon
     */
    public Color shadeHit(HitInfo info) {
        double EPSILON = 0.0001;
        Point point = info.hit().add(info.hitNormal().mult(EPSILON));

        return info.object().getMaterial().phongLighting(
                lightSources.get(0),
                info.hit(),
                info.viewDirection(),
                info.hitNormal(),
                isShadowed(point));
    }

    /**
     * @param point to check
     * @return whether the point should have a shadow or not
     * 1. create ray from point to light source
     * 2. calculate intersection points of the ray in this scene
     * if there is an intersection, and it's closer to the point that the light
     * (t < distance point->light), then this point has a shadow
     */
    public boolean isShadowed(Point point) {

        // ray from point to light
        Vector direction = lightSources.get(0).getPosition().sub(point);
        Ray ray = new Ray(point, direction.normalized());
        // distance point to light
        var distance = direction.magnitude();

        // get all Intersections
        IntersectionManager xs = traceRay(ray);
        // no Intersections
        if (xs.count() == 0) return false;
        // check if any Intersection's t-value < distance
        for (Intersection x : xs.getIntersections())
            //TODO t > 0 check
            if (x.t() < distance && x.t() > 0) return true;

        return false;
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