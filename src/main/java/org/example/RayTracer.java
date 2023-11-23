package org.example;

public class RayTracer {
    public static void main(String[] args) {
        int width = 800;
        int height = 600;

        // Testing canvas
        Canvas dirVectorImg = generateDirectionVectorImage(width, height);
        dirVectorImg.writeFile("direction_vector");

        // Testing spheres and rays intersecting
        Canvas dirRayImg = generateRayDirectionImage(width, height,"ray_direction");
        dirRayImg.writeFile();

        Canvas findSphereImg = generateFindingSphereImage(width, height, "finding_sphere");
        findSphereImg.writeFile();



        /*
        Canvas image2 = generatePixelCoordinatesImage(width, height);
        image2.writeFile("pixel_coordinates");
        Canvas image3 = generateVectorLengthImage(width, height);
        image3.writeFile("vector_length");*/
    }

    private static Canvas generateRayDirectionImage(int width, int height, String fileName) {
        Canvas image = new Canvas(width, height, fileName);

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

                Color colorValue = new Color(absX, absY, 0.0);
                image.setPixelColor(x, y, colorValue);
            }
        }

        return image;
    }

    private static Canvas generateFindingSphereImage(int width, int height, String fileName) {
        Canvas image = new Canvas(width, height, fileName);
        Sphere sphere = new Sphere();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double px = x - width / 2.0;
                double py = height / 2.0 - y;

                Point target = new Point(px * 0.01, py * 0.01, 0);
                Ray ray = new Ray(new Point(0,0,-10), target);
                sphere.intersect(ray);

                if (sphere.getIntersections().count() != 0) {
                    image.setPixelColor(x, y, new Color(1, 0.5, 0));
                }
                else{
                    image.setPixelColor(x, y, new Color(0, 0 ,1));
                }
            }
        }

        return image;
    }

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

    private static Canvas generateDirectionVectorImage(int width, int height) {
        Canvas image = new Canvas(width, height);

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
        Canvas image = new Canvas(width, height);

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
        Canvas image = new Canvas(width, height);
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

}