package org.example.utils.image;

import org.example.math.*;

public class Camera {

    /*---------------------------------//attributes//---------------------------------*/

    private final int width;      // size of the created image
    private final int height;
    private double halfWidth;     // half Size
    private double halfHeight;
    private final double fov;     // field of view angle of the camera
    private Matrix transform;     // View-Transformation -> default: id. matrix

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
    private int aspectRatio() {
        return width / height;
    }

    /**
     * @return opposite side of the right-angled triangle between vpn and origin
     */
    private double halfView() {
        return Math.tan(fov / 2.0);
    }

    /**
     * @return size of one pixel in the image
     * checks for orientation format -> adjusts the size accordingly
     */
    public double pixelSize() {
        if (aspectRatio() < 1) {    //vertical format
            halfHeight = halfView();
            halfWidth = halfView() * aspectRatio();
        } else {                    //horizontal format
            halfWidth = halfView();
            halfHeight = halfView() / aspectRatio();
        }

        // pixels are assumed to be square -> single value
        return (2 * halfWidth / width);
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
