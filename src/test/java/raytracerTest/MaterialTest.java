package raytracerTest;

import math.Color;
import math.Point;
import math.Vector;
import org.junit.jupiter.api.Test;
import raytracer.Material;
import raytracer.light.PointLightSource;

import static org.junit.jupiter.api.Assertions.*;

class MaterialTest {

    @Test
    void creatingMaterialTest() {
        Color color = new Color(1, 1, 1);
        double ambient = 0.1;
        double diffuse = 0.1;
        double specular = 0.1;
        int shininess = 100;
        int reflection = 0;

        Material material = new Material(color, ambient, diffuse, specular, shininess, reflection);

        assertEquals(0.1, material.getAmbient());
        assertEquals(0.1, material.getDiffuse());
        assertEquals(0.1, material.getSpecular());
        assertEquals(color, material.getColor());
        assertEquals(100, material.getShininess());
    }

    @Test
    void defaultMaterialTest() {
        Material material = new Material();

        assertEquals(0.1, material.getAmbient());
        assertEquals(0.9, material.getDiffuse());
        assertEquals(0.9, material.getSpecular());
        assertEquals(new Color(1, 1, 1), material.getColor());
        assertEquals(200, material.getShininess());
    }

    @Test
    void reflectivityTest() {
        Material material = new Material();
        assertEquals(0.0, material.getReflectance());
    }

    /*-------------------shadow on intersection tests----------------------*/

    Material material = new Material();
    Point position = new Point(0, 0, 0);

    @Test
    void lightingWithSurfaceInShadowTest() {
        Vector eyeV = new Vector(0, 0, -1);
        Vector normalV = new Vector(0, 0, -1);
        PointLightSource light = new PointLightSource(new Point(0, 0, -10), new Color(1, 1, 1));

        boolean isShadow = true;
        var result = material.phongLighting(
                light,
                position,
                eyeV,
                normalV,
                isShadow);

        assertEquals(new Color(0.1, 0.1, 0.1), result);
    }

}