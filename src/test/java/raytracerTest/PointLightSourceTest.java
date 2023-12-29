package raytracerTest;

import math.Color;
import math.Point;
import org.junit.jupiter.api.Test;
import raytracer.PointLightSource;

import static org.junit.jupiter.api.Assertions.*;

class PointLightSourceTest {

    @Test
    void pointLightPositionAndIntensityTest() {
        Color color = new Color(1, 1, 1);
        Point position = new Point(0, 0, 0);
        PointLightSource light = new PointLightSource(position, color);

        assertEquals(color, light.getColor());
        assertEquals(position, light.getPosition());
    }

}