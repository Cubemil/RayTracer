package org.example;

public abstract class Shape {

    public abstract IntersectionManager intersect(Ray ray);

    public abstract Vector normalAt(Point point);

}
