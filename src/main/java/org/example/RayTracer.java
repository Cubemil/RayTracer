package org.example;

public class RayTracer {
    public static void main(String[] args) {
        int width = 960;
        int height = 540;

        // Testing canvas
        Canvas dirVectorImage = generateDirectionVectorImage(width, height);
        dirVectorImage.writeFile();
        Canvas pixelCoordinatesImage = generatePixelCoordinatesImage(width, height);
        pixelCoordinatesImage.writeFile();
        Canvas vectorLengthImage = generateVectorLengthImage(width, height);
        vectorLengthImage.writeFile();

        // Testing spheres and rays intersecting
        Canvas dirRayImg = generateRayDirectionImage(width, height);
        dirRayImg.writeFile();
        Canvas findSphereImg = generateFindingSphereImage(width, height);
        findSphereImg.writeFile();
        Canvas tValueSphereImg = generateTValueSphereImage(width, height);
        tValueSphereImg.writeFile();
        Canvas normalVecSphereImg = generateNormalVectorSphereImage(width, height);
        normalVecSphereImg.writeFile();

    }

    /*---------------------------------canvas testing---------------------------------*/


    private static Canvas generateDirectionVectorImage(int width, int height) {
        Canvas image = new Canvas(width, height, "direction_vector");
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
        return image;
    }

    private static Canvas generatePixelCoordinatesImage(int width, int height) {
        Canvas image = new Canvas(width, height, "pixel_coordinates");

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double normalizedX = x / (double) width;
                double normalizedY = y / (double) height;

                Color colorValue = new Color(normalizedX, normalizedY, 0.0);
                image.setPixelColor(x, y, colorValue);
            }
        }
        return image;
    }

    private static Canvas generateVectorLengthImage(int width, int height) {
        Canvas image = new Canvas(width, height, "vector_length");
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
        return image;
    }

    /*---------------------------------intersections testing---------------------------------*/

    private static Canvas generateRayDirectionImage(int width, int height) {
        Canvas image = new Canvas(width, height, "ray_direction");

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

        return image;
    }

    private static Canvas generateFindingSphereImage(int width, int height) {
        Canvas image = new Canvas(width, height, "finding_sphere");
        Sphere sphere = new Sphere();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double px = x - width / 2.0;
                double py = height / 2.0 - y;

                Point target = new Point(px * 0.01, py * 0.01, 0);
                Ray ray = new Ray(new Point(0,0,-10), target);
                sphere.intersect(ray);

                // no intersections -> blue
                if (sphere.getIntersections().count() == 0) image.setPixelColor(x, y, new Color(0, 0, 1));
                // at least one intersection -> orange
                else image.setPixelColor(x, y, new Color(1, 0.5, 0));
            }
        }

        return image;
    }

    private static Canvas generateTValueSphereImage(int width, int height) {
        Canvas image = new Canvas(width, height, "t_value_sphere");
        Sphere sphere = new Sphere();
        double tValue;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double px = x - width / 2.0;
                double py = height / 2.0 - y;

                Point target = new Point(px * 0.01, py * 0.01, 0);
                Ray ray = new Ray(new Point(0,0,-10), target);
                sphere.intersect(ray);

                // no intersection: background blue
                if (sphere.getIntersections().count() == 0) {
                    image.setPixelColor(x, y, new Color(0, 0, 1));
                } else {
                    // Distance to origin 9 - 10 units -> subtract 9
                    tValue = sphere.getIntersections().get(0).getT() - 9;
                    // interpretation as a grayscale
                    Color colorOfT = new Color(tValue, tValue, tValue);
                    image.setPixelColor(x, y, colorOfT);
                }
            }
        }
        return image;
    }

    private static Canvas generateNormalVectorSphereImage(int width, int height) {
        Canvas image = new Canvas(width, height, "normalVector_sphere");
        Sphere sphere = new Sphere();
        double normalVal;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double px = x - width / 2.0;
                double py = height / 2.0 - y;

                Point target = new Point(px * 0.01, py * 0.01, 0);
                Ray ray = new Ray(new Point(0,0,-10), target);
                sphere.intersect(ray);

                // no intersection: background blue
                if (sphere.getIntersections().count() == 0) {
                    image.setPixelColor(x, y, new Color(0, 0, 1));
                } else {
                    // Intercepting point at the sphere from the ray (t value)
                    Point interceptPoint = ray.pointAt(sphere.getIntersections().get(0).getT());
                    // use t value as parameter for getting the normal vector from the sphere surface point
                    Vector normalVec = sphere.normalAt(interceptPoint);
                    // using the absolute components from the vector we can create a new Color in rgb, not grayscale
                    Color newColor = new Color(Math.abs(normalVec.x()), Math.abs(normalVec.y()), Math.abs(normalVec.z()));
                    // setting the pixel to the new color
                    image.setPixelColor(x, y, newColor);
                }
            }
        }
        return image;

    }

    /*---------------------------------helper functions---------------------------------*/

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