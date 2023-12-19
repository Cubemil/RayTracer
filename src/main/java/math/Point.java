package math;

public final class Point extends Tuple {

    //TODO refactor to English, using javadoc

    public static Point ZERO = new Point(0, 0, 0);
    public static Point POSITIVE_INFINITY = new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static Point NEGATIVE_INFINITY = new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

    public Point(double x, double y, double z) {
        super(x, y, z, 1.0);
    }

    public Point(double x, double y, double z, double w) {
        super(x, y, z, w);
    }

    public Point(double[] values) {
        this(values[0], values[1], values[2]);
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

    public Point negated() {
        return new Point(-x, -y, -z);
    }

    // Komponentenweises Minimum
    public Point min(Point point) {
        return new Point(Math.min(this.x, point.x), Math.min(this.y, point.y), Math.min(this.z, point.z));
    }

    // Komponentenweises Maximum
    public Point max(Point point) {
        return new Point(Math.max(this.x, point.x), Math.max(this.y, point.y), Math.max(this.z, point.z));
    }

}
