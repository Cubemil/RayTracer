package raytracer.sampler;

import math.Point;

import java.util.List;

public interface Sampler {
    List<Point> generateSamplePoint(int count);
}
