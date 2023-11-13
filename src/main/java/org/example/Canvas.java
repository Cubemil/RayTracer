package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Canvas {

    private int width;
    private int height;
    private String fileName;
    private BufferedImage outputImage;

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
        outputImage.setRGB(x, y, color.getIntRGB());
    }

    public Color getPixelColor(int x, int y) {
        return new Color(outputImage.getRGB(x, y));
    }

    public void writeFile() {
        try {
            File outputFile = new File(fileName + ".png");
            ImageIO.write(outputImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String newName) {
        try {
            File outputFile = new File(newName + ".png");
            ImageIO.write(outputImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(String fileName) {
        try {
            File inputFile = new File(fileName);
            outputImage = ImageIO.read(inputFile);
            this.width = outputImage.getWidth();
            this.height = outputImage.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
