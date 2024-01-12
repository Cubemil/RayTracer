package raytracer.light;

import math.Color;
import math.Point;
import math.Vector;

public class DirectionalLightSource extends LightSource {

    private final Vector direction;

    public DirectionalLightSource(Vector direction, Color color, double intensity) {
        super(color, intensity);
        this.direction = direction;
    }

    public DirectionalLightSource(Vector direction) {
        super();
        this.direction = direction;
    }

    public DirectionalLightSource(Vector direction, Color color) {
        super(color);
        this.direction = direction;
    }

    public DirectionalLightSource(Vector direction, double intensity) {
        super(intensity);
        this.direction = direction;
    }

    @Override
    public boolean isDirectional() {
        return true;
    }

    @Override
    public Vector directionToPoint(Point point) {
        return direction.normalized();
    }

    @Override
    public Vector directionFromPoint(Point point) {
        return direction.neg().normalized();
    }

    @Override
    public double distanceFromPoint(Point point) {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public Color colorAtPoint(Point point) {
        return color.mult(intensity);
    }

    @Override
    public Point getPosition() {
        throw new IllegalCallerException("Only PointLightSources and SpotLightSources can have a position");
    }

    public Vector getDirection() {
        return direction;
    }

}
