package com;

public class Terrain {
    private int x;
    private int y;
    private int z;
    NoiseGenerator noiseGenerator = new NoiseGenerator();

    public Terrain(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int[][] generate() {
        // mapped value of how varied the terrain is, with smaller values being more varied terrain
        int[][] erosion = noiseGenerator.noiseMap(x, y, z, 200);
        // generate elevation based on erosion, with greater size based on
        int[][] elevation = new int[x][y];
        for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
                Double erosionMultiplier = 0.5 + Math.max(0, Math.min((erosion[i][j] * 1.0 / z), 1));
                elevation[i][j] = noiseGenerator.noise(i, j, z, 150 * erosionMultiplier);
            }
        }
        return elevation;
    }
}