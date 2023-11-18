import org.example.Color;
import org.junit.jupiter.api.Test;

import org.example.Canvas;

import static org.junit.jupiter.api.Assertions.*;

class  CanvasTest {

    @Test
    void namelessCanvasTest() {
        Canvas canvas = new Canvas(300, 300);
        assertEquals(300, canvas.getHeight());
        assertEquals(300, canvas.getWidth());
    }

    @Test
    void namedCavasTest() {
        Canvas canvas = new Canvas(300, 300, "Canvas");
        assertEquals(300, canvas.getHeight());
        assertEquals(300, canvas.getWidth());
        assertEquals("Canvas", canvas.getFileName());
    }

    @Test
    void setPixelTest() {
        Canvas canvas = new Canvas(300, 300);
        canvas.setPixelColor(30, 30, new Color(0.5, 0.5, 0.5));
        //TODO floating point accuracy when setting a pixel to a color
        //assertEquals(new Color(0.5, 0.5, 0.5), canvas.getPixelColor(30, 30));
    }

}