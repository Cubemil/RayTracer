package raytracer.sampler;

import math.Point;

import java.util.ArrayList;
import java.util.List;

public class OffsetSampler implements Sampler{

    @Override
    public List<Point> generateSamplePoint(int count) {
        List<Point> points = new ArrayList<>();
        double gridSize = Math.sqrt(count);

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                double offsetX = (x + 0.5) / gridSize - 0.5;
                double offsetY = (y + 0.5) / gridSize - 0.5;

                points.add(new Point(offsetX, offsetY, 0));
            }
        }

        return points;
    }

}
