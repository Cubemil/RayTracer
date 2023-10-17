package org.example;

public class Point {

    // Dreidimensionale Attribute
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
    public static Point divide(Point p, double s) {
        return new Point(p.x / s, p.y / s, p.z / s);
    }

    // Punkt + Vektor -> Vektor
    // Komponentenweise Addition
    public static Point add(Point p, Vector v) {
        return new Point(p.x + v.getX(), p.y + v.getY(), p.z + v.getZ());
    }

    // Punkt - Vektor -> Vektor
    // Komponentenweise Subtraktion
    public static Point subtract(Point p, Vector v) {
        return new Point(p.x - v.getX(), p.y - v.getY(), p.z - v.getZ());
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
        return ((p1.x < p2.x) && (p1.y < p2.y) && (p1.z < p1.z)) ? p1 : p2;
    }

    // Komponentenweises Maximum
    public static Point max(Point p1, Point p2) {
        return ((p1.x > p2.x) && (p1.y > p2.y) && (p1.z > p1.z)) ? p1 : p2;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
