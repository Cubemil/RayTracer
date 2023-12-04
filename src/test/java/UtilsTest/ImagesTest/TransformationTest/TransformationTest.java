package UtilsTest.ImagesTest.TransformationTest;

import org.example.Math.Matrix;
import org.example.Math.Point;
import org.example.Math.Ray;
import org.example.Math.Vector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransformationTest {

    // Matrix transformation tests
    @Test
    void multiplyingByTranslationMatrixTest() {
        Matrix transform = Matrix.translate(5.0, -3.0, 2.0);
        Point p = new Point(-3, 4, 5);

        assertEquals(new Point(2, 1, 7), transform.mult(p));
    }

    @Test
    void multiplyingByInverseOfTranslationMatrix() {
        Matrix transform = Matrix.translate(5, -3, 2);
        Matrix inv = transform.inv();
        Point p = new Point(-3, 4, 5);

        assertEquals(new Point(-8, 7, 3.0), inv.mult(p));
    }

    @Test
    void translationNoEffectVectorTest() {
        Matrix transform = Matrix.translate(5, -3, 2);
        Vector v = new Vector(-3, 4, 5);

        assertEquals(v, transform.mult(v));
    }

    @Test
    void scalingMatrixAppliedToPointTest() {
        Matrix transform = Matrix.scale(2, 3, 4);
        Point p = new Point(-4, 6, 8);

        assertEquals(new Point(-8, 18, 32), transform.mult(p));
    }

    @Test
    void scalingMatrixAppliedToVectorTest() {
        Matrix transform = Matrix.scale(2, 3, 4);
        Vector p = new Vector(-4, 6, 8);

        assertEquals(new Vector(-8, 18, 32), transform.mult(p));
    }

    @Test
    void multiplyingByInverseOfScalingMatrixTest() {
        Matrix transform = Matrix.scale(2, 3, 4);
        Matrix inv = transform.inv();
        Vector v = new Vector(-4, 6, 8);
        assertEquals(new Vector(-2, 2, 2), inv.mult(v));
    }

    @Test
    void reflectionScalingByNegativeValueTest() {
        Matrix transform = Matrix.scale(-1, 1, 1);
        Point p = new Point(2, 3, 4);

        assertEquals(new Point(-2, 3, 4), transform.mult(p));
    }

    @Test
    void rotatingPointAroundXAxisTest() {
        Point p = new Point(0, 1, 0);
        Matrix halfQuarter = Matrix.rotateX(0.7853981);
        Matrix fullQuarter = Matrix.rotateX(1.5707963);

        assertEquals(new Point(0, 0.707106, 0.707106), halfQuarter.mult(p));
        assertEquals(new Point(0, 0, 1), fullQuarter.mult(p));
    }

    @Test
    void inverseXRotationRotatesInOppositeDirectionTest() {
        Point p = new Point(0, 1, 0);
        Matrix halfQuarter = Matrix.rotateX(0.7853981);
        Matrix inv = halfQuarter.inv();

        assertEquals(new Point(0, 0.707106, -0.707106), inv.mult(p));
    }

    @Test
    void rotatingPointAroundYAxisTest() {
        Point p = new Point(0, 0, 1);
        Matrix halfQuarter = Matrix.rotateY(0.7853981);
        Matrix fullQuarter = Matrix.rotateY(1.5707963);

        assertEquals(new Point(0.707106,0, 0.707106), halfQuarter.mult(p));
        assertEquals(new Point(1, 0, 0), fullQuarter.mult(p));
    }

    @Test
    void rotatingPointAroundZAxisTest() {
        Point p = new Point(0, 1, 0);
        Matrix halfQuarter = Matrix.rotateZ(0.7853981);
        Matrix fullQuarter = Matrix.rotateZ(1.5707963);

        assertEquals(new Point(-0.707106,0.707106, 0), halfQuarter.mult(p));
        assertEquals(new Point(-1, 0, 0), fullQuarter.mult(p));
    }

    @Test
    void individualTransformationsAreAppliedInSequenceTest() {
        Point p = new Point(1, 0, 1);
        Matrix A = Matrix.rotateX(1.5707963);
        Matrix B = Matrix.scale(5, 5, 5);
        Matrix C = Matrix.translate(10, 5, 7);
        //apply rotation first
        Point p2 = A.mult(p);
        assertEquals(new Point(1, -1, 0), p2);
        //then apply scaling
        Point p3 = B.mult(p2);
        assertEquals(new Point(5, -5, 0), p3);
        //then apply translation
        Point p4 = C.mult(p3);
        assertEquals(new Point(15, 0, 7), p4);
    }

    @Test
    void chainedTransformationsMustBeAppliedInReverseOrderTest() {
        Point p = new Point(1, 0, 1);
        Matrix A = Matrix.rotateX(1.5707963);
        Matrix B = Matrix.scale(5, 5, 5);
        Matrix C = Matrix.translate(10, 5, 7);
        Matrix transform = C.mult(B.mult(A));

        assertEquals(new Point(15, 0, 7), transform.mult(p));
    }

    // Ray transformation tests
    @Test
    void rayTranslatingTest() {
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(0, 1, 0));
        Matrix transform = Matrix.translate(3, 4, 5);

        Ray ray2 = ray.transform(transform);
        assertEquals(new Point(4,6,8), ray2.getOriginPoint());
    }

    @Test
    void rayScalingTest() {
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(0, 1, 0));
        Matrix transform = Matrix.scale(2, 3, 4);

        Ray ray2 = ray.transform(transform);
        assertEquals(new Point(2, 6, 12), ray2.getOriginPoint());
        assertEquals(new Vector(0,3,0), ray2.getDirectionVector());
    }

}