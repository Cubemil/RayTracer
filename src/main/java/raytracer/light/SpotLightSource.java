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
        // Color of surface point scaled with light intensity
        Color baseColor = color.mult(intensity);

        // direction of light -> normalised
        Vector lightDirection = direction.normalized();
        // direction to surface point -> normalised
        Vector pointDirection = directionToPoint(point);
        double centerToPoint = Math.acos(lightDirection.dot(pointDirection));

        // point outside light cone
        if (centerToPoint > totalAngleDegrees / 2) return Color.BLACK;
        // point in illuminated center
        if (centerToPoint < fallOffStartDegrees / 2) return baseColor;

        // light direction scaled with radians of cone edge -> normalised
        Vector outerEdgeDirection = lightDirection.mult(Math.toRadians(totalAngleDegrees)).normalized();
        // direction of start of fall-off region -> normalised
        Vector fallOffDirection = lightDirection.mult(Math.toRadians(fallOffStartDegrees)).normalized();

        double pointToEdge = Math.acos(pointDirection.dot(outerEdgeDirection));
        double fallOffToEdge = Math.acos(fallOffDirection.dot(outerEdgeDirection));
        double attenuationPortion = pointToEdge / fallOffToEdge;

        // Point is in the fall-off region, apply attenuation
        return baseColor.mult(Math.pow(attenuationPortion, 2));
    }

    public Point getPosition() {
        return position;
    }

}
