package raytracer.sampler;

import math.Point;

import java.util.Collections;
import java.util.List;

public class NoSampler implements Sampler{

    @Override
    public List<Point> generateSamplePoint(int count) {
        return Collections.singletonList(new Point(0, 0, 0));
    }

}
