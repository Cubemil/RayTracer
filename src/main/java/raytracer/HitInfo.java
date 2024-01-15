package raytracer;

import math.Vector;
import math.Point;
import raytracer.geometry.Shape;

public record HitInfo(Shape object, double t, Point hit, Vector viewDirection, Vector hitNormal) {

}
