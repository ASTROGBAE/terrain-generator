package com;

import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class App 
{
    public static void main(String[] args) {
        // Define the width and height of the image
        int worldX = 200;
        int worldY = 200;
        int worldZ = 200;
        NoiseGenerator noiseGenerator = new NoiseGenerator();
        Gradient gradient = new Gradient();

        // create terrain map
        int[][] map = noiseGenerator.noiseMap(worldX, worldY, worldZ, worldZ);

        // Create a BufferedImage object
        BufferedImage image = new BufferedImage(worldX, worldY, BufferedImage.TYPE_INT_ARGB);

        // Set pixel values in the BufferedImage
        for (int x = 0; x < worldX; x++) {
            for (int y = 0; y < worldY; y++) {
                image.setRGB(x, y, gradient.value(map[x][y], worldZ));
            }
        }

        // Save the image to a file
        try {
            File outputFile = new File("output/terrain.png");
            ImageIO.write(image, "png", outputFile);
            System.out.println("Terrain generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
