package math;

public class MUtils
{
    // comparison constant
    private static final double EPSILON = 0.00001;

    /**
     * @param a first comparison value
     * @param b second comparison value
     * @return true, if both values are similar
     */
    public static boolean approxEqual(double a, double b) {
        return Math.abs(a-b) < EPSILON;
    }

}

