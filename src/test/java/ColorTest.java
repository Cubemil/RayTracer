import org.example.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    // Tests default empty Constructor. New Color should be Black
    @Test
    void defaultColorValuesTest() {
        Color newColor = new Color();
        assertEquals(new Color(0.0, 0.0, 0.0), newColor);
    }

    // Standard Color Constructor Test
    @Test
    void standardConstructorTest() {
        Color newColor = new Color(0.3, 0.2, 0.4);
        assertEquals(0.3, newColor.getR());
        assertEquals(0.2, newColor.getG());
        assertEquals(0.4, newColor.getB());
        assertEquals(new Color(0.3, 0.2, 0.4), newColor);
    }

    // adds 2 Colors
    @Test
    void addingColorsTest() {
        Color color1 = new Color(0.9, 0.6, 0.75);
        Color color2 = new Color(0.7,0.1,0.25);

        assertEquals(new Color(1.6, 0.7, 1.0), color1.add(color2));
    }

    // multiplies two colors by different scalars
    @Test
    void multiplyColorByScalarTest() {
        Color color1 = new Color(0.2, 0.3, 0.4);
        Color color2 = new Color(0.8, 1.2, 1.6);

        Color result = new Color(0.4, 0.6, 0.8);
        assertEquals(result, color1.mult(2));
        assertEquals(result, color2.mult(0.5));
    }

    // multiplying colors using the Hadamard product
    @Test
    void hadamardProductTest() {
        Color color1 = new Color(1, 0.2, 0.4);
        Color color2 = new Color(0.9, 1, 0.1);

        assertEquals(new Color(0.9, 0.2, 0.04), color1.mult(color2));
    }

    // tests the clamping method for Color objects
    @Test
    void clampingTest() {
        assertEquals(new Color(0.1, 0.2, 0.3), new Color(0.1, 0.2, 0.3).clamped());
        assertEquals(new Color(0.0, 0.0, 0.3), new Color(-0.1, -0.2, 0.3).clamped());
        assertEquals(new Color(0.1, 1.0, 1.0), new Color(0.1,1.2,1.3).clamped());
    }


}