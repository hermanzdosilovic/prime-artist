package com.github.hermanzdosilovic.primeartist.model;

import java.awt.Color;

public class Artifact {

    private final int minWidth = 1;
    private final int minHeight = 1;
    private int width;
    private int height;
    private final int maxWidth = 30;
    private final int maxHeight = 30;

    private Color color = Color.MAGENTA;

    public Artifact() {
        this(1, 1);
    }

    public Artifact(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width < minWidth || width > maxWidth) {
            throw new IllegalArgumentException("Width must be >= " + minWidth + " and <= " + maxWidth);
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < minHeight || height > maxHeight) {
            throw new IllegalArgumentException("Width must be >= " + minHeight + " and <= " + maxHeight);
        }
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

    public int getMinWidth() {
        return minWidth;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

}
