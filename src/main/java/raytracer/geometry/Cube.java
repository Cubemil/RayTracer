package raytracer.geometry;

import math.MUtils;
import math.Point;
import math.Vector;
import raytracer.Intersection;
import raytracer.IntersectionManager;
import raytracer.Ray;

public class Cube extends Shape {

    private static final double EPSILON = MUtils.getEPSILON();
    private static final double MIN_BOUND = -1.0;
    private static final double MAX_BOUND = 1.0;

    public Cube() {
        super();
    }

    private double[] getMinMax(double origin, double direction) {
        double tMinNumerator = MIN_BOUND - origin;
        double tMaxNumerator = MAX_BOUND - origin;

        double tMin, tMax;
        if (Math.abs(direction) >= EPSILON) {
            tMin = tMinNumerator / direction;
            tMax = tMaxNumerator / direction;
        } else {
            tMin = tMinNumerator * Double.POSITIVE_INFINITY;
            tMax = tMaxNumerator * Double.POSITIVE_INFINITY;
        }

        if (tMin > tMax) {
            double temp = tMin;
            tMin = tMax;
            tMax = temp;
        }
        return new double[]{tMin, tMax};
    }

    @Override
    public IntersectionManager localIntersect(Ray localRay) {
        var intersectionManager = new IntersectionManager();

        double[] tMinMaxX = getMinMax(localRay.getOriginPoint().x(), localRay.getDirectionVector().x());
        double[] tMinMaxY = getMinMax(localRay.getOriginPoint().y(), localRay.getDirectionVector().y());
        double[] tMinMaxZ = getMinMax(localRay.getOriginPoint().z(), localRay.getDirectionVector().z());

        double minT = Math.max(tMinMaxX[0], Math.max(tMinMaxY[0], tMinMaxZ[0]));
        double maxT = Math.min(tMinMaxX[1], Math.min(tMinMaxY[1], tMinMaxZ[1]));

        if (minT > maxT) return intersectionManager;

        intersectionManager.add(new Intersection(minT, this));
        intersectionManager.add(new Intersection(maxT, this));
        return intersectionManager;
    }

    @Override
    public Vector localNormalAt(Point localPoint) {
        double max = Math.max(Math.abs(localPoint.x()), Math.max(Math.abs(localPoint.y()), Math.abs(localPoint.z())));

        if (max == Math.abs(localPoint.x())) {
            return new Vector(localPoint.x(), 0, 0);
        } else if (max == Math.abs(localPoint.y())) {
            return new Vector(0, localPoint.y(), 0);
        } else {
            return new Vector(0, 0, localPoint.z());
        }
    }

    @Override
    public Vector normalAt(Point worldPoint) {
        Point localPoint = getTransformationMatrix().inv().mult(worldPoint);
        Vector localNormal = localNormalAt(localPoint);
        Vector worldNormal = getTransformationMatrix().inv().transposed().mult(localNormal);
        return worldNormal.normalized();
    }

}
