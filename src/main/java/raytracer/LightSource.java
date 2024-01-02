package raytracer;

import math.Color;
import math.Point;

public class LightSource {

    private final Point position;
    private final Color color;
    private final double intensity;

    public LightSource(Point position, Color color) {
        this.position = position;
        this.color = color;
        this.intensity = 1;
    }

    public LightSource(Point position, Color color, double intensity) {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
    }

    public Point getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public double getIntensity() {
        return intensity;
    }



}
