package raytracer;

import math.Point;
import math.Vector;
import raytracer.geometry.Shape;

public record Intersection(double t, Shape shape) {

    public Object getShape() {
        return this.shape;
    }

    @Override
    public String toString() {
        return "Intersection with " + shape + " at " + t;
    }

    /**
     * @param ray for ray request
     * @return HitInfo object with info about the intersection
     * --------- includes: ---------
     * - intersected object/shape
     * - t-value from this
     * - Point: intersectionPoint by using ray.pointAt(t)
     * - viewDirection: direction = negative ray direction
     * - hitNormal: Vector by using normalAt(ray.pointAt(t))
     * - reflect: Vector of the incoming ray reflected at the intersection-normal
     * */
    public HitInfo prepareHitInfo(Ray ray) {
        Point intersectionPoint = ray.pointAt(t);
        Vector observer = ray.getDirectionVector().neg();
        Vector normal = shape.normalAt(intersectionPoint);
        Vector reflect = ray.getDirectionVector().reflect(normal);

        return new HitInfo(shape, t, intersectionPoint, observer, normal, reflect);
    }

}
