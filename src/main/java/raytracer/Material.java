package raytracer;

import math.Color;
import math.MUtils;

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
