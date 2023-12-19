package raytracer;

import math.*;

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
        this.transform = Matrix.identityMatrix();
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
    private double aspectRatio() {
        return (double) width / height;
    }

    /**
     * @return opposite side of the right-angled triangle between vpn and origin
     * *** toRadians converts angle measured in degrees to an approximately equivalent angle measured in radians. ***
     * -> because Math.tan() expects it
     */
    private double halfView() {
        return Math.tan(Math.toRadians(fov / 2.0));
    }

    /**
     * @return size of one pixel in the image
     * checks for orientation format -> adjusts the size accordingly
     */
    public double pixelSize() {
        var halfView = halfView();

        if (aspectRatio() < 1) {    //vertical format
            halfHeight = halfView;
            halfWidth = halfView * aspectRatio();
        } else {                    //horizontal format
            halfWidth = halfView;
            halfHeight = halfView / aspectRatio();
        }

        // pixels are assumed to be square -> single value
        return (2 * halfWidth) / width;
    }

    /**
     * @param pixelX width of the target pixel
     * @param pixelY height of the target pixel
     * @return a ray that goes from the camera to the given pixel
     * --> the returning pixel from the Canvas is the same as in the result Image
     */
    public Ray generateRay(int pixelX, int pixelY) {
        // determine offset from edge of Canvas to pixel center
        var pixelSize = pixelSize();
        var xOffset = (pixelX + 0.5) * pixelSize;
        var yOffset = (pixelY + 0.5) * pixelSize;

        /* calculate pixel coordinate in world coords
        *  -> vpn goes through Canvas center
        *  -> Offset must be subtracted from half width/height */
        var worldX = halfWidth - xOffset;
        var worldY = halfHeight - yOffset;

        // cam origin and pixel coords transformed with view-transformation (Canvas at z=-1)
        var origin = transform.inv().mult(new Point(0, 0, 0));
        var pixel = transform.inv().mult(new Point(worldX, worldY, -1));

        // ray direction
        var direction = pixel.sub(origin).normalized();
        return new Ray(origin, direction);
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

    public void setTransform(Matrix newTransform) {
        this.transform = newTransform;
    }
}
