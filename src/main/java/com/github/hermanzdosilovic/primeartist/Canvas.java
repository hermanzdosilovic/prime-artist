package com.github.hermanzdosilovic.primeartist;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.ForkJoinPool;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Represents Canvas. On <code>Canvas Artist</code> draws <code>Artifact</code>s.
 * @author Herman Zvonimir Došilović
 */
public class Canvas extends JComponent implements ChangeListener {

    private static final long serialVersionUID = 1L;
    
    /** Represents width of artifact. */
    private int artifactWidth;

    /** Represents height of artifact. */
    private int artifactHeight;

    /** Represents color of canvas. */
    private Color canvasColor;

    /** Represents color of artifact. */
    private Color artifactColor;

    private BufferedImage bufferedImage;
    
    private static final int minimumWidth = 480;
    private static final int minimumHeight = 500;
    private static final int maximumWidth = 3840;
    private static final int maximumHeight = 2160;
    
    public Canvas() {
        this(500, 500, 1, 1, Color.BLACK, Color.YELLOW); 
    }
    
    public Canvas(int canvasWidth, int canvasHeight, int artifactWidth, int artifactHeight, Color canvasColor,
            Color artifactColor) {
        
        setArtifactWidth(artifactWidth);
        setArtifactHeight(artifactHeight);
        
        setCanvasColor(canvasColor);
        setArtifactColor(artifactColor);
        
        
        
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));
        
        Dimension preferredSize = calculateBestFitSize(canvasWidth, canvasHeight);
        setPreferredSize(preferredSize);
        setSize(getPreferredSize());
        
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        Dimension bestFit = calculateBestFitSize(getCanvasWidth(), getCanvasHeight());
        setSize(bestFit);
        
        System.out.println(getCanvasWidth() + " " + getCanvasHeight());
        
        Artist artistDrawer = new Artist(0, 0, getCanvasWidth(), getCanvasHeight(), this);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(artistDrawer);
        
        bufferedImage = artistDrawer.getImage();
        g.drawImage(bufferedImage, getInsets().left, getInsets().top, null);
    }

    public int getArtifactWidth() {
        return artifactWidth;
    }

    public void setArtifactWidth(int artifactWidth) {
        this.artifactWidth = artifactWidth;
    }

    public int getArtifactHeight() {
        return artifactHeight;
    }

    public void setArtifactHeight(int artifactHeight) {
        this.artifactHeight = artifactHeight;
    }

    public Color getCanvasColor() {
        return canvasColor;
    }

    public int getCanvasWidth() {
        return getWidth() - getInsets().left - getInsets().right;
    }
    
    public int getCanvasHeight() {
        return getHeight() - getInsets().top - getInsets().bottom;
    }
    
    public void setCanvasColor(Color canvasColor) {
        this.canvasColor = canvasColor;
    }

    public Color getArtifactColor() {
        return artifactColor;
    }

    public void setArtifactColor(Color artifactColor) {
        this.artifactColor = artifactColor;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
    
    public static int getMinimumWidth() {
        return minimumWidth;
    }

    public static int getMinimumHeight() {
        return minimumHeight;
    }

    public static int getMaximumWidth() {
        return maximumWidth;
    }

    public static int getMaximumHeight() {
        return maximumHeight;
    }

    private Dimension calculateBestFitSize(int currentWidth, int currentHeight) {
        int width = currentWidth / artifactWidth * artifactWidth;
        int height = currentHeight / artifactHeight * artifactHeight;
        return new Dimension(width + getInsets().left + getInsets().right, height + getInsets().top
                + getInsets().bottom);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSlider) {
            JSlider slider = (JSlider) e.getSource();            
            if (slider.getName().equals("artifact width")) {
                setArtifactWidth(slider.getValue());
            } else if (slider.getName().equals("artifact height")) {
                setArtifactHeight(slider.getValue());
            } else if (slider.getName().equals("canvas width")) {
                if (slider.getValueIsAdjusting()) {
                    return;
                }
                setSize(slider.getValue() + getInsets().left + getInsets().right, getHeight());
                setPreferredSize(getSize());
                PrimeArtist.getPrimeArtist().pack();            
            } else {
                if (slider.getValueIsAdjusting()) {
                    return;
                }
                setSize(getWidth(), slider.getValue() + getInsets().top + getInsets().bottom);
                setPreferredSize(getSize());
                PrimeArtist.getPrimeArtist().pack(); 
            }
        }
        repaint();
    }

}
