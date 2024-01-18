package raytracer.geometry;

import raytracer.IntersectionManager;
import raytracer.Material;
import raytracer.Ray;
import math.*;

public abstract class Shape {

    protected Material material = new Material();
    private Matrix transformationMatrix = Matrix.identityMatrix();

    public abstract IntersectionManager localIntersect(Ray localRay);

    public abstract Vector localNormalAt(Point localPoint);

    /**
     * @param worldRay gets a transformed Ray in world coords
     * @return localIntersect() with a local ray transformed to the local coords of this object
     */
    public IntersectionManager intersect(Ray worldRay){
        var ray = worldRay.transform(this.getTransformationMatrix().inv());
        return localIntersect(ray);
    }

    /**
     * @param worldPoint on the surface of the shape
     * @return the hitNormal vector of the surface point
     * -> here it is the same as the location vector of the worldPoint
     * -> before, worldPoint used to be hit
     *      -> changed due to not being able to display hitNormal vectors when multiplying with inverse
     */
    public Vector normalAt(Point worldPoint) {
        // transformation of worldPoint in local CS
        Point localPoint = getTransformationMatrix().inv().mult(worldPoint);

        // local hitNormal
        Vector localNormal = localPoint.sub(new Point(0, 0, 0));

        // transformation of localNormal in world CS
        Vector worldNormal = getTransformationMatrix().inv().transposed().mult(localNormal);

        // has to change to a vector, afaik only achievable through a setter
        worldNormal.setW(0);
        return worldNormal.normalized();
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Matrix getTransformationMatrix() {
        return transformationMatrix;
    }

    public void setTransformationMatrix(Matrix transformationMatrix) {
        this.transformationMatrix = transformationMatrix;
    }

}
