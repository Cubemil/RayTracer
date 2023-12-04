package org.example.Utils.Intersections;

import org.example.Objects.Shape;

public record Intersection(double t, Shape shape) {

    public Object getShape() {
        return this.shape;
    }

    @Override
    public String toString() {
        return "Intersection with " + shape + " at " + t;
    }

}
