package org.example;

public class Intersection {

    private double t;
    private Object shape;

    public Intersection(double t, Object shape) {
        this.t = t;
        this.shape = shape;
    }

    public double getT() {
        return this.t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public Object getShape() {
        return this.shape;
    }

    public void setShape(Object shape) {
        this.shape = shape;
    }

}
