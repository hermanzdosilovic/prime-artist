package com.github.hermanzdosilovic.primeartist;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import com.github.hermanzdosilovic.primeartist.view.ArtistCanvas;

public final class PrimeArtist extends JFrame {

    private static final long serialVersionUID = 1L;

    public PrimeArtist(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addComponents(getContentPane());
        pack();
        setVisible(true);
    }

    private void addComponents(Container contentPane) {
        ArtistCanvas artistCanvas = new ArtistCanvas();
        contentPane.add(artistCanvas, BorderLayout.CENTER);

        JMenuBar menuBar = createMenuBar();
        contentPane.add(menuBar, BorderLayout.NORTH);

        JToolBar toolBar = createToolBar();
        contentPane.add(toolBar, BorderLayout.WEST);

        JLabel label = createLabel();
        contentPane.add(label, BorderLayout.SOUTH);
    }

    private JLabel createLabel() {
        JLabel label = new JLabel("some text");
        return label;
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar("Toolbar");

        JButton button = new JButton("A");
        toolBar.add(button);

        return toolBar;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        menuBar.add(file);

        JMenu edit = new JMenu("Edit");
        menuBar.add(edit);

        JMenu about = new JMenu("About");
        menuBar.add(about);

        return menuBar;
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
