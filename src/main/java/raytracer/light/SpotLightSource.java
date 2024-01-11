package raytracer.light;

import math.Color;
import math.Point;
import math.Vector;

public class SpotLightSource extends LightSource{

    private final Point position;
    private final Vector direction;

    public SpotLightSource(Point position, Vector direction, Color color, double intensity) {
        super(color, intensity);
        this.position = position;
        this.direction = direction;
    }

    public SpotLightSource(Point position, Vector direction) {
        super();
        this.position = position;
        this.direction = direction;
    }

    public SpotLightSource(Point position, Vector direction, Color color) {
        super(color);
        this.position = position;
        this.direction = direction;
    }

    public SpotLightSource(Point position, Vector direction, double intensity) {
        super(intensity);
        this.position = position;
        this.direction = direction;
    }

    public Point getPosition() {
        return position;
    }

    public Vector getDirection() {
        return direction;
    }

    private double totalAngleDegrees() {
        return 0.0;
    }

    private double fallOffStartDegrees() {
        return 0.0;
    }
}
