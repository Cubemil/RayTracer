package math;

public final class Vector extends Tuple{

    //- constants -------------------------------------------------------------

    public static Vector ONE = new Vector(1, 1, 1);
    public static Vector ZERO = new Vector(0, 0, 0);
    public static Vector UP = new Vector(0, 1, 0);
    public static Vector DOWN = new Vector(0, -1, 0);
    public static Vector RIGHT = new Vector(1, 0, 0);
    public static Vector FORWARD = new Vector(0, 0, 1);

    //- constructors ----------------------------------------------------------

    public Vector(double x, double y, double z) {
        super(x, y, z, 0.0);
    }

    public Vector(double x, double y, double z, double w) {
        super(x, y, z, w);
    }

    public Vector(double[] values) {
        this(values[0], values[1], values[2]);
    }

    //- calc functions --------------------------------------------------------

    /**
     * @param vector to add
     * @return vector + vector = vector
     */
    public Vector add(Vector vector) {
        return new Vector(this.x + vector.x, this.y + vector.y, this.z + vector.z);
    }

    /**
     * @param vector to subtract
     * @return vector - vector = vector
     */
    public Vector sub(Vector vector) {
        return new Vector(this.x - vector.x, this.y - vector.y, this.z - vector.z);
    }

    /**
     * @return vector with negative values
     */
    public Vector neg() {
        return new Vector(-this.x, -this.y, -this.z);
    }

    /**
     * @param s scalar
     * @return new vector scaled with s
     */
    public Vector mult(double s) {
        return new Vector(
                s * this.x,
                s * this.y,
                s * this.z);
    }

    /**
     * @param vector to multiply with
     * @return vector * vector = vector
     */
    public Vector mult(Vector vector) {
        return new Vector(this.x * vector.x, this.y * vector.y, this.z * vector.z);
    }

    /**
     * @param s scalar
     * @return new vector divided by s
     * @throws IllegalArgumentException when s == 0
     */
    // Skalar / Vektor -> Vektor
    // Komponentenweise Multiplikation mit dem Skalar
    public Vector div(double s) throws IllegalArgumentException {
        if (s == 0) throw new IllegalArgumentException();
        return new Vector(
                this.x / s,
                this.y / s,
                this.z / s);
    }

    /**
     * @return length of a vector, sqrt of dot with self
     */
    public double magnitude() {
        return Math.sqrt(sqrMagnitude());
    }

    /**
     * @return mag of vector squared
     */
    public double sqrMagnitude() {
        return this.x * this.x +
                this.y * this.y +
                this.z * this.z +
                this.w * this.w;
    }

    /**
     * @return normalised vector
     */
    public Vector normalized() {
        double mag = magnitude();
        return mag != 0 ? new Vector(this.x / mag, this.y / mag, this.z / mag) : ZERO;
    }

    /**
     * @param vector scalar-product of two vectors
     * @return dot product
     */
    public double dot(Vector vector) {
        return this.x * vector.x + this.y * vector.y + this.z * vector.z;
    }

    /**
     * @param vector other vector to be crossed with
     * @return cross-product of two vectors
     */
    public Vector cross(Vector vector) {
        return new Vector(
                this.y * vector.z - this.z * vector.y,
                this.z * vector.x - this.x * vector.z,
                this.x * vector.y - this.y * vector.x);
    }

    /**
     * @return true if vector is normalised
     */
    public boolean isNormalized() {
        return Math.abs(this.magnitude() - 1.0) < 0.00001;
    }

    /**
     * @param normal normal Vector n
     * @return a reflected vector from e (this)
     * r = e - 2(e * n) * n
     * @throws IllegalArgumentException if the input vector is not normalized
     */
    public Vector reflect(Vector normal) throws IllegalArgumentException {
        if (!normal.isNormalized()) throw new IllegalArgumentException("Input vector is not normalized.");

        double s = this.dot(normal);
        return this.sub(normal.mult(2 * s));
    }


}