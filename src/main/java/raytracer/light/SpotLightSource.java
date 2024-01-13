package raytracer.light;

import math.Color;
import math.Point;
import math.Vector;

public class SpotLightSource extends LightSource{

    private final Point position;
    private final Vector direction;
    private final double totalAngleDegrees;
    private final double fallOffStartDegrees;

    public SpotLightSource(Point position, Vector direction, Color color, double intensity, double totalAngleDegrees, double fallOffStartDegrees) {
        super(color, intensity);
        this.position = position;
        this.direction = direction;
        this.totalAngleDegrees = totalAngleDegrees;
        this.fallOffStartDegrees = fallOffStartDegrees;
    }

    public SpotLightSource(Point position, Vector direction, double totalAngleDegrees, double fallOffStartDegrees) {
        this.position = position;
        this.direction = direction;
        this.totalAngleDegrees = totalAngleDegrees;
        this.fallOffStartDegrees = fallOffStartDegrees;
    }

    @Override
    public boolean isDirectional() {
        return false;
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
        // angle between direction of light and direction to surface point
        double directionAndPointAngle = direction.normalized().dot(directionToPoint(point));
        Color baseColor = color.mult(intensity);

        // point outside light cone
        if (directionAndPointAngle > totalAngleDegrees / 2) return Color.BLACK;
        // point in illuminated center
        if (directionAndPointAngle < fallOffStartDegrees / 2) return baseColor;

        // Point is in the fall-off region, apply attenuation
        double falloffFactor = Math.cos(directionAndPointAngle - fallOffStartDegrees);
        // base color * part of inside angle to outside ^2
        return baseColor.mult(falloffFactor);

    }

    public Point getPosition() {
        return position;
    }

    public Vector getDirection() {
        return direction;
    }

    private double getTotalAngleDegrees() {
        return totalAngleDegrees;
    }

    private double getFallOffStartDegrees() {
        return fallOffStartDegrees;
    }
}
