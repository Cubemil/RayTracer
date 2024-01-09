package math;

public class Tuple {

    protected final double x;
    protected final double y;
    protected final double z;
    protected double w;

    //- constructors ----------------------------------------------------------

    public Tuple(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Tuple(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    //- structure methods -----------------------------------------------------

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    public double w() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tuple other) {
            return MUtils.approxEqual(this.x, other.x) &&
                    MUtils.approxEqual(this.y, other.y) &&
                    MUtils.approxEqual(this.z, other.z) &&
                    MUtils.approxEqual(this.w, other.w);
        }
        return false;
    }

    @Override
    public String toString() {
        return "(x: " + x + ", y: " + y + ", z: " + z + ", w: " + w + ")";
    }

}
