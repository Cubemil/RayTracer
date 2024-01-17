package raytracer;

import math.Color;
import math.MUtils;
import math.Point;
import math.Vector;
import raytracer.light.LightSource;

public class Material {

    /*-------------------------------attributes-------------------------------*/

    private Color color;            // color of the surface

    private double ambient;         // ambient reflection coefficient (0-1) -> share of basic brightness of the scene
    private double diffuse;         // diffuse reflection coefficient (0-1) -> share of matte or diffused reflections
    private double specular;        // specularity reflection coefficient (0-1) -> share of shining/specular reflection
    private int shininess;          // shininess of material

    // new: reflection
    private double reflectance;

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
        this.reflectance = 0.0;
    }

    // custom values Constructor
    public Material(Color color, double ambient, double diffuse, double specular, int shininess, int reflection) {
        this.color = color;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
        this.reflectance = reflection;
    }

    // pink testing material with diffused reflection only
    public static Material TESTING_MATERIAL = new Material(new Color(1, 0, 1), 0, 1, 0, 0, 0);

    /*---------------------------calculation functions------------------------*/

    /**
     * @param lightSource   light source
     * @param position      the object's surface point at which the calculation happens
     *                          -> intersection of ray
     *                          -> gives us the corresponding material
     * @param observer      Vector from intersection point to viewDirection
     * @param normal        hitNormal Vector at the intersection point
     * @param inShadow      boolean, checks if position contains shadow
     * @return              Color containing the correct lighting conditions given the parameters
     * -------------------------------------------------------------------------
     * > calculates the color after the lighting model Phong
     * > r = reflected viewDirection Vector
     * > l = Vector from position to light source
     * > kA = ambient  part
     * > kD = diffuse  part
     * > kS = specular part
     * > I  = resulting Color
     *      IL = light source color and intensity
     *      O  = material color
     * > I = kA * O + IL * O * kD * (hitNormal * l) + IL * kS * (viewDirection * r) ^ shininess
     *
     * -------------------------------------------------------------------------
     */
    public Color phongLighting(LightSource lightSource, Point position, Vector observer, Vector normal, boolean inShadow) {
        Color lightColor = lightSource.colorAtPoint(position);
        Vector l = lightSource.directionFromPoint(position);

        // O * kA
        var ambientPart = color.scaled(ambient);

        if (inShadow) return ambientPart;

        // (hitNormal * l) -> dot product
        // only have an influence when dot product is positive
        var diffuseDotProduct = l.dot(normal);
        // IL * O * kD * diffuseDotProduct
        Color diffusePart = lightColor.scaled(color).scaled(diffuse).scaled(Math.max(diffuseDotProduct, 0));


        // (viewDirection * r) -> dot product
        var specularDotProduct = l.neg().reflect(normal).dot(observer);

        // default color if dot product is negative
        var specularPart = new Color(0, 0, 0);

        if (specularDotProduct > 0) {
            // specularDot ^ shininess
            var reflectExponent = Math.pow(specularDotProduct, shininess);
            // IL * kS * reflectExponent
            specularPart = lightColor.scaled(specular).scaled(reflectExponent);
        }

        // return Color from the sum of all three parts
        return ambientPart.add(diffusePart.add(specularPart));
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

    public double getReflectance() {
        return reflectance;
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

    public void setReflectance(double reflectance) {
        this.reflectance = reflectance;
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
