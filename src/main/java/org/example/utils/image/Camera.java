package org.example.utils.image;

import org.example.math.*;

public class Camera {

    /*---------------------------------//attributes//---------------------------------*/

    private final int width;      // size of the created image
    private final int height;
    private final double fov;     // field of view angle of the camera
    private Matrix transform;       // View-Transformation -> default: id. matrix

    /*--------------------------------//constructors//--------------------------------*/

    /**
     * @creates Camera object with the identity-matrix as View-Transformation
     */
    public Camera(int width, int height, double fov) {
        this.width = width;
        this.height = height;
        this.fov = fov;
        this.transform = Matrix.identityMatrix(new Matrix());
    }

    /**
     * @creates Camera object with an externally calculated View-Transformation
     */
    public Camera(int width, int height, double fov, Matrix viewTransform) {
        this(width, height, fov);
        this.transform = viewTransform;
    }

    /**
     * @creates Camera object with an internally calculated View-Transformation
     */
    public Camera(int width, int height, double fov, Point position, Point lookAt, Vector up) {
        this(width, height, fov, Matrix.viewTransform(position, lookAt, up));
    }

    /*----------------------------//calculation functions//---------------------------*/

    /**
     * @return aspect ratio of the pixel image to its height
     */
    public int aspectRatio() {
        return width / height;
    }


    /*-----------------------------//structure functions//----------------------------*/

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getFov() {
        return fov;
    }

    public Matrix getTransform() {
        return transform;
    }

}
