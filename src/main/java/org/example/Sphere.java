package org.example;

public class Sphere extends Shape {

    private Point m = new Point(0,0,0);    // center Point
    private double r = 1.0;   // radius 1

    @Override
    public IntersectionManager intersect(Ray ray) {
        return null;
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
}
