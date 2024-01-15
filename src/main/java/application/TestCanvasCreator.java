package application;

import math.*;
import raytracer.Ray;
import raytracer.geometry.Sphere;
import raytracer.Canvas;
import raytracer.IntersectionManager;

public class TestCanvasCreator {
    public static void main(String[] args) {

        int width = 960;
        int height = 540;

        // Testing canvas
        generateDirectionVectorImage(width, height);
        generatePixelCoordinatesImage(width, height);
        generateVectorLengthImage(width, height);

        // Testing spheres and rays intersecting
        generateRayDirectionImage(width, height);
        generateFindingSphereImage(width, height);
        generateTValueSphereImage(width, height);
        generateNormalVectorSphereImage(width, height);

        // Testing transformed spheres
        Sphere sphere = new Sphere();
        generateSilhouette(sphere, "noTransformationSphereSilhouette");

        sphere.setTransformationMatrix(Matrix.scale(1, 0.5, 1));
        generateSilhouette(sphere, "y_scaledSphereSilhouette");

        sphere.setTransformationMatrix(Matrix.scale(0.5, 1, 1));
        generateSilhouette(sphere, "x_scaledSphereSilhouette");

        sphere.setTransformationMatrix(Matrix.rotateZ(Math.PI / 4).mult(Matrix.scale(0.5, 1, 1)));
        generateSilhouette(sphere, "rotatedAndScaledSphereSilhouette");
    }

    /*---------------------------------rendering canvas-------------------------------------*/

