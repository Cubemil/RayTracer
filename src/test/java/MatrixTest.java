import org.example.Matrix;
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

    //TODO test for unit matrix

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

}