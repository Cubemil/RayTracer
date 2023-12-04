package org.example.math;

public class Ray {

    /*-------------------------------//attributes//-------------------------------------*/

    private final Point originPoint;
    private final Vector directionVector;

    /*-------------------------------//constructors//-----------------------------------*/

    // Creates an Object using a point of origin with a Vector as direction
    public Ray(Point originPoint, Vector directionVector) {
        this.originPoint = originPoint;
        this.directionVector = directionVector;
    }

    // Creates an Object using two Points and NORMALISING the distance Vector between the two
    public Ray(Point originPoint, Point destinationPoint) {
        this.originPoint = originPoint;
        Vector temp = destinationPoint.sub(originPoint);
        this.directionVector = temp.normalized();
    }

    /*-----------------------------//calculation methods//----------------------------------*/

    /**
     * @param t parameter for scaling along the vector
     * @return coordinates for a point on the vector
     */
    public Point pointAt(double t) {
        return originPoint.add(directionVector.mult(t));
    }

    /**
     * @param transformMatrix a transformed matrix
     * @return a new Ray with point and vector of current Ray multiplied with transformMatrix
     *      --> NOT normalised
     */
    public Ray transform(Matrix transformMatrix) {
        return new Ray(transformMatrix.mult(this.originPoint), transformMatrix.mult(this.directionVector));
    }

    /*-----------------------------//structure methods//----------------------------------*/

    public Point getOriginPoint() {
        return originPoint;
    }

    public Vector getDirectionVector() {
        return directionVector;
    }
}
