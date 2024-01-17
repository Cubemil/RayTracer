package raytracer.geometry;

import math.Point;
import math.Vector;
import raytracer.IntersectionManager;
import raytracer.Ray;

public class TestShape extends Shape{

    private Ray savedRay;

    public Ray getSavedRay() {
        return savedRay;
    }

    @Override
    public IntersectionManager localIntersect(Ray localRay) {
        savedRay = localRay;
        return new IntersectionManager();
    }

    public Vector localNormalAt(Point localPoint) {
        return new Vector(localPoint.x(), localPoint.y(), localPoint.z());
    }

}
