package raytracerTest;

import math.Color;
import math.Point;
import math.Vector;
import org.junit.jupiter.api.Test;
import raytracer.light.DirectionalLightSource;
import raytracer.light.PointLightSource;

import static org.junit.jupiter.api.Assertions.*;

class LightSourceTest {

    @Test
    void pointLightPositionAndIntensityTest() {
        Color color = new Color(1, 1, 1);
        Point position = new Point(0, 0, 0);
        PointLightSource light = new PointLightSource(position, color);

        assertEquals(color, light.getColor());
        assertEquals(position, light.getPosition());
    }

    /*---------------------new class-structure tests-------------------*/

    @Test
    void pointLightWithStandardParametersTest() {
        PointLightSource light = new PointLightSource(new Point(0, 0, 0));
        Point p = new Point(0, 1, 0);
        assertFalse(light.isDirectional());
        assertEquals(1.0, light.distanceFromPoint(p));
        assertEquals(new Vector(0, 1, 0), light.directionToPoint(p));
        assertEquals(new Vector(0, -1, 0), light.directionFromPoint(p));
        assertEquals(new Color(1, 1, 1), light.colorAtPoint(p));
    }

    @Test
    void pointLightWithCustomParametersTest() {
        PointLightSource light = new PointLightSource(
                new Point(0, 0, 0),
                new Color(0.2, 0.1, 0.3),
                3.0);
        Point p = new Point(0, 1, 0);
        assertFalse(light.isDirectional());
        assertEquals(1.0, light.distanceFromPoint(p));
        assertEquals(new Vector(0, 1, 0), light.directionToPoint(p));
        assertEquals(new Vector(0, -1, 0), light.directionFromPoint(p));
        assertEquals(new Color(0.6, 0.3, 0.9), light.colorAtPoint(p));
    }

    @Test
    void directionalLightWithStandardParametersTest() {
        DirectionalLightSource light = new DirectionalLightSource(new Vector(0, -1, 0));
        Point p = new Point(0, 1, 0);
        assertTrue(light.isDirectional());
        assertEquals(Double.POSITIVE_INFINITY, light.distanceFromPoint(p));
        assertEquals(new Vector(0, -1, 0), light.directionToPoint(p));
        assertEquals(new Vector(0, 1, 0), light.directionFromPoint(p));
        assertEquals(new Color(1, 1, 1), light.colorAtPoint(p));
    }

    @Test
    void directionalLightWithCustomParametersTest() {
        DirectionalLightSource light = new DirectionalLightSource(
                new Vector(0, -1, 0),
                new Color(0.2, 0.1, 0.3),
                3.0);
        Point p = new Point(0, 1, 0);
        assertTrue(light.isDirectional());
        assertEquals(Double.POSITIVE_INFINITY, light.distanceFromPoint(p));
        assertEquals(new Vector(0, -1, 0), light.directionToPoint(p));
        assertEquals(new Vector(0, 1, 0), light.directionFromPoint(p));
        assertEquals(new Color(0.6, 0.3, 0.9), light.colorAtPoint(p));
    }

}