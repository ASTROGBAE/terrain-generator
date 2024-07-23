package com;

import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.generation.Ocean;
import com.generation.Elevation;
import com.utilities.Gradient;

public class App {
    public static void main(String[] args) {
        // Define the width and height of the image
        int worldX = 500;
        int worldY = 500;
        int worldZ = 200;
        Elevation terrainGenerator = new Elevation(worldX, worldY, worldZ);
        Ocean oceanGenerator = new Ocean(worldX, worldY, worldZ);
        Gradient gradient = Gradient.getInstance();

        // create terrain maps
        int[][] terrain = terrainGenerator.generate();
        int[][] ocean = oceanGenerator.generate(terrain);

        // create ocean map

        // Create a BufferedImage object
        BufferedImage image = new BufferedImage(worldX, worldY, BufferedImage.TYPE_INT_ARGB);

        // colours for world generation
        Color forestGreen = new Color(63, 84, 41);
        Color snowyWhite = new Color(222, 221, 220);
        Color turquiose = new Color(53, 156, 132);
        Color deepBlue = new Color(30, 53, 66);

        // Set pixel values in the BufferedImage
        for (int x = 0; x < worldX; x++) {
            for (int y = 0; y < worldY; y++) {
                // draw an ocean if it exists, or a gradiated terrain, otherwise
                if (ocean[x][y] != 0) {
                    image.setRGB(x, y, gradient.value(turquiose, deepBlue, ocean[x][y], worldZ));
                }
                else {
                    image.setRGB(x, y, gradient.value(forestGreen, snowyWhite, terrain[x][y], worldZ));
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
