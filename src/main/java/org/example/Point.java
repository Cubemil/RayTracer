package org.example;

public final class Point {

    private final double x;
    private final double y;
    private final double z;
    private final double w;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1;
    }

    // Punkt - Punkt -> Vektor!!!
    // Komponentenweise Subtraktion
    public Vector sub(Point point) {
        return new Vector(this.x - point.x, this.y - point.y, this.z - point.z);
    }

    // Punkt * Skalar -> Punkt
    // Komponentenweise Multiplikation mit dem Skalar
    public Point mult(double s) {
        return new Point(this.x * s, this.y * s, this.z * s);
    }

    // Punkt / Skalar -> Punkt
    // Komponentenweise Division mit dem Skalar
    public Point div(double s) throws IllegalArgumentException {
        if (s == 0) throw new IllegalArgumentException();
        return new Point(this.x / s, this.y / s, this.z / s);
    }

    // Punkt + Vektor -> Punkt
    // Komponentenweise Addition
    public Point add(Vector v) {
        return new Point(this.x + v.x(), this.y + v.y(), this.z + v.z());
    }

    // Punkt - Vektor -> Punkt
    // Komponentenweise Subtraktion
    public Point sub(Vector v) {
        return new Point(this.x - v.x(), this.y - v.y(), this.z - v.z());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point other) {
            return  Math.abs(this.x - other.x) < 0.00001 &&
                    Math.abs(this.y - other.y) < 0.00001 &&
                    Math.abs(this.z - other.z) < 0.00001 &&
                    Math.abs(this.w - other.w) < 0.00001;
        }
        return false;
    }

    // Komponentenweises Minimum
    public Point min(Point point) {
        return new Point(Math.min(this.x, point.x), Math.min(this.y, point.y), Math.min(this.z, point.z));
    }

    // Komponentenweises Maximum
    public Point max(Point point) {
        return new Point(Math.max(this.x, point.x), Math.max(this.y, point.y), Math.max(this.z, point.z));
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

    public double w(){
        return w;
    }

    @Override
    public String toString() {
        return "Point[" +
                "x=" + x + ", " +
                "y=" + y + ", " +
                "z=" + z + ", " +
                "w=" + w + ']';
    }

}
