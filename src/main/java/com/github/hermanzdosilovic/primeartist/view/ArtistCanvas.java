package com.github.hermanzdosilovic.primeartist.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import org.apache.commons.math3.primes.Primes;

public class ArtistCanvas extends JComponent {

    private static final long serialVersionUID = 1L;

    private static final int preferedWidth = 500;
    private static final int preferedHeight = 500;
    private static final Dimension preferedSize = new Dimension(preferedWidth, preferedHeight);

    private static int widthThickness = 20;
    private static int heightThickness = 20;

    public ArtistCanvas() {
        setPreferredSize(preferedSize);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));
        setOpaque(true);
        setBackground(Color.YELLOW);
        addComponentListener(new ComponentListener() {

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentResized(ComponentEvent e) {
                JComponent component = (JComponent) e.getComponent();
                Insets insets = component.getInsets();
                int width = (component.getWidth() - insets.left - insets.right) / ArtistCanvas.getWidthThickness()
                        * ArtistCanvas.getWidthThickness();
                component.setSize(width + insets.left + insets.right, component.getHeight());
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
        Insets insets = getInsets();
        int width = getSize().width - insets.right - insets.left;
        int height = getSize().height - insets.bottom - insets.top;

        g.setColor(Color.BLACK);
        g.fillRect(insets.top, insets.left, width, height);
        g.setColor(Color.RED);

        for (int y = 0; y < height; y += heightThickness) {
            for (int x = 0; x < width; x += widthThickness) {
                int number = (y * width) / (heightThickness * heightThickness) + x / widthThickness;
                if (Primes.isPrime(number)) {
                    g.fillRect(x + insets.left, y + insets.top, widthThickness, heightThickness);
                }
            }
        }

    }

    public static int getWidthThickness() {
        return widthThickness;
    }

    public static void setWidthThickness(int widthThickness) {
        ArtistCanvas.widthThickness = widthThickness;
    }

    public static int getHeightThickness() {
        return heightThickness;
    }

    public static void setHeightThickness(int heightThickness) {
        ArtistCanvas.heightThickness = heightThickness;
    }

}
