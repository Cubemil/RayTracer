import org.example.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    // Tests default empty Constructor. New Color should be Black
    @Test
    void defaultColorValuesTest() {
        Color newColor = new Color();
        assertTrue(Color.equals(new Color(0.0, 0.0, 0.0), newColor));
    }

    // Standard Color Constructor Test
    @Test
    void standardConstructorTest() {
        Color newColor = new Color(0.3, 0.2, 0.4);
        assertEquals(0.3, newColor.getR());
        assertEquals(0.2, newColor.getG());
        assertEquals(0.4, newColor.getB());
        assertTrue(Color.equals(new Color(0.3, 0.2, 0.4), newColor));
    }

    // adds 2 Colors
    @Test
    void addingColorsTest() {
        Color color1 = new Color(0.9, 0.6, 0.75);
        Color color2 = new Color(0.7,0.1,0.25);

        assertTrue(Color.equals(new Color(1.6, 0.7, 1.0), Color.add(color1, color2)));
    }

    // multiplies two colors by different scalars
    @Test
    void multiplyColorByScalarTest() {
        Color color1 = new Color(0.2, 0.3, 0.4);
        Color color2 = new Color(0.8, 1.2, 1.6);

        Color result = new Color(0.4, 0.6, 0.8);
        assertTrue(Color.equals(result, Color.multiply(color1, 2)));
        assertTrue(Color.equals(result, Color.multiply(color2, 0.5)));
    }

    // multiplying colors using the Hadamard product
    @Test
    void hadamardProductTest() {
        Color color1 = new Color(1, 0.2, 0.4);
        Color color2 = new Color(0.9, 1, 0.1);

        // TODO Fix floating point precision error
        assertTrue(Color.equals(new Color(0.9, 0.2, 0.04000000000000001), Color.multiply(color1, color2)));
    }

    // tests the clamping method for Color objects
    @Test
    void clampingTest() {
        assertTrue(Color.equals(new Color(0.1, 0.2, 0.3), Color.clamped(new Color(0.1, 0.2, 0.3))));
        assertTrue(Color.equals(new Color(0.0, 0.0, 0.3), Color.clamped(new Color(-0.1, -0.2, 0.3))));
        assertTrue(Color.equals(new Color(0.1, 1.0, 1.0), Color.clamped(new Color(0.1, 1.2, 1.3))));
    }


}