package raytracer.light;

import math.Color;
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

    public Vector getDirection() {
        return direction;
    }

}
