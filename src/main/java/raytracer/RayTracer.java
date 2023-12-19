package raytracer;

import math.Color;

public class RayTracer {

    private final Scene scene;
    private final Camera camera;
    private final Canvas renderTarget;

    public RayTracer(Scene scene, Camera camera) {
        this.scene = scene;
        this.camera = camera;
        // Canvas gets size from Camera
        this.renderTarget = new Canvas(camera.getWidth(), camera.getHeight());
    }

    /**
     * sends a ray from every pixel of the camera on the scene
     * and sets the colors according to the first Intersection hit
     */
    public void render() {
        for (int y = 0; y < camera.getHeight(); y++) {
            for (int x = 0; x < camera.getWidth(); x++) {
                Ray ray = camera.generateRay(x, y);
                var intersectionManager = scene.traceRay(ray);
                if (intersectionManager.hit() != null) renderTarget.setPixelColor(x, y, Color.ORANGE);
                else renderTarget.setPixelColor(x, y, Color.CORNFLOWERBLUE);
            }
        }
    }

    public Canvas getRenderTarget() {
        return renderTarget;
    }

}
