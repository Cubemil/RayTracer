package org.example.objects;

import org.example.utils.intersections.IntersectionManager;
import org.example.math.Matrix;
import org.example.math.Point;
import org.example.math.Ray;
import org.example.math.Vector;

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
