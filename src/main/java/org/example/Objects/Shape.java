package org.example.Objects;

import org.example.Utils.Intersections.IntersectionManager;
import org.example.Math.Matrix;
import org.example.Math.Point;
import org.example.Math.Ray;
import org.example.Math.Vector;

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
