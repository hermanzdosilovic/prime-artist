package com.github.hermanzdosilovic.primeartist;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class ColorView extends JComponent {

    private static final long serialVersionUID = 1L;
    
    private Color color;
        
    private static final int width = 100;
    private static final int height = 50;
    
    public ColorView(Color color) {
        this.color = color;
        setPreferredSize(new Dimension(width, height));
    }
    
    public Color getColor() {
        return color;    
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        int x = getInsets().left;
        int y = getInsets().top;
        int width = getWidth() - getInsets().left - getInsets().right;
        int height = getHeight() - getInsets().top - getInsets().bottom;
        g.fillRect(x, y, width, height);
    }
    
    
}
