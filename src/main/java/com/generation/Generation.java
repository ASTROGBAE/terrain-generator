package com.generation;

import com.utilities.NoiseGenerator;

abstract class Generation {
    protected int worldX;
    protected int worldY;
    protected int worldZ;
    protected NoiseGenerator noiseGenerator;

    public Generation(int worldX, int worldY, int worldZ) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.worldZ = worldZ;
        this.noiseGenerator = NoiseGenerator.getInstance();
    }
}
