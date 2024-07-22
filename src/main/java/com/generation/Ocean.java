package com.generation;

public class Ocean {
    private int worldX;
    private int worldY;
    private int seaLevel;

    public Ocean(int worldX, int worldY, int worldZ) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.seaLevel = worldZ / 3;
    }

    public int[][] generate(int[][] terrain) {
        int[][] ocean = new int[worldX][worldY];
        for (int x = 0; x < worldX; x ++) {
			for (int y = 0; y < worldY; y ++) {
                // add ocean (1) if terrain is below the sea level, 0, otherwise
                if (terrain[x][y] <= seaLevel) {
                    ocean[x][y] = 1;
                }
                else {
                    ocean[x][y] = 0;
                }
            }
        }
        return ocean;
    }
}
