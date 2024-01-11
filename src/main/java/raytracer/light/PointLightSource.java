package raytracer.light;

import math.Point;
import math.Color;

public class PointLightSource extends LightSource {

    private final Point position;

    public PointLightSource(Point position, Color color) {
        super(color);
        this.position = position;
    }

    public PointLightSource(Point position, double intensity) {
        super(intensity);
        this.position = position;
    }

    public PointLightSource(Point position) {
        super();
        this.position = position;
    }

    public PointLightSource(Point position, Color color, double intensity) {
        super(color, intensity);
        this.position = position;
    }

    public Point getPosition() {
        return this.position;
    }

}
