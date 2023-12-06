package org.example.utils.image;

import org.example.math.Ray;

public class RayTracer {

    private final Scene scene;
    private final Camera camera;
    private final Canvas renderTarget = new Canvas(800, 800);


    public RayTracer(Scene scene, Camera camera) {
        this.scene = scene;
        this.camera = camera;
    }

    public void render() {
        for (int y = 0; y < camera.getHeight(); y++) {
            for (int x = 0; x < camera.getWidth(); x++) {
                Ray ray = camera.generateRay(x, y);
                var intersectionManager = scene.traceRay(ray);
                if (intersectionManager.hit() != null) renderTarget.setPixelColor(x, y, Color.ORANGE);
                else renderTarget.setPixelColor(x, y, Color.BlUE);
            }
        }
    }

    public Canvas getRenderTarget() {
        return renderTarget;
    }
}
