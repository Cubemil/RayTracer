package org.example.objects;

import org.example.utils.intersections.*;
import org.example.math.*;

public class Sphere extends Shape {

    private final Point m;
    private final double r;

    // default Sphere
    public Sphere() {
        this.m = new Point(0, 0, 0);   // center Point
        this.r = 1.0;                           // radius 1
    }

    /**
     * @param localRay ray to check for intersections
     * -> later Transformed according to transformation Matrix of Sphere
     * @return the list of intersections with the ray
     * <p> -----------------calculations-------------------</p>
     * >Sphere: dot((P - C), (P - C) = r^2
     * >Ray: p(t) = A + tB
     * >Combined: dot((A + tB - C), (A + tB - C)) = r^2
     * >rearranged: t^2 * dot(B,B) + 2t * dot(B,A - C) + dot(A - C, A - C) - r^2 = 0
     * >quadratic equation: at^2 + bt + c = 0
     *      where   a = dot(B,B)
     *              b = 2 * dot (B, A - C)
     *              c = dot(A - C, A - C) - r^2
     * >with all: t = -b +-root(b^2 - 4ac)) / 2a --> The stuff in the root is the discriminant
     * <p> -------------------Results-----------------------</p>
     * if discriminant < 0, the ray does not intersect the sphere
     * if discriminant >= 0, the ray touches the sphere at one point (tangent) or in two points (still same condition)
     */
    public IntersectionManager localIntersect(Ray localRay) {
        IntersectionManager resultIntersections = new IntersectionManager();

        Vector oc = localRay.getOriginPoint().sub(getM());
        double a = localRay.getDirectionVector().dot(localRay.getDirectionVector());
        double b = 2 * oc.dot(localRay.getDirectionVector());
        double c = oc.dot(oc) - r * r;

        double discriminant = b * b - 4 * a * c;

        // return empty array
        if (discriminant < 0) return new IntersectionManager(new Intersection[]{});
        else {
            // first intersection point
            double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            // other intersection point:
            double t2 = (-b - Math.sqrt(discriminant)) / (2 * a);

            // Idea: check if intersection is in front of ray origin: if (tX >= 0)
            resultIntersections.add(new Intersection(t1, this));
            resultIntersections.add(new Intersection(t2, this));
        }

        return resultIntersections;
    }

    /**
     * @param worldRay gets a transformed Ray in world coords
     * @return localIntersect() with a local ray transformed to the local coords of this object
     */
    public IntersectionManager intersect(Ray worldRay) {
        var ray = worldRay.transform(this.getTransformationMatrix().inv());
        return localIntersect(ray);
    }

    /**
     * @param localPoint point in the local CS
     * @return normal vector at given point
     */
    private Vector localNormalAt(Point localPoint) {
        return localPoint.sub(new Point(0, 0, 0));
    }

    public Point getM() {
        return m;
    }

    public double getR() {
        return this.r;
    }

    /**
     * @param obj other Sphere
     * @return true if this and other Sphere share the same origin Point, radius and Transformation
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sphere other) {
            return  this.getM().equals(other.getM()) &&
                    this.r == other.getR() &&
                    this.getTransformationMatrix().equals(other.getTransformationMatrix());
        }
        return false;
    }
}
