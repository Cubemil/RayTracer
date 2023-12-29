package raytracerTest;

import math.Color;
import org.junit.jupiter.api.Test;
import raytracer.Material;

import static org.junit.jupiter.api.Assertions.*;

class MaterialTest {

    @Test
    void creatingMaterialTest() {
        Color color = new Color(1, 1, 1);
        double ambient = 0.1;
        double diffuse = 0.1;
        double specular = 0.1;
        int shininess = 100;

        Material material = new Material(color, ambient, diffuse, specular, shininess);

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

}