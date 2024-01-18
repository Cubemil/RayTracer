package math;

public final class Point extends Tuple {

    public static final Point ZERO = new Point(0, 0, 0);
    public static final Point POSITIVE_INFINITY = new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static final Point NEGATIVE_INFINITY = new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

    public Point(double x, double y, double z) {
        super(x, y, z, 1.0);
    }

    public Point(double x, double y, double z, double w) {
        super(x, y, z, w);
    }

    public Point(double[] values) {
        this(values[0], values[1], values[2]);
    }

    // Point - Point → Vector
    public Vector sub(Point point) {
        return new Vector(this.x - point.x, this.y - point.y, this.z - point.z);
    }

    // Point * scalar -> Point
    public Point mult(double s) {
        return new Point(this.x * s, this.y * s, this.z * s);
    }

    // Point / scalar → Point
    public Point div(double s) throws IllegalArgumentException {
        if (s == 0) throw new IllegalArgumentException("Division by zero");
        return new Point(this.x / s, this.y / s, this.z / s);
    }

    // Point + Vector → Point
    public Point add(Vector v) {
        return new Point(this.x + v.x(), this.y + v.y(), this.z + v.z());
    }

    // Punkt - Vektor → Punkt
    public Point sub(Vector v) {
        return new Point(this.x - v.x(), this.y - v.y(), this.z - v.z());
    }

    public Point negated() {
        return new Point(-x, -y, -z);
    }

    public Point min(Point point) {
        return new Point(Math.min(this.x, point.x), Math.min(this.y, point.y), Math.min(this.z, point.z));
    }

    public Point max(Point point) {
        return new Point(Math.max(this.x, point.x), Math.max(this.y, point.y), Math.max(this.z, point.z));
    }

}
