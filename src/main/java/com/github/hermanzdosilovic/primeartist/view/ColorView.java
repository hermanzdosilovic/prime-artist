package com.github.hermanzdosilovic.primeartist.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JComponent;

public class ColorView extends JComponent {

    private static final long serialVersionUID = 1L;

    private Color color;

    public ColorView(Color color) {
        setColor(color);
        addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                Color newColor = JColorChooser.showDialog(null, "Change number color", color);
                if(newColor != null) {
                    setColor(newColor);
                }
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRect(getInsets().left, getInsets().top, getWidth() - getInsets().left - getInsets().right, getHeight() - getInsets().top - getInsets().bottom);
    }
    
    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color cannot be null");
        }
        this.color = color;
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                repaint();
            }
        }).start();
    }
    
    public Color getColor() {
        return color;
    }
}
