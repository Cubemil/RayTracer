package math;

public final class Vector extends Tuple{

    public static Vector ONE = new Vector(1, 1, 1);
    public static Vector ZERO = new Vector(0, 0, 0);
    public static Vector UP = new Vector(0, 1, 0);
    public static Vector DOWN = new Vector(0, -1, 0);
    public static Vector RIGHT = new Vector(1, 0, 0);
    public static Vector FORWARD = new Vector(0, 0, 1);

    public Vector(double x, double y, double z) {
        super(x, y, z, 0.0);
    }

    public Vector(double x, double y, double z, double w) {
        super(x, y, z, w);
    }

    public Vector(double[] values) {
        this(values[0], values[1], values[2]);
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

    // Länge eines Vektors → Double
    // Wurzel aus dem Skalarprodukt des Vektors mit sich selbst
    public double magnitude() {
        return Math.sqrt(sqrMagnitude());
    }

    // Länge eines Vektors quadriert → Double
    public double sqrMagnitude() {
        return  this.x * this.x +
                this.y * this.y +
                this.z * this.z +
                this.w * this.w;
    }

    // Vektor normalisieren -> Vektor
    public Vector normalized() {
        double mag = magnitude();
        return mag != 0 ? new Vector(this.x / mag, this.y / mag, this.z / mag) : ZERO;
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

    // Check if the vector is normalized
    public boolean isNormalized() {
        return Math.abs(this.magnitude() - 1.0) < 0.00001;
    }

}