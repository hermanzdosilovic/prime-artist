package com.github.hermanzdosilovic.primeartist.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import com.github.hermanzdosilovic.primeartist.controller.Artist;
import com.github.hermanzdosilovic.primeartist.model.Artifact;

public class Canvas extends JComponent {

    private static final long serialVersionUID = 1L;

    private static final int preferedWidth = 637;
    private static final int preferedHeight = 637;
    private static final Dimension preferedSize = new Dimension(preferedWidth, preferedHeight);

    private Color color = Color.BLACK;

    private static Canvas canvas = new Canvas();

    private Canvas() {
        setPreferredSize(preferedSize);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));
        setOpaque(true);
        addComponentListener(new ComponentListener() {

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentResized(ComponentEvent e) {
                Canvas canvas = (Canvas) e.getComponent();
                Artifact artifact = Artifact.getArtifact();
                int width = canvas.getCanvasWidth() / artifact.getWidth() * artifact.getWidth();
                int height = canvas.getCanvasHeight() / artifact.getHeight() * artifact.getHeight();
                canvas.setCanvasSize(width, height);
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Artist artistDrawer = new Artist(0, 0, getCanvasWidth(), getCanvasHeight());
        BufferedImage image = artistDrawer.getImage();
        artistDrawer.start();
        try {
            while (artistDrawer.isAlive()) {
                artistDrawer.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.drawImage(image, getInsets().left, getInsets().top, null);
    }

    public static Canvas getCanvas() {
        return canvas;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCanvasWidth() {
        return getWidth() - getInsets().right - getInsets().left;
    }

    public void setCanvasWidth(int width) {
        setSize(width + getInsets().right + getInsets().left, getHeight());
    }

    public int getCanvasHeight() {
        return getHeight() - getInsets().top - getInsets().bottom;
    }

    public void setCanvasHeight(int height) {
        setSize(getWidth(), height + getInsets().top + getInsets().bottom);
    }

    public void setCanvasSize(int width, int height) {
        setSize(width + getInsets().left + getInsets().right, height + getInsets().top + getInsets().bottom);
    }

}
