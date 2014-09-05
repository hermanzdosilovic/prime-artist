package com.github.hermanzdosilovic.primeartist;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import com.github.hermanzdosilovic.primeartist.model.Artifact;
import com.github.hermanzdosilovic.primeartist.view.Canvas;

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
        Canvas artistCanvas = Canvas.getCanvas();
        contentPane.add(artistCanvas, BorderLayout.CENTER);

        JMenuBar menuBar = createMenuBar();
        JToolBar toolBar = createToolBar();

        JPanel menuBarAndToolBar = new JPanel();
        menuBarAndToolBar.setLayout(new BorderLayout());
        menuBarAndToolBar.add(menuBar, BorderLayout.NORTH);
        menuBarAndToolBar.add(toolBar, BorderLayout.SOUTH);

        contentPane.add(menuBarAndToolBar, BorderLayout.NORTH);

        JLabel label = createLabel();
        contentPane.add(label, BorderLayout.SOUTH);
    }

    private JLabel createLabel() {
        JLabel label = new JLabel("Some text");
        return label;
    }

    private JToolBar createToolBar() {

        JPanel toolBarPanel = new JPanel();
        toolBarPanel.setLayout(new FlowLayout(0));

        JPanel artifactPanel = createArtifactPanel();
        toolBarPanel.add(artifactPanel);

        JToolBar toolBar = new JToolBar("Toolbar");
        toolBar.setFloatable(true);
        toolBar.add(toolBarPanel);

        return toolBar;
    }

    private JPanel createArtifactPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 4));

        final Artifact artifact = Artifact.getArtifact();

        panel.add(new JLabel("Width"));
        final JTextField widthText = new JTextField(Integer.toString(artifact.getWidth()));
        final JButton widthIncrement = new JButton("+");
        final JButton widthDecrement = new JButton("-");
        if (artifact.getWidth() == Artifact.getMinwidth()) {
            widthDecrement.setEnabled(false);
        } else if (artifact.getWidth() == Artifact.getMaxwidth()) {
            widthIncrement.setEnabled(false);
        }
        widthIncrement.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int width = artifact.getWidth() + 1;
                widthText.setText(Integer.toString(width));
                if (width == Artifact.getMinwidth()) {
                    widthDecrement.setEnabled(false);
                }
                if (width == Artifact.getMaxwidth()) {
                    widthIncrement.setEnabled(false);
                }
                if (width < Artifact.getMaxwidth()) {
                    widthIncrement.setEnabled(true);
                }
                if (width > Artifact.getMinwidth()) {
                    widthDecrement.setEnabled(true);
                }
                artifact.setWidth(width);
                Canvas.getCanvas().repaint();
            }
        });
        widthDecrement.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int width = artifact.getWidth() - 1;
                widthText.setText(Integer.toString(width));
                if (width == Artifact.getMinwidth()) {
                    widthDecrement.setEnabled(false);
                }
                if (width == Artifact.getMaxwidth()) {
                    widthIncrement.setEnabled(false);
                }
                if (width < Artifact.getMaxwidth()) {
                    widthIncrement.setEnabled(true);
                }
                if (width > Artifact.getMinwidth()) {
                    widthDecrement.setEnabled(true);
                }
                artifact.setWidth(width);
                Canvas.getCanvas().repaint();
            }
        });
        widthText.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = widthText.getText();
                Integer width;
                try {
                    width = Integer.parseInt(text);
                } catch (NumberFormatException ignorable) {
                    widthText.setText(Integer.toString(artifact.getWidth()));
                    return;
                }
                if(width < Artifact.getMinwidth() || width > Artifact.getMaxwidth()) {
                    widthText.setText(Integer.toString(artifact.getWidth()));
                    return;
                }
                if (width == Artifact.getMinwidth()) {
                    widthDecrement.setEnabled(false);
                }
                if (width == Artifact.getMaxwidth()) {
                    widthIncrement.setEnabled(false);
                }
                if (width < Artifact.getMaxwidth()) {
                    widthIncrement.setEnabled(true);
                }
                if (width > Artifact.getMinwidth()) {
                    widthDecrement.setEnabled(true);
                }
                artifact.setWidth(width);
                Canvas.getCanvas().repaint();
            }
        });
        widthIncrement.setFocusable(false);
        widthDecrement.setFocusable(false);
        panel.add(widthText);
        panel.add(widthIncrement);
        panel.add(widthDecrement);

        panel.add(new JLabel("Height"));
        final JTextField heightText = new JTextField(Integer.toString(artifact.getHeight()));
        final JButton heightIncrement = new JButton("+");
        final JButton heightDecrement = new JButton("-");
        if (artifact.getHeight() == Artifact.getMinheight()) {
            heightDecrement.setEnabled(false);
        } else if (artifact.getHeight() == Artifact.getMaxheight()) {
            heightIncrement.setEnabled(false);
        }
        heightIncrement.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int height = artifact.getHeight() + 1;
                heightText.setText(Integer.toString(height));
                if (height == Artifact.getMinheight()) {
                    heightDecrement.setEnabled(false);
                }
                if (height == Artifact.getMaxheight()) {
                    heightIncrement.setEnabled(false);
                }
                if (height < Artifact.getMaxheight()) {
                    heightIncrement.setEnabled(true);
                }
                if (height > Artifact.getMinheight()) {
                    heightDecrement.setEnabled(true);
                }
                artifact.setHeight(height);
                Canvas.getCanvas().repaint();
            }
        });
        heightDecrement.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int height = artifact.getHeight() - 1;
                heightText.setText(Integer.toString(height));
                if (height == Artifact.getMinheight()) {
                    heightDecrement.setEnabled(false);
                }
                if (height == Artifact.getMaxheight()) {
                    heightIncrement.setEnabled(false);
                }
                if (height < Artifact.getMaxheight()) {
                    heightIncrement.setEnabled(true);
                }
                if (height > Artifact.getMinheight()) {
                    heightDecrement.setEnabled(true);
                }
                artifact.setHeight(height);
                Canvas.getCanvas().repaint();
            }
        });
        heightText.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = heightText.getText();
                Integer height;
                try {
                    height = Integer.parseInt(text);
                } catch (NumberFormatException ignorable) {
                    heightText.setText(Integer.toString(artifact.getHeight()));
                    return;
                }
                if(height < Artifact.getMinheight() || height > Artifact.getMaxheight()) {
                    heightText.setText(Integer.toString(artifact.getHeight()));
                    return;
                }
                if (height == Artifact.getMinheight()) {
                    heightDecrement.setEnabled(false);
                }
                if (height == Artifact.getMaxheight()) {
                    heightIncrement.setEnabled(false);
                }
                if (height < Artifact.getMaxheight()) {
                    heightIncrement.setEnabled(true);
                }
                if (height > Artifact.getMinheight()) {
                    heightDecrement.setEnabled(true);
                }
                artifact.setHeight(height);
                Canvas.getCanvas().repaint();
            }
        });
        heightIncrement.setFocusable(false);
        heightDecrement.setFocusable(false);
        panel.add(heightText);
        panel.add(heightIncrement);
        panel.add(heightDecrement);

        return panel;
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
