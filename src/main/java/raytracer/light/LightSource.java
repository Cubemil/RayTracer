package raytracer.light;

import math.Color;
import math.Point;
import math.Vector;

public abstract class LightSource {

    protected final Color color;
    protected final double intensity;

    public LightSource() {
        this.color = new Color(1.0, 1.0, 1.0);
        this.intensity = 1.0;
    }

    public LightSource(Color color) {
        this.color = color;
        this.intensity = 1.0;
    }

    public LightSource(double intensity) {
        this.color = new Color(1.0, 1.0, 1.0);
        this.intensity = intensity;
    }

    public LightSource(Color color, double intensity) {
        this.color = color;
        this.intensity = intensity;
    }

    public Color getColor() {
        return color;
    }

    public double getIntensity() {
        return intensity;
    }

    public abstract boolean isDirectional();

    public abstract Vector directionFromPoint(Point point);

    public abstract double distanceFromPoint(Point point);

    public abstract Vector directionToPoint(Point point);

    public abstract Color colorAtPoint(Point point);

    public abstract Point getPosition();

}
