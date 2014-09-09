package com.github.hermanzdosilovic.primeartist.model;

import java.awt.Color;
import java.awt.Dimension;

public class Canvas {

    private int width;
    private int height;
    private int preferredWidth;
    private int preferredHeight;
    private Dimension preferredSize;
    private Dimension size;

    private Color color = Color.BLACK;

    public Canvas() {
        this(637, 637);
    }

    public Canvas(int width, int height) {
        this(width, height, 637, 637);
    }

    public Canvas(int width, int height, int preferredWidth, int preferredHeight) {
        setWidth(width);
        setHeight(height);
        setPreferredWidth(preferredWidth);
        setPreferredHeight(preferredHeight);
        preferredSize = new Dimension(preferredWidth, preferredHeight);
        size = new Dimension(width, height);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color cannot be null");
        }
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width < 1) {
            throw new IllegalArgumentException("Width must be greater than 0");
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < 1) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        this.height = height;
    }

    public int getPreferredWidth() {
        return preferredWidth;
    }

    public void setPreferredWidth(int preferredWidth) {
        if (preferredWidth < 1) {
            throw new IllegalArgumentException("Preferred width must be greater than 0");
        }
        this.preferredWidth = preferredWidth;
    }

    public int getPreferredHeight() {
        return preferredHeight;
    }

    public void setPreferredHeight(int preferredHeight) {
        if (preferredHeight < 1) {
            throw new IllegalArgumentException("Preferred height must be greater than 0");
        }
        this.preferredHeight = preferredHeight;
    }

    public Dimension getPreferredSize() {
        return preferredSize;
    }

    public void setPreferredSize(Dimension preferredSize) {
        if (preferredSize == null) {
            throw new IllegalArgumentException("Preferred size cannot be null");
        }
        setPreferredWidth(preferredSize.width);
        setPreferredHeight(preferredSize.height);
        this.preferredSize = new Dimension(preferredSize);
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.setSize(size.width, size.height);
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
        this.size = new Dimension(width, height);
    }

}
