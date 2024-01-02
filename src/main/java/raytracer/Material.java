package raytracer;

import math.Color;
import math.MUtils;
import math.Point;
import math.Vector;

public class Material {

    /*-------------------------------attributes-------------------------------*/

    private Color color;            // color of the surface

    private double ambient;         // ambient reflection coefficient (0-1) -> share of basic brightness of the scene
    private double diffuse;         // diffuse reflection coefficient (0-1) -> share of matte or diffused reflections
    private double specular;        // specularity reflection coefficient (0-1) -> share of shining/specular reflection
    private int shininess;          // shininess of material

    /*-------------------------------constructors-----------------------------*/

    /**
     * default constructor
     * -> ambient = 0.1
     * -> diffuse = 0.9
     * -> specular = 0.9
     * -> shininess = 200
     * -> color = (1,1,1)
     */
    public Material() {
        this.color = new Color(1, 1, 1);
        this.ambient = 0.1;
        this.diffuse = 0.9;
        this.specular = 0.9;
        this.shininess = 200;
    }

    // custom values Constructor
    public Material(Color color, double ambient, double diffuse, double specular, int shininess) {
        this.color = color;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
    }

    // pink testing material with diffused reflection only
    public static Material TESTING_MATERIAL = new Material(new Color(1, 0, 1), 0, 1, 0, 0);

    /*---------------------------calculation functions------------------------*/

    /**
     * @param lightSource   light source
     * @param position      the object's surface point at which the calculation happens
     *                          -> intersection of ray
     *                          -> gives us the corresponding material
     * @param observer      Vector from intersection point to observer
     * @param normal        normal Vector at the intersection point
     * @return              Color containing the correct lighting conditions given the parameters
     * -------------------------------------------------------------------------
     * > calculates the color after the lighting model Phong
     * > r = reflected observer Vector
     * > l = Vector from position to light source
     * > kA = ambient  part
     * > kD = diffuse  part
     * > kS = specular part
     * > I  = resulting Color
     *      IL = light source color and intensity
     *      O  = material color
     * > I = kA * O + IL * O * kD * (normal * l) + IL * kS * (observer * r) ^ normal
     *
     * -------------------------------------------------------------------------
     */
    public Color phongLighting(LightSource lightSource, Point position, Vector observer, Vector normal) {
        Color lightColor = lightSource.getColor();
        Vector l = lightSource.getPosition().sub(position).normalized();

        return  color.mult(ambient)
                .add(lightColor.mult(color).mult(diffuse).mult(normal.dot(l)))
                .add(lightColor.mult(specular).mult(Math.pow(observer.normalized().dot(l.reflect(normal)), shininess)));
    }

    /*----------------------------structure functions-------------------------*/

    public Color getColor() {
        return color;
    }

    public double getAmbient() {
        return ambient;
    }

    public double getDiffuse() {
        return diffuse;
    }

    public double getSpecular() {
        return specular;
    }

    public int getShininess() {
        return shininess;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setAmbient(double ambient) {
        this.ambient = ambient;
    }

    public void setDiffuse(double diffuse) {
        this.diffuse = diffuse;
    }

    public void setSpecular(double specular) {
        this.specular = specular;
    }

    public void setShininess(int shininess) {
        this.shininess = shininess;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Material other) {
            return this.color.equals(other.getColor()) &&
                    MUtils.approxEqual(this.ambient, other.ambient) &&
                    MUtils.approxEqual(this.diffuse, other.diffuse) &&
                    MUtils.approxEqual(this.specular, other.specular) &&
                    this.shininess == other.shininess;
        }
        return false;
    }

}
