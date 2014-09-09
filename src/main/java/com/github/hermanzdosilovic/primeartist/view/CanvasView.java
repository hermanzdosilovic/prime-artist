package com.github.hermanzdosilovic.primeartist.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import com.github.hermanzdosilovic.primeartist.controller.Artist;
import com.github.hermanzdosilovic.primeartist.model.Artifact;
import com.github.hermanzdosilovic.primeartist.model.Canvas;

public class CanvasView extends JComponent {

    private static final long serialVersionUID = 1L;

    private Canvas canvas;
    private Artifact artifact;

    public CanvasView(Canvas canvas, Artifact artifact) {
        setCanvas(canvas);
        setArtifact(artifact);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));
        setPreferredSize(new Dimension(canvas.getPreferredSize().width + getInsets().left + getInsets().right, 
                canvas.getPreferredSize().height + getInsets().top + getInsets().bottom));
        setOpaque(true);
        addComponentListener(new ComponentListener() {

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentResized(ComponentEvent e) {
                CanvasView canvasView = (CanvasView) e.getComponent();
                int width = canvasView.getWidthNoInsets() / artifact.getWidth() * artifact.getWidth();
                int height = canvasView.getHeightNoInsets() / artifact.getHeight() * artifact.getHeight();
                canvasView.setSizeNoInsets(width, height);
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
        setSizeNoInsets(getSizeNoInsets());
        Artist artistDrawer = new Artist(0, 0, getWidthNoInsets(), getHeightNoInsets(), this);
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

    public Dimension getSizeNoInsets() {
        return new Dimension(getWidthNoInsets(), getHeightNoInsets());
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        canvas.setSize(getWidthNoInsets(), getHeightNoInsets());
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

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        if (canvas == null) {
            throw new IllegalArgumentException("Canvas cannot be null");
        }
        this.canvas = canvas;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        if (artifact == null) {
            throw new IllegalArgumentException("Artifact cannot be null");
        }
        this.artifact = artifact;
    }

}
