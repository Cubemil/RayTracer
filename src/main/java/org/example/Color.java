package org.example;

public class Color {

    private double r;
    private double g;
    private double b;

    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color() {
        this.r = 0.0;
        this.g = 0.0;
        this.b = 0.0;
    }

    public Color(int rgb) {
       //TODO Constructor from rgb values
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
        if (obj instanceof Color other)
        {
            return Math.abs(this.r - other.r) < 0.00001 &&
                    Math.abs(this.g - other.g) < 0.00001 &&
                    Math.abs(this.b - other.b) < 0.00001;
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
        return (int) (this.r * 255)
                + (int) (this.g * 255) << 8
                + (int) (this.b * 255) << 16;
    }
}
