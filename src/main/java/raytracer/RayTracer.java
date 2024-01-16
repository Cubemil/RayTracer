package raytracer;

import math.Color;

public class RayTracer {

    private final Scene scene;
    private final Camera camera;
    private final Canvas renderTarget;
    private final int maxDepth;

    public RayTracer(Scene scene, Camera camera) {
        this.scene = scene;
        this.camera = camera;
        // Canvas gets size from Camera
        this.renderTarget = new Canvas(camera.getWidth(), camera.getHeight());
        this.maxDepth = 5;
    }

    public RayTracer(Scene scene, Camera camera, int maxDepth) {
        this.scene = scene;
        this.camera = camera;
        // Canvas gets size from Camera
        this.renderTarget = new Canvas(camera.getWidth(), camera.getHeight());
        this.maxDepth = maxDepth;
    }

    /**
     * sends a ray from every pixel of the camera on the scene
     * and sets the colors according to the first Intersection hit
     */
    public void render() {
        for (int y = 0; y < camera.getHeight(); y++)
            for (int x = 0; x < camera.getWidth(); x++)
                //TODO un-hardcode 5-.
                setPixelColor(x, y, 5);
    }

    /**
     * @param x             - Coordinate of the image
     * @param y             - Coordinate of the image
     * @param sampleCount   - number of n-rays sent through the area of one pixel
     * - sends multiple rays per pixel using offsets
     * - return Color using sum of different return values
     * - adds (clamped) color to pixel
     */
    private void setPixelColor(int x, int y, int sampleCount) {
        Color superSamplingColor = Color.BLACK;
        for (int sample = 0; sample < sampleCount; sample++) {
            // create offset TODO
            double offsetX = 0;
            double offsetY = 0;

            // create ray
            Ray ray = camera.generateRay(x + offsetX, y + offsetY);
            var hit = scene.traceRay(ray).hit();

            superSamplingColor = superSamplingColor.add(scene.colorAt(ray, maxDepth));
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
