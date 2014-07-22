package com.github.hermanzdosilovic.primeartist.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import org.apache.commons.math3.primes.Primes;

public class ArtistCanvas extends JComponent {

    private static final long serialVersionUID = 1L;

    private static final int preferedWidth = 500;
    private static final int preferedHeight = 500;
    private static final Dimension preferedSize = new Dimension(preferedWidth, preferedHeight);

    public ArtistCanvas() {
        setPreferredSize(preferedSize);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int number = 0;
        Insets insets = getInsets();
        int width = getSize().width - insets.right;
        int height = getSize().height - insets.bottom;

        g.setColor(Color.WHITE);
        g.fillRect(insets.top, insets.left, width, height);
        g.setColor(Color.BLACK);
        for (int y = insets.top; y < height; y++) {
            for (int x = insets.left; x < width; x++) {
                if (Primes.isPrime(number)) {
                    g.drawLine(x, y, x, y);
                }
                number++;
            }
        }

    }

}
