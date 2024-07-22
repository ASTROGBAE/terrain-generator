package com;

import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.generation.Ocean;
import com.generation.Terrain;
import com.utilities.Gradient;

public class App 
{
    public static void main(String[] args) {
        // Define the width and height of the image
        int worldX = 500;
        int worldY = 500;
        int worldZ = 200;
        Terrain terrainGenerator = new Terrain(worldX, worldY, worldZ);
        Ocean oceanGenerator = new Ocean(worldX, worldY, worldZ);
        Gradient gradient = new Gradient();

        // create terrain maps
        int[][] terrain = terrainGenerator.generate();
        int[][] ocean = oceanGenerator.generate(terrain);

        // create ocean map

        // Create a BufferedImage object
        BufferedImage image = new BufferedImage(worldX, worldY, BufferedImage.TYPE_INT_ARGB);

        // Set pixel values in the BufferedImage
        for (int x = 0; x < worldX; x++) {
            for (int y = 0; y < worldY; y++) {
                // draw an ocean if it exists, or a gradiated terrain, otherwise
                if (ocean[x][y] == 1) {
                    image.setRGB(x, y, new Color(44, 85, 92).getRGB());
                }
                else {
                    image.setRGB(x, y, gradient.value(terrain[x][y], worldZ));
                }
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
