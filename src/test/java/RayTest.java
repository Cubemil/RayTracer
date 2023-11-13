import org.example.Point;
import org.example.Ray;
import org.example.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void rayCreationAndQueryingTest() {
        Point point = new Point(1, 2, 3);
        Vector direction = new Vector(4, 5, 6);

        Ray ray = new Ray(point, direction);
        assertEquals(point, ray.getOriginPoint());
        assertEquals(direction, ray.getDirectionVector());
    }

    @Test
    void rayCreationFromTwoPointsTest() {
        Point origin = new Point(2, 3, 4);
        Point target = new Point(3, 4, 5);

        Ray ray = new Ray(origin, target);
        assertEquals(origin, ray.getOriginPoint());
        //assertEquals(new Vector(1, 1, 1), ray.getDirectionVector());
        //TODO check if direction vector is normalized (it is)

    }

    @Test
    void computingPointFromDistanceTest() {
        Ray ray = new Ray(new Point(2, 3, 4), new Vector(1, 0, 0));
        assertEquals(new Point(2, 3, 4), ray.pointAt(0));
        assertEquals(new Point(3, 3, 4), ray.pointAt(1));
        assertEquals(new Point(1, 3, 4), ray.pointAt(-1));
        assertEquals(new Point(4.5, 3, 4), ray.pointAt(2.5));

    }

}