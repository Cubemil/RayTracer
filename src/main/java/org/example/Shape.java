package org.example;

public abstract class Shape {

    public abstract IntersectionManager localIntersect(Ray localRay);

    public abstract IntersectionManager intersect(Ray worldRay);

    public abstract Vector normalAt(Point point);

    private Matrix transformationMatrix = Matrix.identityMatrix(new Matrix());

    public Matrix getTransformationMatrix() {
        return transformationMatrix;
    }

    public void setTransformationMatrix(Matrix transformationMatrix) {
        this.transformationMatrix = transformationMatrix;
    }
}
