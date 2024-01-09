package math;

public record Color(double red, double green, double blue) {

    //- example constants -----------------------------------------------------

    public static final Color PINK = new Color(1.0, 16 / 255.0, 240 / 255.0);
    public static final Color BLACK = new Color(0.0, 0.0, 0.0);
    public static final Color WHITE = new Color(1.0, 1.0, 1.0);
    public static final Color RED = new Color(1.0, 0.0, 0.0);
    public static final Color GREEN = new Color(0.0, 1.0, 0.0);
    public static final Color BLUE = new Color(0.0, 0.0, 1.0);
    public static final Color YELLOW = new Color(1.0, 1.0, 0.0);
    public static final Color ORANGE = new Color(1.0, 0.4, 0.0);
    public static final Color CORNFLOWERBLUE = new Color(0.3922, 0.5843, 0.9294);
    public static final Color NEON_GREEN = new Color(0.224, 1.00, 0.78);

    //- constructors ----------------------------------------------------------

    /**
     * @param other different Color from which we copy the values
     */
    public Color(Color other) {
        this(other.red, other.green, other.blue);
    }

    /**
     * @param values array with at least 3 values
     */
    public Color(double[] values) {
        this(values[0], values[1], values[2]);
    }

    /**
     * @param rgb INT_RGB value to calculate into our three values
     */
    public Color(double rgb) {
        this(rgb, rgb, rgb);
    }

    /**
     * default -> black
     */
    public Color() {
        this(0, 0, 0);
    }

    //- calculation functions -------------------------------------------------

    public Color add(Color other) {
        return new Color(red + other.red(), green + other.green(), blue + other.blue());
    }

    public Color sub(Color other) {
        return new Color(red - other.red(), green - other.green(), blue - other.blue());
    }

    public Color mult(Color other) {
        return new Color(red * other.red(), green * other.green(), blue * other.blue());
    }

    public Color mult(double value) {
        return new Color(red * value, green * value, blue * value);
    }

    public Color clamped() {
        return new Color(
                Math.max(0.0, Math.min(1.0, red)),
                Math.max(0.0, Math.min(1.0, green)),
                Math.max(0.0, Math.min(1.0, blue)));
    }

    //- methods for calculating rgb values ------------------------------------

    public int toIntRGB() {
        var rgbValue = (int)(this.blue * 255);
        rgbValue += (int)(this.green * 255) << 8;
        rgbValue += (int)(this.red * 255) << 16;
        return rgbValue;
    }

    public static Color fromIntRGB(int intRGB) {
        double r = (intRGB >> 16 & 0xff) / 255.0;
        double g = (intRGB >> 8 & 0xff) / 255.0;
        double b = (intRGB & 0xff) / 255.0;
        return new Color(r, g, b);
    }

    //- Equals, HashCode, etc. ------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Color other) {
            double epsilon = 0.01;
            return Math.abs(this.red - other.red) < epsilon &&
                    Math.abs(this.green - other.green) < epsilon &&
                    Math.abs(this.blue - other.blue) < epsilon;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Color{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }

}
