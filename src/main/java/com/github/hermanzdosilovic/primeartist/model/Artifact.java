package com.github.hermanzdosilovic.primeartist.model;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.github.hermanzdosilovic.primeartist.view.ColorView;

public class Artifact extends Observable implements PropertyChangeListener {

    private final int minWidth = 1;
    private final int minHeight = 1;
    private int width;
    private int height;
    private final int maxWidth = 31;
    private final int maxHeight = 31;

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
        notifyObservers();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < minHeight || height > maxHeight) {
            throw new IllegalArgumentException("Width must be >= " + minHeight + " and <= " + maxHeight);
        }
        this.height = height;
        notifyObservers();
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if(color == null) {
            throw new IllegalArgumentException("Color cannot be null");
        }
        this.color = color;
        notifyObservers();
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
    
    public class WidthListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider jSlider = (JSlider) e.getSource();
            int value = jSlider.getValue();
            setChanged();
            setWidth(value);            
        }
    }
    
    public class HeightListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider jSlider = (JSlider) e.getSource();
            int value = jSlider.getValue();
            setChanged();
            setHeight(value);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ColorView colorView = (ColorView) evt.getSource();
        setChanged();
        setColor(colorView.getColor());
    }

}
