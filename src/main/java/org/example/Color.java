package org.example;

public class Color {

    private final double r;
    private final double g;
    private final double b;

    // Constructor using 3 separate double values
    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    // Default Constructor (white)
    public Color() {
        this.r = 0.0;
        this.g = 0.0;
        this.b = 0.0;
    }

    // Creates a Color object using an Integer value with format ??RRGGBB
    public Color(int rgb) {
        this.b = (double) ((rgb >> 16) & 0xFF) / 255.0;
        this.g = (double) ((rgb >> 8) & 0xFF) / 255.0;
        this.r = (double) (rgb & 0xFF) / 255.0;
    }

    // adding colors
    public Color add(Color color) {
        return new Color(
                this.r + color.r,
                this.g + color.g,
                this.b + color.b
        );
    }

    // multiply Color by scalar
    public Color mult(double s) {
        return new Color(
                this.r * s,
                this.g * s,
                this.b * s
        );
    }

    // multiply Color by Color (Hadamard-product)
    public Color mult(Color color) {
        return new Color(
                this.r * color.r,
                this.g * color.g,
                this.b * color.b
        );
    }

    public Color clamped() {
        double newR = this.r;
        double newG = this.g;
        double newB = this.b;

        if (this.r >= 1.0) newR = 1.0;
            else if (this.r <= 0.0) newR = 0.0;
        if (this.g >= 1.0) newG = 1.0;
            else if (this.g <= 0.0) newG = 0.0;
        if (this.b >= 1.0) newB = 1.0;
            else if (this.b <= 0.0) newB = 0.0;

        return new Color(newR, newG, newB);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Color other) {
            double epsilon = 0.01;
            return Math.abs(this.r - other.r) < epsilon &&
                    Math.abs(this.g - other.g) < epsilon &&
                    Math.abs(this.b - other.b) < epsilon;
        }
        return false;
    }

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    /**
     * @return int value of RGB in format: ??RRGGBB
     */
    public int getIntRGB() {
        return ((int) (this.r * 255) << 16)
                + ((int) (this.g * 255) << 8)
                + (int) (this.b * 255);
    }

}
