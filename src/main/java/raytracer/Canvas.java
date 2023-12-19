package raytracer;

import math.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Canvas {

    private final int width;
    private final int height;
    private String fileName;
    private final BufferedImage outputImage;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.fileName = "";
        this.outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public Canvas(int width, int height, String fileName) {
        this(width, height);
        this.fileName = fileName;
    }

    public void setPixelColor(int x, int y, Color color) {
        outputImage.setRGB(x, y, color.toIntRGB());
    }

    public Color getPixelColor(int x, int y) {
        return Color.fromIntRGB(outputImage.getRGB(x, y));
    }

    public void writeFile() {
        // adds current year, month, day, hour and minute to file name
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new java.util.Date());
        // No need to check if fileName is an empty String
        try {
            File outputFile = new File(fileName + timeStamp + ".png");
            ImageIO.write(outputImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String newName) {
        this.setFileName(newName);
        this.writeFile();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