    /**
     * @param width width of the output image
     * @param height height of the output image
     * creates a new Canvas object that utilises the direction
     * vector to each position to represent a specific colour
     */
    private static void generateDirectionVectorImage(int width, int height) {
        Canvas image = new Canvas(width, height, "Images/TestCanvasImages/direction_vector");
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double px = x - width / 2.0;
                double py = height / 2.0 - y;

                Point observer = new Point(0, 0, -100);
                Point ziel = new Point(px, py, 0);
                Ray ray = new Ray(observer, ziel);

                Color colorValue = vectorToColor(ray.getDirectionVector());
                image.setPixelColor(x, y, colorValue);
            }
        }
        image.writeFile();
    }

    /**
     * @param width width of the output image
     * @param height height of the output image
     * creates a Canvas object that uses every pixel and their components
     * for showing a specific colour
     */
    private static void generatePixelCoordinatesImage(int width, int height) {
        Canvas image = new Canvas(width, height, "Images/TestCanvasImages/pixel_coordinates");

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double normalizedX = x / (double) width;
                double normalizedY = y / (double) height;

                Color colorValue = new Color(normalizedX, normalizedY, 0.0);
                image.setPixelColor(x, y, colorValue);
            }
        }
        image.writeFile();
    }

    /**
     * @param width width of the output image
     * @param height height of the output image
     * creates a Canvas object that uses the vector length from
     * an viewDirection point, showing a color in grayscale
     */
    private static void generateVectorLengthImage(int width, int height) {
        Canvas image = new Canvas(width, height, "Images/TestCanvasImages/vector_length");
        Point observer = new Point(0, 0, -100);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double px = x - width / 2.0;
                double py = height / 2.0 - y;
                Vector ziel = new Vector(px, py, 0);
                Ray strahl = new Ray(observer, ziel);
                double vectorLength = strahl.getDirectionVector().magnitude();
                double normalizedLength = vectorLength / (width + height); // Beispielhafte Normalisierung
                Color colorValue = new Color(normalizedLength, normalizedLength, normalizedLength);
                image.setPixelColor(x, y, colorValue);
            }
        }
        image.writeFile();
    }

    /*---------------------------------rendering intersections---------------------------------*/

    /**
     * @param width width of the output image
     * @param height height of the output image
     * creates a Canvas object that uses the direction of every
     * ray to return a color
     */
    private static void generateRayDirectionImage(int width, int height) {
        Canvas image = new Canvas(width, height, "Images/TestCanvasImages/ray_direction");

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double px = x - width / 2.0;
                double py = height / 2.0 - y;

                // artificially minimizing the image by using only 0.01 times the regular coordinates
                Point target = new Point(px * 0.01, py * 0.01, 0);
                Ray ray = new Ray(new Point(0,0,-10), target);

                // Get absolute values of x and y
                double absX = Math.abs(ray.getDirectionVector().x());
                double absY = Math.abs(ray.getDirectionVector().y());
                // Normalize values to the range [0, 1]
                absX = Math.max(0.0, Math.min(1.0, absX));
                absY = Math.max(0.0, Math.min(1.0, absY));

                // ignores blue value
                Color colorValue = new Color(absX, absY, 0.0);
                image.setPixelColor(x, y, colorValue);
            }
        }
        image.writeFile();
    }

    /**
     * @param width width of the output image
     * @param height height of the output image
     * creates a Canvas object that shows one color when
     * a ray does not intersect a sphere in the center of
     * the image and another color when it does
     */
    private static void generateFindingSphereImage(int width, int height) {
        Canvas image = new Canvas(width, height, "Images/TestSphereImages/finding_sphere");
        Sphere sphere = new Sphere();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double px = x - width / 2.0;
                double py = height / 2.0 - y;

                Point target = new Point(px * 0.01, py * 0.01, 0);
                Ray ray = new Ray(new Point(0,0,-10), target);
                IntersectionManager intersections = sphere.intersect(ray);

                // no intersections -> blue
                if (intersections.count() == 0) image.setPixelColor(x, y, new Color(0, 0, 1));
                // at least one intersection -> orange
                else image.setPixelColor(x, y, new Color(1, 0.5, 0));
            }
        }
        image.writeFile();
    }

    /**
     * @param width width of the output image
     * @param height height of the output image
     * creates a Canvas object that shows a grayscale color
     * according to the t-Value of the smaller one of two possible
     * intersection points of a ray and the sphere
     */
    private static void generateTValueSphereImage(int width, int height) {
        Canvas image = new Canvas(width, height, "Images/TestSphereImages/t_value_sphere");
        Sphere sphere = new Sphere();
        double tValue;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double px = x - width / 2.0;
                double py = height / 2.0 - y;

                Point target = new Point(px * 0.01, py * 0.01, 0);
                Ray ray = new Ray(new Point(0,0,-10), target);
                IntersectionManager intersections = sphere.intersect(ray);

                // no intersection: background blue
                if (intersections.count() == 0) {
                    image.setPixelColor(x, y, new Color(0, 0, 1));
                } else {
                    // Distance to origin 9 - 10 units -> subtract 9
                    tValue = intersections.get(0).t() - 9;
                    // interpretation as a grayscale
                    Color colorOfT = new Color(tValue, tValue, tValue);
                    image.setPixelColor(x, y, colorOfT);
                }
            }
        }
        image.writeFile();
    }

    /**
     * @param width width of the output image
     * @param height height of the output image
     * creates a Canvas object that shows an rgb color according to
     * the values of a hitNormal vector of a surface point on
     * the sphere that the current ray intercepts
     */
    private static void generateNormalVectorSphereImage(int width, int height) {
        Canvas image = new Canvas(width, height, "Images/TestSphereImages/normalVector_sphere");
        Sphere sphere = new Sphere();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double px = x - width / 2.0;
                double py = height / 2.0 - y;

                Point target = new Point(px * 0.01, py * 0.01, 0);
                Ray ray = new Ray(new Point(0,0,-10), target);
                IntersectionManager intersections = sphere.intersect(ray);

                // no intersection: background blue
                if (intersections.count() == 0) {
                    image.setPixelColor(x, y, new Color(0, 0, 1));
                } else {
                    // Intercepting point at the sphere from the ray (t value)
                    Point interceptPoint = ray.pointAt(intersections.get(0).t());
                    // use t value as parameter for getting the hitNormal vector from the sphere surface point
                    Vector normalVec = sphere.normalAt(interceptPoint);
                    // using the absolute components from the vector we can create a new Color in rgb, not grayscale
                    Color newColor = new Color(Math.abs(normalVec.x()), Math.abs(normalVec.y()), Math.abs(normalVec.z()));
                    // setting the pixel to the new color
                    image.setPixelColor(x, y, newColor);
                }
            }
        }
        image.writeFile();
    }

    /*---------------------------------rendering transformations---------------------------------*/

    /**
     * @param sphere Sphere to be showing in the Image. Can be transformed beforehand!!!
     * @param fileName Name and path of the output Image
     * writes a Canvas object with an image of the silhouette of a (transformed) sphere
     */
    private static void generateSilhouette(Sphere sphere, String fileName) {
        int WIDTH = 400;
        int HEIGHT = 400;
        double WALL_Z = 10.0;
        double WALL_SIZE = 7.0;
        double HALF_SIZE = WALL_SIZE / 2.0;
        double PIXEL_SIZE = WALL_SIZE / WIDTH;
        Canvas image = new Canvas(WIDTH,HEIGHT, "Images/TestTransformationImages/" + fileName);

        for (int y = 0; y < HEIGHT; y++) {
            double worldY = HALF_SIZE - PIXEL_SIZE * y;
            for (int x = 0; x < WIDTH; x++) {
                double worldX = -HALF_SIZE + PIXEL_SIZE * x;
                Point position = new Point(worldX, worldY, WALL_Z);
                Ray ray = new Ray(new Point(0,0,-5), position);
                IntersectionManager intersections = sphere.intersect(ray);

                // at least one intersection: red
                // no intersection: background blue
                image.setPixelColor(x, y, intersections.count() == 0 ? Color.CORNFLOWERBLUE : Color.ORANGE);

            }
        }
        image.writeFile();
    }

    /*---------------------------------helper functions---------------------------------*/

    /**
     * @param vector to be turned into a color
     * @return a new Color from the xyz-components of a vector
     * 1. the absolute value of the component is stored
     * 2. the range is limited to 0-1
     */
    private static Color vectorToColor(Vector vector) {
        // Get absolute values of x and y
        double absX = Math.abs(vector.x());
        double absY = Math.abs(vector.y());
        double absZ = Math.abs(vector.z());
        // Normalize values to the range [0, 1]
        absX = Math.max(0.0, Math.min(1.0, absX));
        absY = Math.max(0.0, Math.min(1.0, absY));
        absZ = Math.max(0.0, Math.min(1.0, absZ));

        return new Color(absX, absY, absZ);
    }

}