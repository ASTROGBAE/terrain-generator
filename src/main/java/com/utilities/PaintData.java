package com.utilities;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class PaintData {
    // singleton instance
	private static PaintData instance = null;

    Gradient gradient = Gradient.getInstance();

    // colours for world generation
    // terrain start to end
    Color forestGreen = new Color(63, 84, 41);
    Color snowyWhite = new Color(222, 221, 220);
    // ocean start to end
    Color turquiose = new Color(53, 156, 132);
    Color deepBlue = new Color(30, 53, 66);

    // get singleton class
	public static synchronized PaintData getInstance() {
        if (instance == null) instance = new PaintData();
		return instance;
    }

    public BufferedImage paint(int[][] terrain, int[][] ocean, int worldHeight) {
        // check data is a valid 2D multiarray
        if (terrain.length != 0 && terrain[0].length != 0) {
            // Create a BufferedImage object
            int worldX = terrain.length;
            int worldY = terrain[0].length;
            BufferedImage image = new BufferedImage(worldX, worldY, BufferedImage.TYPE_INT_ARGB);
            try {
                // Set pixel values in the BufferedImage
                for (int x = 0; x < worldX; x++) {
                    for (int y = 0; y < worldY; y++) {
                        // draw an ocean if it exists, or a gradiated terrain, otherwise
                        if (ocean[x][y] != 0) {
                            image.setRGB(x, y, gradient.value(turquiose, deepBlue, ocean[x][y], worldHeight));
                        }
                        else {
                            image.setRGB(x, y, gradient.value(forestGreen, snowyWhite, terrain[x][y], worldHeight));
                        }
                    }
                }
                return image; // success
            } catch (Exception e) {
                System.out.println("input data to paint into a buffered image has invalid format: " + e);
                return null;
            }
        }
        return null; // invalid response
    }
}
