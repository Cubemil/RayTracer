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

    public boolean isDirectional() {
        return !(this instanceof PointLightSource);
    }

    public Vector directionFromPoint(Point point) {
        if (this instanceof DirectionalLightSource) throw new IllegalCallerException("Only PointLightSources and SpotLightSources can have a position");
        return getPosition().sub(point).normalized();
    }

    public double distanceFromPoint(Point point) {
        return directionFromPoint(point).magnitude();
    }

    public Vector directionToPoint(Point point) {
        if (this instanceof DirectionalLightSource) throw new IllegalCallerException("Only PointLightSources and SpotLightSources can have a position");
        return point.sub(getPosition()).normalized();
    }

    public Color colorAtPoint(Point point) {
        return color.mult(intensity);
    }

    public Point getPosition() {
        throw new IllegalCallerException("Only PointLightSources and SpotLightSources can have a position");
    }

}
