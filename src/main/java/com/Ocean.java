package com;

public class Ocean {
    private int x;
    private int y;
    private int seaLevel;

    public Ocean(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.seaLevel = z / 3;
    }

    public int[][] generate(int[][] terrain) {
        int[][] ocean = new int[x][y];
        for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
                // add ocean (1) if terrain is below the sea level, 0, otherwise
                if (terrain[i][j] <= seaLevel) {
                    ocean[i][j] = 1;
                }
                else {
                    ocean[i][j] = 0;
                }
            }
        }
        return ocean;
    }
}
