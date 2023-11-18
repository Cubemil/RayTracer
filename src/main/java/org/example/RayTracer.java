package org.example;

public class RayTracer {
    public static void main(String[] args) {
        int breite = 800;
        int hoehe = 600;

        Canvas image1 = generateDirectionVectorImage(breite, hoehe);
        image1.writeFile("direction_vector");

        Canvas image2 = generatePixelCoordinatesImage(breite, hoehe);
        image2.writeFile("pixel_coordinates");

        Canvas image3 = generateVectorLengthImage(breite, hoehe);
        image3.writeFile("vector_length");
    }

    private static Color vectorToColor(Vector vector) {
        // Normalisiere die Komponenten des Vektors auf den Bereich [0, 1]
        double normalizedX = vector.normalized().x();
        double normalizedY = vector.normalized().y();
        double normalizedZ = vector.normalized().z();

        /*** Alte Implementierung:
            double normalizedX = (vector.x() + 1.0) / 2.0;
            double normalizedY = (vector.y() + 1.0) / 2.0;
            double normalizedZ = (vector.z() + 1.0) / 2.0;
        ***/

        // Begrenze die Werte auf den Bereich [0, 1]
        normalizedX = Math.max(0.0, Math.min(1.0, normalizedX));
        normalizedY = Math.max(0.0, Math.min(1.0, normalizedY));
        normalizedZ = Math.max(0.0, Math.min(1.0, normalizedZ));

        return new Color(normalizedX, normalizedY, normalizedZ);
    }


    private static Canvas generateDirectionVectorImage(int breite, int hoehe) {
        Canvas bild = new Canvas(breite, hoehe);

        for (int x = 0; x < breite; x++) {
            for (int y = 0; y < hoehe; y++) {
                double px = x - breite / 2.0;
                double py = hoehe / 2.0 - y;

                Point observer = new Point(0, 0, -100);
                Point ziel = new Point(px, py, 0);
                Ray strahl = new Ray(observer, ziel);

                Color farbwert = vectorToColor(strahl.getDirectionVector());
                bild.setPixelColor(x, y, farbwert);
            }
        }

        return bild;
    }

    private static Canvas generatePixelCoordinatesImage(int breite, int hoehe) {
        Canvas bild = new Canvas(breite, hoehe);

        for (int x = 0; x < breite; x++) {
            for (int y = 0; y < hoehe; y++) {
                double normalizedX = x / (double) breite;
                double normalizedY = y / (double) hoehe;

                Color farbwert = new Color(normalizedX, normalizedY, 0.0);
                bild.setPixelColor(x, y, farbwert);
            }
        }

        return bild;
    }

    private static Canvas generateVectorLengthImage(int breite, int hoehe) {
        Canvas bild = new Canvas(breite, hoehe);

        Point observer = new Point(0, 0, -100);

        for (int x = 0; x < breite; x++) {
            for (int y = 0; y < hoehe; y++) {
                double px = x - breite / 2.0;
                double py = hoehe / 2.0 - y;

                Vector ziel = new Vector(px, py, 0);
                Ray strahl = new Ray(observer, ziel);

                double vectorLength = strahl.getDirectionVector().magnitude();
                double normalizedLength = vectorLength / (breite + hoehe); // Beispielhafte Normalisierung

                Color farbwert = new Color(normalizedLength, normalizedLength, normalizedLength);
                bild.setPixelColor(x, y, farbwert);
            }
        }

        return bild;

    }

}