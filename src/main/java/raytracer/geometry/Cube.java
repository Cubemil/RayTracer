package raytracer.geometry;

import math.MUtils;
import math.Point;
import math.Vector;
import raytracer.Intersection;
import raytracer.IntersectionManager;
import raytracer.Ray;

public class Cube extends Shape {

    public Cube() {
        super();
    }

    private double[] getMinMax(double origin, double direction) {
        double tMinNumerator = -1 - origin;
        double tMaxNumerator = 1 - origin;

        double tMin, tMax;
        if (Math.abs(direction) >= MUtils.getEPSILON()) {
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
        var xs = new IntersectionManager();

        // check all three planes with corresponding rays
        double[] tMinX = getMinMax(localRay.getOriginPoint().x(), localRay.getDirectionVector().x());
        double[] tMinY = getMinMax(localRay.getOriginPoint().y(), localRay.getDirectionVector().y());
        double[] tMinZ = getMinMax(localRay.getOriginPoint().z(), localRay.getDirectionVector().z());

        // get the max of tMinX, tMinY, tMinZ
        double minT = Math.max(tMinX[0], Math.max(tMinY[0], tMinZ[0]));
        double maxT = Math.min(tMinX[1], Math.min(tMinY[1], tMinZ[1]));

        // no intersection
        if (minT > maxT) return xs;

        // return both parameter values of both intersections with cube
        xs.add(new Intersection(minT, this));
        xs.add(new Intersection(maxT, this));
        return xs;
    }

    @Override
    public Vector localNormalAt(Point localPoint) {
        double max =    Math.max(
                            Math.abs(localPoint.x()),
                        Math.max(
                            Math.abs(localPoint.y()),
                            Math.abs(localPoint.z())));

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
        // transform point in local CS
        Point localPoint = getTransformationMatrix().inv().mult(worldPoint);
        // get the local normal
        Vector localNormal = localNormalAt(localPoint);
        // transform local normal back into world CS
        Vector worldNormal = getTransformationMatrix().inv().transposed().mult(localNormal);
        // normalised world normal
        return worldNormal.normalized();
    }

}
