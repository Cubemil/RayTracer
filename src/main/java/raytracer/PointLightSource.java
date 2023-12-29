package raytracer;

import math.MUtils;
import math.Point;
import math.Color;

public class PointLightSource extends LightSource {

    private final Point position;
    private final Color color;
    private double intensity;

    public PointLightSource(Point position, Color color) {
        this.position = position;
        this.color = color;
        this.intensity = 1;
    }

    public PointLightSource(Point position, Color color, double intensity) {
        this(position, color);
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PointLightSource other) {
            return  this.color.equals(other.getColor()) &&
                    this.position.equals(other.getPosition()) &&
                    MUtils.approxEqual(this.intensity, other.intensity);
        }
        return false;
    }

}
