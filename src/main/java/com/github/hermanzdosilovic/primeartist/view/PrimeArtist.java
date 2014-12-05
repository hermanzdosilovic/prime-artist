package com.github.hermanzdosilovic.primeartist.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import com.github.hermanzdosilovic.primeartist.model.Artifact;
import com.github.hermanzdosilovic.primeartist.model.Canvas;

public final class PrimeArtist extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final int minimumWidth = 200;
    private static final int minimumHeight = 200;
    private static final Dimension minimumSize = new Dimension(minimumWidth, minimumHeight);
    
    final private CanvasView canvasView;
    private Canvas canvas;
    private Artifact artifact;
    
    public PrimeArtist(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(minimumSize);
        
        artifact = new Artifact();        
        canvas = new Canvas();
        canvasView = new CanvasView(canvas, artifact);
 
        artifact.addObserver(canvasView);
        canvas.addObserver(canvasView);
        canvasView.addComponentListener(canvas);
        
        addComponents(getContentPane());
        
        pack();
        setVisible(true);
    }

    private void addComponents(Container contentPane) {
        contentPane.add(canvasView, BorderLayout.CENTER);
            
        JPanel menuAndToolBar = createMenuAndToolBar();
        contentPane.add(menuAndToolBar, BorderLayout.NORTH);
    }

    private JPanel createMenuAndToolBar() {
        JPanel panel = new JPanel(new BorderLayout());
        JToolBar toolBar = createToolBar();
        JMenuBar menuBar = createMenuBar();
        panel.add(toolBar, BorderLayout.SOUTH);
        panel.add(menuBar, BorderLayout.NORTH);
        return panel;
    }
    
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.showSaveDialog(null);
                File outputFile = fc.getSelectedFile();
                try {
                    ImageIO.write(canvasView.getBufferedImage(), "png", outputFile);
                } catch (IOException ignorable) {
                }
            }
        });
        file.add(save);
        menuBar.add(file);
        return menuBar;
    }
    
    private JToolBar createToolBar() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JToolBar toolBar = new JToolBar();
        
        JSlider width = new JSlider(SwingConstants.HORIZONTAL, artifact.getMinWidth(), artifact.getMaxWidth(), artifact.getWidth());
        setTickSpacing(width);
        width.setBorder(new TitledBorder("Width of number"));
        width.addChangeListener(artifact.new WidthListener());
        panel.add(width);
        
        ColorView artifactColor = new ColorView(artifact.getColor());
        artifactColor.addPropertyChangeListener(artifact);
        artifactColor.setBorder(new TitledBorder("Number color"));
        panel.add(artifactColor);
        
        JSlider height = new JSlider(SwingConstants.HORIZONTAL, artifact.getMinHeight(), artifact.getMaxHeight(), artifact.getHeight());
        setTickSpacing(height);
        height.setBorder(new TitledBorder("Height of number"));
        height.addChangeListener(artifact.new HeightListener());
        panel.add(height);
        
        ColorView canvasColor = new ColorView(canvas.getColor());
        canvasColor.addPropertyChangeListener(canvas);
        canvasColor.setBorder(new TitledBorder("Background color"));
        panel.add(canvasColor);

        toolBar.add(panel);
        return toolBar;
    }
    
    private void setTickSpacing(JSlider jSlider) {
        jSlider.setMinorTickSpacing(1);
        jSlider.setMajorTickSpacing(5);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
        jSlider.setSnapToTicks(true);
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
