package com.github.hermanzdosilovic.primeartist.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ForkJoinPool;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import com.github.hermanzdosilovic.primeartist.controller.Artist;
import com.github.hermanzdosilovic.primeartist.model.Artifact;
import com.github.hermanzdosilovic.primeartist.model.Canvas;

public class CanvasView extends JComponent implements Observer {

    private static final long serialVersionUID = 1L;

    private int artifactWidth;
    private int artifactHeight;
    private Color artifactColor;
    private Color canvasColor;
    
    private boolean stateChanged;
    
    private BufferedImage bufferedImage;
    
    public CanvasView(Canvas canvas, Artifact artifact) {
        if(canvas == null) {
            throw new IllegalArgumentException("Canvas cannot be null");
        }
        if(artifact == null) {
            throw new IllegalArgumentException("Artifact cannot be null");
        }
        
        artifactWidth = artifact.getWidth();
        artifactHeight = artifact.getHeight();
        artifactColor = artifact.getColor();
        canvasColor = canvas.getColor();
        
        Dimension preferredSize = calculateBestFitSize(canvas.getWidth(), canvas.getHeight());
        setPreferredSize(preferredSize);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(!stateChanged) {
            Dimension bestFit = calculateBestFitSize(getWidthNoInsets(), getHeightNoInsets());
            setSize(bestFit);
        }
        stateChanged = false;
        
        Artist artistDrawer = new Artist(0, 0, getWidthNoInsets(), getHeightNoInsets(), this);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(artistDrawer);       
        bufferedImage = artistDrawer.getImage();
        g.drawImage(bufferedImage, getInsets().left, getInsets().top, null);
    }
    
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
    
    private Dimension calculateBestFitSize(int currentWidth, int currentHeight) {
        int width = currentWidth / artifactWidth* artifactWidth;
        int height = currentHeight / artifactHeight * artifactHeight;
        return new Dimension(width + getInsets().left + getInsets().right, height + getInsets().top + getInsets().bottom);
    }
    
    public Dimension getSizeNoInsets() {
        return new Dimension(getWidthNoInsets(), getHeightNoInsets());
    }

    public void setSizeNoInsets(int width, int height) {
        this.setSize(width + getInsets().left + getInsets().right, height + getInsets().top + getInsets().bottom);
    }

    public void setSizeNoInsets(Dimension d) {
        this.setSizeNoInsets(d.width, d.height);
    }

    public int getWidthNoInsets() {
        return getWidth() - getInsets().left - getInsets().right;
    }

    public int getHeightNoInsets() {
        return getHeight() - getInsets().top - getInsets().bottom;
    }

    
    public int getArtifactWidth() {
        return artifactWidth;
    }

    public int getArtifactHeight() {
        return artifactHeight;
    }

    public Color getArtifactColor() {
        return artifactColor;
    }

    public Color getCanvasColor() {
        return canvasColor;
    }

    @Override
    public void update(Observable o, Object arg) {
        stateChanged = true;
        if(o instanceof Artifact) {
            Artifact artifact = (Artifact) o;
            artifactWidth = artifact.getWidth();
            artifactHeight = artifact.getHeight();
            artifactColor = artifact.getColor();
        }
        if(o instanceof Canvas) {
            Canvas canvas = (Canvas) o;
            canvasColor = canvas.getColor();
        }
        repaint();
    }

}
