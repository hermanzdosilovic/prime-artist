package com.github.hermanzdosilovic.primeartist.model;

import java.awt.Color;

public class Artifact {

    private static final int minWidth = 1;
    private static final int minHeight = 1;

    private int width = 1;
    private int height = 1;

    private static final int maxWidth = 30;
    private static final int maxHeight = 30;

    private Color color = Color.MAGENTA;

    private static Artifact artifact = new Artifact();

    private Artifact() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static int getMinwidth() {
        return minWidth;
    }

    public static int getMinheight() {
        return minHeight;
    }

    public static int getMaxwidth() {
        return maxWidth;
    }

    public static int getMaxheight() {
        return maxHeight;
    }

    public static Artifact getArtifact() {
        return artifact;
    }

}
