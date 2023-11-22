import org.example.Matrix;
import org.example.Vector;
import org.example.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void constructionAndInspection_4x4_Test() {
        Matrix m = new Matrix(new double[][]{{1, 2, 3, 4}, {5.5, 6.5, 7.5, 8.5}, {9, 10, 11, 12}, {13.5, 14.5, 15.5, 16.5}});
        assertEquals(1, m.get(0, 0));
        assertEquals(4, m.get(0, 3));
        assertEquals(5.5, m.get(1,0));
        assertEquals(7.5, m.get(1, 2));
        assertEquals(11, m.get(2, 2));
        assertEquals(13.5, m.get(3, 0));
        assertEquals(15.5, m.get(3, 2));
    }

    @Test
    void twoTimesTwoMatrixTest() {
        Matrix m = new Matrix(new double[][]{{-3, 5}, {1, -2}});
        assertEquals(-3, m.get(0, 0));
        assertEquals(5, m.get(0, 1));
        assertEquals(1, m.get(1, 0));
        assertEquals(-2, m.get(1, 1));
    }

    @Test
    void threeTimesThreeMatrixTest() {
        Matrix m = new Matrix(new double[][]{{-3, 5, 0}, {1, -2, -7}, {0, 1, 1}});
        assertEquals(-3, m.get(0, 0));
        assertEquals(-2, m.get(1, 1));
        assertEquals(1, m.get(2, 2));
    }

    @Test
    void default4x4MatrixTest() {
        Matrix m = new Matrix();
        assertEquals(new Matrix(new double[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}), m);
    }

    @Test
    void matrixEqualityTest() {
        Matrix m1 = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 8, 7, 6}, {5, 4, 3, 2}});
        Matrix m2 = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 8, 7, 6}, {5, 4, 3, 2}});
        assertEquals(m1, m2);

        Matrix m3 = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 8, 7, 6}, {5, 4, 3, 2}});
        Matrix m4 = new Matrix(new double[][]{{2, 3, 4, 5}, {6, 7, 8, 9}, {8, 7, 6, 5}, {4, 3, 2, 1}});
        assertNotEquals(m3, m4);
    }

    @Test
    void differentSizedMatrixEqualityTest() {
        // Test with dimension parameter
        Matrix m1 = new Matrix(4);
        Matrix m2 = new Matrix(6);
        assertNotEquals(m1, m2);

        // Test with different array lengths
        Matrix m3 = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 8, 7, 6}, {5, 4, 3, 2}});
        Matrix m4 = new Matrix(new double[][]{{2, 3}, {6, 7}});
        assertNotEquals(m3, m4);

    }

    @Test
    void multiplyingTwoMatricesTest() {
        Matrix m1 = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 8, 7, 6}, {5, 4, 3, 2}});
        Matrix m2 = new Matrix(new double[][]{{-2, 1, 2, 3}, {3, 2, 1, -1}, {4, 3, 6, 5}, {1, 2, 7, 8}});

        assertEquals(new Matrix(new double[][]{{20, 22, 50, 48}, {44, 54, 114, 108}, {40, 58, 110, 102}, {16, 26, 46, 42}}), m1.mult(m2));
    }

    @Test
    void multiplyingWithIdentityMatrixTest() {
        Matrix m1 = new Matrix(new double[][]{{20, 22, 50, 48}, {44, 54, 114, 108}, {40, 58, 110, 102}, {16, 26, 46, 42}});
        Matrix m2 = Matrix.identityMatrix(new Matrix(4));

        assertEquals(m1, m1.mult(m2));
        assertEquals(m1, m2.mult(m1));
    }

    @Test
    void differentSizedMatrixMultiplyingTest() {
        Matrix m1 = new Matrix(4);
        Matrix m2 = new Matrix(6);
        boolean thrown = false;
        try {
            m1.mult(m2);
        } catch (RuntimeException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void matrixTimesVectorTest() {
        Matrix m = new Matrix(new double[][]{{1, 2, 3, 4}, {2, 4, 4, 2}, {8, 6, 4, 1}, {0, 0, 0, 1}});
        Vector v = new Vector(1, 2, 3);
        assertEquals(new Vector(14, 22, 32), m.mult(v));
    }

    @Test
    void identityMatrixTimesVector() {
        Vector v = new Vector(1, 2, 3);
        Matrix m = Matrix.identityMatrix(new Matrix());
        assertEquals(v, m.mult(v));
    }

    @Test
    void matrixTimesPointTest() {
        Matrix m = new Matrix(new double[][]{{1, 2, 3, 4}, {2, 4, 4, 2}, {8, 6, 4, 1}, {0, 0, 0, 1}});
        Point p = new Point(1, 2, 3);
        assertEquals(new Point(18, 24, 33), m.mult(p));
    }

    @Test
    void identityMatrixTimesPointTest() {
        Point p = new Point(1, 2, 3);
        assertEquals(p, Matrix.identityMatrix(new Matrix()).mult(p));
    }

    @Test
    void transposeMatrixTest() {
        Matrix m = new Matrix(new double[][]{{0, 9, 3, 0}, {9, 8, 0, 8}, {1, 8, 5, 3}, {0, 0, 5, 8}});
        assertEquals(new Matrix(new double[][]{{0, 9, 1, 0}, {9, 8, 8, 0}, {3, 0, 5, 5}, {0, 8, 3, 8}}), m.trans());
    }

    @Test
    void transposeIdentityMatrixTest() {
        Matrix m = Matrix.identityMatrix(new Matrix());
        assertEquals(m, m.trans());
    }

    @Test
    void determinant2x2MatrixTest() {
        Matrix m = new Matrix(new double[][]{{1, 5}, {-3, 2}});
        assertEquals(17, m.det());
    }

    @Test
    void subMatrix3x3Test() {
        Matrix m = new Matrix(new double[][]{{1, 5, 0}, {-3, 2, 7}, {0, 6, -3}});
        assertEquals(new Matrix(new double[][]{{-3, 2}, {0, 6}}), m.subMatrix(0, 2));
    }

    @Test
    void subMatrix4x4Test() {
        Matrix m = new Matrix(new double[][]{{-6, 1, 1, 6}, {-8, 5, 8, 6}, {-1, 0, 8, 2}, {-7, 1, -1, 1}});
        assertEquals(new Matrix(new double[][]{{-6, 1, 6}, {-8, 8, 6}, {-7, -1, 1}}), m.subMatrix(2, 1));
    }

    @Test
    void minorOfA3x3MatrixTest() {
        Matrix m = new Matrix(new double[][]{{3, 5, 0}, {2, -1, -7}, {6, -1, 5}});
        Matrix subM = m.subMatrix(1, 0);
        double determinant = 25;
        double minor = 25;

        assertEquals(determinant, subM.det());
        assertEquals(minor, m.minor(1, 0));
    }

    @Test
    void cofactor3x3MatrixTest() {
        Matrix m = new Matrix(new double[][]{{3, 5, 0}, {2, -1, -7}, {6, -1, 5}});
        assertEquals(-12, m.cofactor(0, 0));
        assertEquals(-25, m.cofactor(1, 0));
    }

    @Test
    void determinant3x3MatrixTest() {
        Matrix m = new Matrix(new double[][]{{1, 2, 6}, {-5, 8, -4}, {2, 6, 4}});
        assertEquals(56, m.cofactor(0, 0));
        assertEquals(12, m.cofactor(0, 1));
        assertEquals(-46, m.cofactor(0, 2));
        assertEquals(-196, m.det());
    }

    @Test
    void determinant4x4MatrixTest() {
        Matrix m = new Matrix(new double[][]{{-2, -8, 3, 5}, {-3, 1, 7, 3}, {1, 2, -9, 6}, {-6, 7, 7, -9}});
        assertEquals(690, m.cofactor(0, 0));
        assertEquals(447, m.cofactor(0, 1));
        assertEquals(210, m.cofactor(0, 2));
        assertEquals(51, m.cofactor(0, 3));
        assertEquals(-4071, m.det());
    }

    @Test
    void testInvertibleMatrixForInvertibility() {
        Matrix m = new Matrix(new double[][]{{6, 4, 4, 4}, {5, 5, 7, 6}, {4, -9, 3, -7}, {9, 1, 7, -6}});
        boolean thrown = false;
        try {
            assertEquals(-2120, m.det());
        } catch (ArithmeticException e) {
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void testNonInvertibleMatrixForInvertibility() {
        Matrix m = new Matrix(new double[][]{{-4, 2, -2, -3}, {9, 6, 2, 6}, {0, -5, 1, -5}, {0, 0, 0, 0}});
        boolean thrown = false;
        try {
            m.inv().det();
        } catch (ArithmeticException e) {
            e.printStackTrace();
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void calculatingInverseOfAMatrix() {
        Matrix m = new Matrix(new double[][]{{-5, 2, 6, -8}, {1, -5, 1, 8}, {7, 7, -6, -7}, {1, -3, 7, 4}});
        Matrix b = m.inv();

        assertEquals(532, m.det(), 1e-10);
        assertEquals(-160, m.cofactor(2, 3), 1e-10);
        assertEquals((-160/532.0), b.get(3, 2), 1e-10);
        assertEquals(105, m.cofactor(3, 2), 1e-10);
        assertEquals((105/532.0), b.get(2, 3), 1e-10);

        Matrix expected = new Matrix(new double[][]{
                {0.21805, 0.45113, 0.24060, -0.04511},
                {-0.80827, -1.45677, -0.44361, 0.52068},
                {-0.07895, -0.22368, -0.05263, 0.19737},
                {-0.52256, -0.81391, -0.30075, 0.30639}});

        assertEquals(expected, b);
    }

    @Test
    void calculatingInverseMatrixTest2() {
        Matrix m = new Matrix(new double[][]{{8, -5, 9, 2}, {7, 5, 6, 1}, {-6, 0, 9, 6}, {-3, 0, -9, -4}});
        Matrix invM = new Matrix(new double[][]{
                {-0.15385, -0.15385, -0.28205, -0.53846},
                {-0.07692, 0.12308, 0.02564, 0.03077},
                {0.35897, 0.35897, 0.43590, 0.92308},
                {-0.69231, -0.69231, -0.76923, -1.92308}});

        assertEquals(invM, m.inv());
    }

    @Test
    void multiplyingAProductByItsInverseTest() {
        Matrix m = new Matrix(new double[][]{{3, -9, 7, 3}, {3, -8, 2, -9}, {-4, 4, 4, 1}, {-6, 5, -1, 1}});
        Matrix b = new Matrix(new double[][]{{8, 2, 2, 2}, {3, -1, 7, 0}, {7, 0, 5, 4}, {6, -2, 0, 5}});
        Matrix c = m.mult(b);

        assertEquals(m, c.mult(b.inv()));
    }

}