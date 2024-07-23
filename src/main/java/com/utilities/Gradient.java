package com.utilities;

import java.awt.Color;

public class Gradient {
	// singleton instance
	private static Gradient instance = null;
    
    // get singleton class

	public static synchronized Gradient getInstance() {
        if (instance == null) instance = new Gradient();
		return instance;
    }

    public int value(Color startColor, Color endColor, int n, int maxN) {
        // Ensure n is within the range [0, maxN]
        n = Math.max(0, Math.min(n, maxN));
        
        // Calculate the ratio (fractional value) of n over maxN
        float ratio = (float) n / maxN;

        // Interpolate between the two colors
        int red = getColourSection(ratio, "red", 1.04, startColor, endColor);
        int green = getColourSection(ratio, "green", 1.03, startColor, endColor);
        int blue = getColourSection(ratio, "blue", 0.95, startColor, endColor);

        // Return the RGB integer value
        return new Color(red, green, blue).getRGB();
    }

    private int getColourSection(
        float ratio, String color, Double rate, Color startColor, Color endColor
        ) {
        int start = getColourComponent(startColor, color);
        int end = getColourComponent(endColor, color);
        int deviation = (int)(rate * Math.sin(rate * 15 * ratio) * 4);
        int value = (int) (start * (1 - ratio) + end * ratio * Math.pow(rate, 2)) + deviation;
        // return into in bounds
        return Math.max(0, Math.min(value, 255));
    }

    private int getColourComponent(Color color, String selection) {
        if (selection.equals("red")) {
            return color.getRed();
        }
        else if (selection.equals("green")) {
            return color.getGreen();
        }
        else { // blue
            return color.getBlue();
        }
    }
}
