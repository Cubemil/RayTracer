package raytracer;

import math.Color;
import math.Point;
import raytracer.sampler.Sampler;

public class RayTracer {

    private final Scene scene;
    private final Camera camera;
    private final Canvas renderTarget;
    private Sampler sampler;
    private int sampleCount;

    public RayTracer(Scene scene, Camera camera) {
        this.scene = scene;
        this.camera = camera;
        // Canvas gets size from Camera
        this.renderTarget = new Canvas(camera.getWidth(), camera.getHeight());
        this.sampleCount = 1;
    }

    public RayTracer(Scene scene, Camera camera, Sampler sampler, int sampleCount) {
        this.scene = scene;
        this.camera = camera;
        // Canvas gets size from Camera
        this.renderTarget = new Canvas(camera.getWidth(), camera.getHeight());
        this.sampler = sampler;
        this.sampleCount = sampleCount;
    }

    public void render() {
        for (int y = 0; y < camera.getHeight(); y++)
            for (int x = 0; x < camera.getWidth(); x++)
                findPixelColor(x, y);
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

        for (Point offset : sampler.generateSamplePoint(sampleCount)) {
            Ray ray = camera.generateRay(x + offset.x(), y + offset.y());
            superSamplingColor = superSamplingColor.add(scene.colorAt(ray, MAX_DEPTH));
        }
        // divide color by sampleCount
        Color color = superSamplingColor.mult(1.0 / sampleCount);
        // set pixel to Color
        renderTarget.setPixelColor(x, y, superSamplingColor.clamped());
    }

    /**
     * @return reference to renderTarget Canvas object
     */
    public Canvas getRenderTarget() {
        return renderTarget;
    }

}
