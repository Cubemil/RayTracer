package raytracer.light;

import math.Point;
import math.Color;
import math.Vector;

public class PointLightSource extends LightSource {

    private final Point position;

    public PointLightSource(Point position, Color color) {
        super(color);
        this.position = position;
    }

    public PointLightSource(Point position) {
        this.position = position;
    }

    public PointLightSource(Point position, Color color, double intensity) {
        super(color, intensity);
        this.position = position;
    }

    @Override
    public Vector directionFromPoint(Point point) {
        return position.sub(point).normalized();
    }

    @Override
    public Vector directionToPoint(Point point) {
        return point.sub(position).normalized();
    }

    @Override
    public double distanceFromPoint(Point point) {
        return directionFromPoint(point).magnitude();
    }

    @Override
    public Color colorAtPoint(Point point) {
        return color.mult(intensity);
    }

    @Override
    public boolean isDirectional() {
        return false;
    }

    public Point getPosition() {
        return this.position;
    }

}
