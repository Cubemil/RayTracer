package raytracer;

import math.Color;

public class RayTracer {

    private final Scene scene;
    private final Camera camera;
    private final Canvas renderTarget;
    private int maxDepth;

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
                setPixelColor(x, y);
    }

    /**
     * @param x - Coordinate of the image
     * @param y - Coordinate of the image
     * sets Color using HitInfo -> using phongLighting
     */
    private void setPixelColor(int x, int y) {
        Ray ray = camera.generateRay(x, y);
        var hit = scene.traceRay(ray).hit();

        // background color
        if (hit == null) {
            renderTarget.setPixelColor(x, y, Color.BLACK);
        } else {
            Color color = scene.colorAt(ray, maxDepth);

            // set pixel to Color
            renderTarget.setPixelColor(x, y, color.clamped());
        }
    }

    /**
     * @return reference to renderTarget Canvas object
     */
    public Canvas getRenderTarget() {
        return renderTarget;
    }

}
