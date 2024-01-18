package raytracer.geometry;

import math.MUtils;
import raytracer.Intersection;
import raytracer.IntersectionManager;
import raytracer.Ray;
import math.Point;
import math.Vector;

public class Plane extends Shape {

    public Plane() {
        super();
    }

    @Override
    public IntersectionManager localIntersect(Ray localRay) {
        IntersectionManager xs = new IntersectionManager();

        // Check if the ray is parallel or nearly parallel to the plane.
        if (Math.abs(localRay.getDirectionVector().y()) < MUtils.getEPSILON()) {
            return xs; // No intersection.
        }

        // Calculate the intersection point with the plane.
        double t = -localRay.getOriginPoint().y() / localRay.getDirectionVector().y();
        xs.add(new Intersection(t, this));

        return xs;
    }

    @Override
    public Vector localNormalAt(Point localPoint) {
        // The normal vector for the plane is always (0, 1, 0) in local coordinates.
        return new Vector(0, 1, 0);
    }

    @Override
    public Vector normalAt(Point worldPoint) {
        // Transformiere den Punkt in das lokale Koordinatensystem der Ebene
        Point localPoint = getTransformationMatrix().inv().mult(worldPoint);

        // Berechne die lokale Normale (ist bei einer Ebene immer (0, 1, 0))
        Vector localNormal = localNormalAt(localPoint);

        // Transformiere die lokale Normale zurück in das Weltkoordinatensystem
        Vector worldNormal = getTransformationMatrix().inv().transposed().mult(localNormal);

        // Normalisiere die Welt-Normale
        return worldNormal.normalized();
    }

}
