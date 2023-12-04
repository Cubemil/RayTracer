package org.example.utils.image;

import java.awt.*;
import java.util.ArrayList;

public class Scene {

    private ArrayList<Shape> objects = new ArrayList<>();

    /*TODO
    private ArrayList<LightSource> lightSources = new ArrayList<>();
    private ArrayList<Material> materials = new ArrayList<>();
    */

    public void addObject(Shape newShape) {
        this.objects.add(newShape);
    }

    public ArrayList<Shape> getObjects() {
        return objects;
    }
    
}
