package com.github.hermanzdosilovic.primeartist;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Main Class
 * @author Herman Zvonimir Došilović
 */
public final class PrimeArtist extends JFrame {

    private static final long serialVersionUID = 1L;

    /** Main JComponent of window. */
    final private Canvas canvas;
    
    private static PrimeArtist primeArtist;
    
    /**
     * Constructs new PrimeArtist window with given title.
     * @param title
     *            title of new window
     */
    private PrimeArtist(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new Canvas();
        
        addComponents(getContentPane());
        
        pack();
        setVisible(true);
    }

    private void addComponents(Container contentPane) {
        contentPane.setLayout(new BorderLayout());
        
        JToolBar toolBar = createToolBar();
        contentPane.add(toolBar, BorderLayout.NORTH);
        
        contentPane.add(canvas, BorderLayout.CENTER);
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        
        JPanel artifactPanel = createArtifactPanel();
        toolBar.add(artifactPanel);
        
        JPanel canvasPanel = createCanvasPanel();
        toolBar.add(canvasPanel);
        
        return toolBar;
    }
 
    private JPanel createCanvasPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        
        /* Adding width slider. */
        JPanel widthPanel = new JPanel(new BorderLayout());
        widthPanel.setBorder(new TitledBorder("Canvas Width"));
        final JSlider width = new JSlider(Canvas.getMinimumWidth(), Canvas.getMaximumWidth(), canvas.getCanvasWidth());
        width.setName("canvas width");
        width.setMinorTickSpacing(1);
        width.setSnapToTicks(true);
        final JLabel widthLabel = new JLabel(Integer.toString(width.getValue()));
        width.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int k = width.getValue();
                int p = width.getValue() > Integer.parseInt(widthLabel.getText()) ? 1 : -1;
                while(k % canvas.getArtifactWidth() != 0) {
                    k += p;
                 }
                width.setValue(k);
                widthLabel.setText(Integer.toString(width.getValue()));
            }
        });
        canvas.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                width.setValue(canvas.getCanvasWidth());
            }
        });
        width.addChangeListener(canvas);
        widthPanel.add(width, BorderLayout.WEST);
        widthPanel.add(widthLabel, BorderLayout.CENTER);
        panel.add(widthPanel);
        
        /* Adding height slider. */
        JPanel heightPanel = new JPanel(new BorderLayout());
        heightPanel.setBorder(new TitledBorder("Canvas Height"));
        final JSlider height = new JSlider(Canvas.getMinimumHeight(), Canvas.getMaximumHeight(), canvas.getCanvasHeight());
        height.setName("canvas height");
        height.setMinorTickSpacing(1);
        height.setSnapToTicks(true);
        final JLabel heightLabel = new JLabel(Integer.toString(height.getValue()));
        height.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               int k = height.getValue();
               int p = height.getValue() > Integer.parseInt(heightLabel.getText()) ? 1 : -1;
               while(k % canvas.getArtifactHeight() != 0) {
                   k += p;
                }
                height.setValue(k);
                heightLabel.setText(Integer.toString(height.getValue()));
            }
        });
        canvas.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                height.setValue(canvas.getCanvasHeight());
            }
        });
        height.addChangeListener(canvas);
        heightPanel.add(height, BorderLayout.WEST);
        heightPanel.add(heightLabel, BorderLayout.CENTER);
        panel.add(heightPanel);
        
        return panel;
    }

    private JPanel createArtifactPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        
        /* Adding width slider. */
        JPanel widthPanel = new JPanel(new BorderLayout());
        widthPanel.setBorder(new TitledBorder("Number Width"));
        final JSlider width = new JSlider(1, 30, canvas.getArtifactWidth());
        width.setName("artifact width");
        width.setMinorTickSpacing(1);
        width.setPaintTicks(true);
        width.setSnapToTicks(true);
        final JLabel widthLabel = new JLabel(Integer.toString(width.getValue()));
        width.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                widthLabel.setText(Integer.toString(width.getValue()));
            }
        });
        width.addChangeListener(canvas);
        widthPanel.add(width, BorderLayout.WEST);
        widthPanel.add(widthLabel, BorderLayout.CENTER);
        panel.add(widthPanel);
        
        /* Adding height slider. */
        JPanel heightPanel = new JPanel(new BorderLayout());
        heightPanel.setBorder(new TitledBorder("Number Height"));
        final JSlider height = new JSlider(1, 30, canvas.getArtifactHeight());
        height.setName("artifact height");
        height.setMinorTickSpacing(1);
        height.setPaintTicks(true);
        height.setSnapToTicks(true);
        final JLabel heightLabel = new JLabel(Integer.toString(height.getValue()));
        height.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                heightLabel.setText(Integer.toString(height.getValue()));
            }
        });
        height.addChangeListener(canvas);
        heightPanel.add(height, BorderLayout.WEST);
        heightPanel.add(heightLabel, BorderLayout.CENTER);
        panel.add(heightPanel);
        
        return panel;
    }
    
    public static PrimeArtist getPrimeArtist() {
        return primeArtist;
    }
    
    /**
     * Main method. Invokes and creates new Swing window.
     * @param args
     *            command line arguments. Not in use.
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                primeArtist = new PrimeArtist("Prime Artist");
            }

        });
    }

}
