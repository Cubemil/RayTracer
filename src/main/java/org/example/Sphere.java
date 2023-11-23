package org.example;

public class Sphere extends Shape {

    private final Point m;    // center Point
    private final double r;   // radius 1
    private IntersectionManager intersections = new IntersectionManager();

    public Sphere() {
        this.r = 1.0;
        this.m = new Point(0,0,0);
    }

    @Override
    public IntersectionManager intersect(Ray ray) {
        double a = ray.getDirectionVector().dot(ray.getDirectionVector());
        double b = 2 * ray.getOriginPoint().dot(ray.getDirectionVector());
        double c = ray.getOriginPoint().dot(ray.getOriginPoint());

        double discriminant = b * b - 4 * a * c;

        if (discriminant >= 0) {
            // there are intersections
            double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

            intersections.add(new Intersection(t1, this));

            if (t1 != t2) intersections.add(new Intersection(t2, this));
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

    public double getR() {
        return r;
    }

    public IntersectionManager getIntersections() {
        return intersections;
    }
}
