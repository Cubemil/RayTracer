package org.example;

public class Vector {

    // Dreidimensionale Attribute
    private final double x;
    private final double y;
    private final double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Vektor + Vektor -> Vektor
    // Komponentenweise Addition
    public static Vector add(Vector v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    // Vektor - Vektor -> Vektor
    // Komponentenweise Subtraktion
    public static Vector subtract(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    // -Vektor -> Vektor
    // Komponentenweise Negation
    public static Vector negate(Vector v) {
        return new Vector(-v.x, -v.y, -v.z);
    }

    // Hilfsmethode → Gibt den Skalar des Vektors zurück
    private static double scalar(Vector v) {
        return v.x + v.y + v.z;
    }

    // Skalarprodukt zweier Vektoren → Double
    public static double dot(Vector v1, Vector v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    // Skalar * Vektor -> Vektor
    // Komponentenweise Multiplikation mit dem Skalar
    public static Vector scalarTimesVector(Vector v) {
        return new Vector(
            scalar(v) * v.x,
            scalar(v) * v.y,
            scalar(v) * v.z);
    }

    // Skalar / Vektor -> Vektor
    // Komponentenweise Multiplikation mit dem Skalar
    public static Vector scalarDividedByVector(Vector v) {
        return new Vector(
                scalar(v) / v.x,
                scalar(v) / v.y,
                scalar(v) / v.z);
    }

    // Vektor == Vektor und Vektor != Vektor -> Boolean
    // Komponentenweiser Vergleich
    public static boolean equals(Vector v1, Vector v2) {
        return (v1.x == v2.x &&
                v1.y == v2.y &&
                v1.z == v2.z);
    }

    // Hilfsmethode für das Berechnen der Wurzel einer Zahl
    private static double squareRoot(double number) {
        double temp;
        double sr = number/2;
        do {
            temp = sr;
            sr = (temp + (number / temp)) / 2;
        } while ((temp - sr) != 0);
        return sr;
    }

    // Länge eines Vektors → Double
    // Wurzel aus dem Skalarprodukt des Vektors mit sich selbst
    public static double magnitude(Vector vector) {
        return squareRoot(dot(vector, vector));
    }

    // Länge eines Vektors quadriert → Double
    public double sqrMagnitude(Vector vector) {
        return magnitude(vector) * magnitude(vector);
    }

    // Vektor normalisieren -> Vektor
    public static Vector normalized(Vector vector) {
        double length = magnitude(vector);
        return new Vector(
                vector.x / length,
                vector.y / length,
                vector.z / length);
    }

    // Kreuzprodukt zweier Vektoren → Vektor
    public static Vector cross(Vector v1, Vector v2) {
        return new Vector(
                v1.y * v2.z - v1.z * v2.y,
                v1.z * v2.x - v1.x * v2.z,
                v1.x * v2.y - v1.y * v2.x);
    }

}