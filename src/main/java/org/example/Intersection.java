package org.example;

public class Intersection {

    private double t;
    private Shape shape;

    public Intersection(double t, Shape shape) {
        this.t = t;
        this.shape = shape;
    }

    public double getT() {
        return this.t;
    }

    public Object getShape() {
        return this.shape;
    }

    @Override
    public String toString() {
        return "Intersection with " + shape + " at " + t;
    }

    public Shape shape() {
        return this.shape;
    }

}
