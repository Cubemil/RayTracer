package org.example.utils.intersections;

import org.example.objects.Shape;

public record Intersection(double t, Shape shape) {

    public Object getShape() {
        return this.shape;
    }

    @Override
    public String toString() {
        return "Intersection with " + shape + " at " + t;
    }

}
