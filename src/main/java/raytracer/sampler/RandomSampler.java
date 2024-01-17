package raytracer.sampler;

import math.Point;

import java.util.ArrayList;
import java.util.List;

public class RandomSampler implements Sampler {

    @Override
    public List<Point> generateSamplePoint(int count) {
        List<Point> points = new ArrayList<>();

        for (int offset = 0; offset < count; offset++) {
            double x = Math.random() - 0.5;
            double y = Math.random() - 0.5;

            points.add(new Point(x, y, 0));
        }

        return points;
    }

}
