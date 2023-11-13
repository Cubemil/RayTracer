package org.example;

public class Ray {

    private Point originPoint;
    private Vector directionVector;

    // Creates an Object using a point of origin with a Vector as direction
    public Ray(Point originPoint, Vector directionVector) {
        this.originPoint = originPoint;
        this.directionVector = directionVector;
    }

    // Creates an Object using two Points and normalising the distance Vector between the two
    public Ray(Point originPoint, Point destinationPoint) {
        this.originPoint = originPoint;
        this.directionVector = destinationPoint.sub(originPoint).normalized();
    }

    /**
     * @param t parameter for scaling along the vector
     * @return coordinates for a point on the vector
     */
    public Point pointAt(double t) {
        return originPoint.add(directionVector.mult(t));
    }

    public Point getOriginPoint() {
        return originPoint;
    }

    public Vector getDirectionVector() {
        return directionVector;
    }
}
