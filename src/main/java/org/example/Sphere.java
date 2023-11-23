package org.example;

public class Sphere extends Shape {

    private final Point m;    // center Point
    private final double r;   // radius 1
    private final IntersectionManager intersections;

    public Sphere() {
        this.r = 1.0;
        this.m = new Point(0,0,0);
        this.intersections = new IntersectionManager();
    }

    /**
     * @param ray ray to check for intersections
     * @return the list of intersections with the ray
     * automatically adds intersections to the array
     * <p> -----------------calculations-------------------
     * >Sphere: dot((P - C), (P - C) = r^2
     * >Ray: p(t) = A + tB
     * >Combined: dot((A + tB - C), (A + tB - C)) = r^2
     * >rearranged: t^2 * dot(B,B) + 2t * dot(B,A - C) + dot(A - C, A - C) - r^2 = 0
     * >quadratic equation: at^2 + bt + c = 0
     *      where   a = dot(B,B)
     *              b = 2 * dot (B, A - C)
     *              c = dot(A - C, A - C) - r^2
     * >with all: t = -b +-root(b^2 - 4ac)) / 2a --> The stuff in the root is the discriminant
     * <p> -------------------Results-----------------------
     * if discriminant < 0, the ray does not intersect the sphere
     * if discriminant >= 0, the ray touches the sphere at one point (tangent) or in two points (still same condition)
     */
    @Override
    public IntersectionManager intersect(Ray ray) {
        Vector oc = ray.getOriginPoint().sub(getM());
        double a = ray.getDirectionVector().dot(ray.getDirectionVector());
        double b = 2 * oc.dot(ray.getDirectionVector());
        double c = oc.dot(oc) - r * r;

        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) return intersections;
        else {
            // first intersection point
            double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            // other intersection point:
            double t2 = (-b - Math.sqrt(discriminant)) / (2 * a);

            // Idea: check if intersection is in front of ray origin: if (tX >= 0)
            intersections.add(new Intersection(t1, this));
            intersections.add(new Intersection(t2, this));
        }

        return intersections;
    }


    @Override
    public Vector normalAt(Point point) {
        return null;
    }

    public Point getM() {
        return m;
    }

}
