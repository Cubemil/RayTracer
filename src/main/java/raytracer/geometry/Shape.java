package raytracer.geometry;

import raytracer.IntersectionManager;
import raytracer.Ray;
import math.*;

public abstract class Shape {

    public abstract IntersectionManager localIntersect(Ray localRay);

    public abstract IntersectionManager intersect(Ray worldRay);

    /**
     * @param worldPoint on the surface of the shape
     * @return the normal vector of the surface point
     * -> here it is the same as the location vector of the worldPoint
     * -> before, worldPoint used to be hit
     *      ->changed due to not being able to display normal vectors when multiplying with inverse
     */
    public Vector normalAt(Point worldPoint) {
        // transformation of worldPoint in local CS
        Point localPoint = getTransformationMatrix().inv().mult(worldPoint);

        // local normal
        Vector localNormal = localPoint.sub(new Point(0, 0, 0));

        // transformation of localNormal in world CS
        Vector worldNormal = getTransformationMatrix().inv().transposed().mult(localNormal);

        // has to change to a vector, afaik only achievable through a setter
        worldNormal.setW(0);
        return worldNormal.normalized();
    }

    private Matrix transformationMatrix = Matrix.identityMatrix();

    public Matrix getTransformationMatrix() {
        return transformationMatrix;
    }

    public void setTransformationMatrix(Matrix transformationMatrix) {
        this.transformationMatrix = transformationMatrix;
    }
}
