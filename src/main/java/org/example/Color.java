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

    // adding colors
    public static Color add(Color color1, Color color2) {
        return new Color(
                color1.r + color2.r,
                color1.g + color2.g,
                color1.b + color2.b
        );
    }

    // multiply Color by scalar
    public static Color multiply(Color color, double s) {
        return new Color(
                color.r * s,
                color.g * s,
                color.b * s
        );
    }

    // multiply Color by Color (Hadamard-product)
    public static Color multiply(Color color1, Color color2) {
        return new Color(
                color1.r * color2.r,
                color1.g * color2.g,
                color1.b * color2.b
        );
    }

    public static Color clamped(Color color) {
        double newR = color.r;
        double newG = color.g;
        double newB = color.b;

        if (color.r >= 1.0) newR = 1.0;
            else if (color.r <= 0.0) newR = 0.0;
        if (color.g >= 1.0) newG = 1.0;
            else if (color.g <= 0.0) newG = 0.0;
        if (color.b >= 1.0) newB = 1.0;
            else if (color.b <= 0.0) newB = 0.0;

        return new Color(newR, newG, newB);
    }

    // equals() -> each component checked
    public static boolean equals(Color color1, Color color2) {
        return (color1.r == color2.r &&
                color1.g == color2.g &&
                color1.b == color2.b);
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

}
