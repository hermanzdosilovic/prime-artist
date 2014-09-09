package com.github.hermanzdosilovic.primeartist;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.github.hermanzdosilovic.primeartist.model.Artifact;
import com.github.hermanzdosilovic.primeartist.model.Canvas;
import com.github.hermanzdosilovic.primeartist.view.CanvasView;

public final class PrimeArtist extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final int minimumWidth = 200;
    private static final int minimumHeight = 200;
    private static final Dimension minimumSize = new Dimension(minimumWidth, minimumHeight);

    public PrimeArtist(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(minimumSize);
        addComponents(getContentPane());
        pack();
        setVisible(true);
    }

    private void addComponents(Container contentPane) {
        CanvasView canvasView = new CanvasView(new Canvas(), new Artifact(1, 1));
        contentPane.add(canvasView, BorderLayout.CENTER);
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new PrimeArtist("Prime Artist");
            }

        });
    }

}
