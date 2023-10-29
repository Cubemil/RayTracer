package org.example;

public final class Point {

    private final double x;
    private final double y;
    private final double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Punkt - Punkt -> Vektor!!!
    // Komponentenweise Subtraktion
    public static Vector subtract(Point p1, Point p2) {
        return new Vector(p1.x - p2.x, p1.y - p2.y, p1.z - p2.z);
    }

    // Punkt * Skalar -> Punkt
    // Komponentenweise Multiplikation mit dem Skalar
    public static Point multiply(Point p, double s) {
        return new Point(p.x * s, p.y * s, p.z * s);
    }

    // Skalar * Punkt -> Punkt
    // Komponentenweise Multiplikation mit dem Skalar
    public static Point multiply(double s, Point p) {
        return new Point(p.x * s, p.y * s, p.z * s);
    }

    // Punkt / Skalar -> Punkt
    // Komponentenweise Division mit dem Skalar
    public static Point divide(Point p, double s) throws IllegalArgumentException {
        if (s == 0) throw new IllegalArgumentException();
        return new Point(p.x / s, p.y / s, p.z / s);
    }

    // Punkt + Vektor -> Vektor
    // Komponentenweise Addition
    public static Point add(Point p, Vector v) {
        return new Point(p.x + v.x(), p.y + v.y(), p.z + v.z());
    }

    // Punkt - Vektor -> Vektor
    // Komponentenweise Subtraktion
    public static Point subtract(Point p, Vector v) {
        return new Point(p.x - v.x(), p.y - v.y(), p.z - v.z());
    }

    // Punkt == Punkt und Punkt != Punkt -> Boolean
    // Komponentenweiser Vergleich
    public static boolean equals(Point p1, Point p2) {
        return (p1.x == p2.x &&
                p1.y == p2.y &&
                p1.z == p2.z);
    }

    // Komponentenweises Minimum
    public static Point min(Point p1, Point p2) {
        return new Point(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.min(p1.z, p2.z));
    }

    // Komponentenweises Maximum
    public static Point max(Point p1, Point p2) {
        return new Point(Math.max(p1.x, p2.x), Math.max(p1.y, p2.y), Math.max(p1.z, p2.z));
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    @Override
    public String toString() {
        return "Point[" +
                "x=" + x + ", " +
                "y=" + y + ", " +
                "z=" + z + ']';
    }

}
