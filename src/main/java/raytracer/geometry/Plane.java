package raytracer.geometry;

import math.MUtils;
import math.Point;
import math.Vector;
import raytracer.Intersection;
import raytracer.IntersectionManager;
import raytracer.Ray;

public class Plane extends Shape {

//    private Point origin;
//    private Vector xDirection;
//    private Vector yDirection;
//    private Vector zDirection;
//
//    public Plane(Point origin) {
//        this.origin = origin;
//    }

    @Override
    public IntersectionManager localIntersect(Ray localRay) {
        var xs = new IntersectionManager();

        // ray is perpendicular or equal to plane -> no intersections
        if (MUtils.approxEqual(localRay.getDirectionVector().y(), 0)) return xs;

        var t = - localRay.getOriginPoint().y() / localRay.getDirectionVector().y();
        xs.add(new Intersection(t, this));
        return xs;
    }

    /**
     * @param localPoint point in local CS
     * @return always the normal (0, 1, 0)^T,
     * since all points are on the same plane
     */
    @Override
    public Vector localNormalAt(Point localPoint) {
        return new Vector(0, 1, 0);
    }

}
