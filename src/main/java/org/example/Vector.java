package org.example;

public final class Vector {

    private final double x;
    private final double y;
    private final double z;
    private final double w;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 0;
    }

    // Vektor + Vektor -> Vektor
    // Komponentenweise Addition
    public Vector add(Vector vector) {
        return new Vector(this.x + vector.x, this.y + vector.y, this.z + vector.z);
    }

    // Vektor - Vektor -> Vektor
    // Komponentenweise Subtraktion
    public Vector sub(Vector vector) {
        return new Vector(this.x - vector.x, this.y - vector.y, this.z - vector.z);
    }

    // -Vektor -> Vektor
    // Komponentenweise Negation
    public Vector neg() {
        return new Vector(-this.x, -this.y, -this.z);
    }

    // Skalar * Vektor -> Vektor
    // Komponentenweise Multiplikation mit dem Skalar
    public Vector mult(double s) {
        return new Vector(
                s * this.x,
                s * this.y,
                s * this.z);
    }

    // Skalar / Vektor -> Vektor
    // Komponentenweise Multiplikation mit dem Skalar
    public Vector div(double s) throws IllegalArgumentException {
        if (s == 0) throw new IllegalArgumentException();
        return new Vector(
                this.x / s,
                this.y / s,
                this.z / s);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector other) {
            return  Math.abs(this.x - other.x) < 0.00001 &&
                    Math.abs(this.y - other.y) < 0.00001 &&
                    Math.abs(this.z - other.z) < 0.00001 &&
                    Math.abs(this.w - other.w) < 0.00001;
        }
        return false;
    }

    // Länge eines Vektors → Double
    // Wurzel aus dem Skalarprodukt des Vektors mit sich selbst
    public double magnitude() {
        return Math.sqrt(sqrMagnitude());
    }

    // Länge eines Vektors quadriert → Double
    public double sqrMagnitude() {
        return (Math.abs(this.x * this.x) +
                Math.abs(this.y * this.y) +
                Math.abs(this.z * this.z));
    }


    // Vektor normalisieren -> Vektor
    public Vector normalized() {
        double length = magnitude();
        return new Vector(
                this.x / length,
                this.y / length,
                this.z / length);
    }

    // Skalarprodukt zweier Vektoren → Double
    public double dot(Vector vector) {
        return this.x * vector.x + this.y * vector.y + this.z * vector.z;
    }

    // Kreuzprodukt zweier Vektoren → Vektor
    public Vector cross(Vector vector) {
        return new Vector(
                this.y * vector.z - this.z * vector.y,
                this.z * vector.x - this.x * vector.z,
                this.x * vector.y - this.y * vector.x);
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

    public double getW() {
        return w;
    }

    @Override
    public String toString() {
        return "Vector[" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                ']';
    }


}