package raytracer;

import math.Color;
import math.Point;

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
                var hit = scene.traceRay(ray).hit();

                if (hit == null) renderTarget.setPixelColor(x, y, Color.CORNFLOWERBLUE);
                else {
                    // get Material from hit-shape
                    Material mat = hit.shape().getMaterial();
                    // get surface point at hit
                    Point position = ray.pointAt(hit.t());
                    // direction to observer is negative ray direction
                    var observer = ray.getDirectionVector().neg();
                    // normal at intersection
                    var normal = hit.shape().normalAt(position);

                    // execute Phong lighting
                    var color = mat.phongLighting(scene.getLightSources().get(0), position, observer, normal);
                    renderTarget.setPixelColor(x, y, color);
                }
            }
        }
    }

    /**
     * @return reference to renderTarget Canvas object
     */
    public Canvas getRenderTarget() {
        return renderTarget;
    }

}
