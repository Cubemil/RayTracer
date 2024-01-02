package raytracer;

import math.MUtils;
import math.Point;
import math.Color;

public class PointLightSource extends LightSource {

    public PointLightSource(Point position, Color color) {
        super(position, color);
    }

    public PointLightSource(Point position, Color color, double intensity) {
        super(position, color, intensity);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PointLightSource other) {
            return  this.getColor().equals(other.getColor()) &&
                    this.getPosition().equals(other.getPosition()) &&
                    MUtils.approxEqual(this.getIntensity(), other.getIntensity());
        }
        return false;
    }

}
