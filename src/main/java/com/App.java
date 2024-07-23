package com;

import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.generation.Ocean;
import com.generation.Elevation;
import com.utilities.PaintData;

public class App {
    public static void main(String[] args) {
        // Define the width and height of the image
        int worldX = 500;
        int worldY = 500;
        int worldZ = 200;
        Elevation terrainGenerator = new Elevation(worldX, worldY, worldZ);
        Ocean oceanGenerator = new Ocean(worldX, worldY, worldZ);
        PaintData painter = PaintData.getInstance();

        // create terrain maps
        int[][] terrain = terrainGenerator.generate();
        int[][] ocean = oceanGenerator.generate(terrain);

        // create ocean map

        // Generate and color a BufferedImage object
        BufferedImage image = painter.paint(terrain, ocean, worldZ);

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
