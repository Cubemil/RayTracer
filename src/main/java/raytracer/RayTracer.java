package raytracer;

import math.Color;
import math.Point;

public class RayTracer {

    private final Scene scene;
    private final Camera camera;
    private final Canvas renderTarget;
    private final int sampleCount;

    public RayTracer(Scene scene, Camera camera) {
        this.scene = scene;
        this.camera = camera;
        // Canvas gets size from Camera
        this.renderTarget = new Canvas(camera.getWidth(), camera.getHeight());
        this.sampleCount = 1;
    }

    public RayTracer(Scene scene, Camera camera, int sampleCount) {
        this.scene = scene;
        this.camera = camera;
        // Canvas gets size from Camera
        this.renderTarget = new Canvas(camera.getWidth(), camera.getHeight());
        this.sampleCount = sampleCount;
    }

    public void render() {
        var start = System.nanoTime();

        for (int y = 0; y < camera.getHeight(); y++)
            for (int x = 0; x < camera.getWidth(); x++)
                findPixelColor(x, y);

        var timeElapsed = (System.nanoTime() - start) /  1e-9;
        System.out.printf("Render time: " + timeElapsed + " seconds");
    }

    /**
     * @param x             - Coordinate of the image
     * @param y             - Coordinate of the image
     * - sends multiple rays per pixel using offsets
     * - return Color using sum of different return values
     * - adds (clamped) color to pixel
     */
    private void findPixelColor(int x, int y) {
        int MAX_DEPTH = 5;
        Color superSamplingColor = Color.BLACK;

        for (Point offset : scene.getSampler().generateSamplePoint(sampleCount)) {
            Ray ray = camera.generateRay(x + offset.x(), y + offset.y());
            var color = scene.colorAt(ray, MAX_DEPTH);
            superSamplingColor = superSamplingColor.add(color);
        }
        // divide color by sampleCount
        var color = superSamplingColor.scaled(1.0 / sampleCount);
        // set pixel to Color
        renderTarget.setPixelColor(x, y, color.clamped());
    }

    /**
     * @return reference to renderTarget Canvas object
     */
    public Canvas getRenderTarget() {
        return renderTarget;
    }

}
