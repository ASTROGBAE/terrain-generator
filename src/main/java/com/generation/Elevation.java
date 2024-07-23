package com.generation;

public class Elevation extends Generation {

    public Elevation(int worldX, int worldY, int worldZ) {
        super(worldX, worldY, worldZ);
    }

    public int[][] generate() {
        // mapped value of how varied the terrain is, with smaller values being more varied terrain
        int[][] erosion = noiseGenerator.noiseMap(worldX, worldY, worldZ, 200);
        // generate elevation based on erosion, with greater size based on
        int[][] elevation = new int[worldX][worldY];
        for (int x = 0; x < worldX; x ++) {
			for (int y = 0; y < worldY; y ++) {
                Double erosionMultiplier = 0.5 + Math.max(0, Math.min((erosion[x][y] * 1.0 / worldZ), 1));
                elevation[x][y] = noiseGenerator.noise(x, y, worldZ, 150 * erosionMultiplier);
            }
        }
        return elevation;
    }
}