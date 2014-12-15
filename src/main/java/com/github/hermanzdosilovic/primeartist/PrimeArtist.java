package com.github.hermanzdosilovic.primeartist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        setMinimumSize(getSize());
        setVisible(true);
        setBackground(Color.WHITE);
    }

    /**
     * Adds component to given Container.
     * @param contentPane
     *            of frame
     */
    private void addComponents(Container contentPane) {
        contentPane.setLayout(new BorderLayout());

        JToolBar toolBar = createToolBar();
        toolBar.setFloatable(true);
        contentPane.add(toolBar, BorderLayout.NORTH);

        contentPane.add(canvas, BorderLayout.CENTER);

        JPanel aboutPanel = createAboutLabel();
        contentPane.add(aboutPanel, BorderLayout.SOUTH);
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();

        JPanel artifactPanel = createArtifactPanel();
        toolBar.add(artifactPanel);

        JPanel colorPanel = createColorPanel();
        toolBar.add(colorPanel);

        JPanel exportAndStart = new JPanel(new GridLayout(2, 1));
        JPanel panel = new JPanel();
        final JFormattedTextField numberField = new JFormattedTextField(NumberFormat.getInstance());
        numberField.setValue(canvas.getStartNumber());
        numberField.setColumns(9);
        numberField.addPropertyChangeListener("value", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int number;
                try {
                    number = Integer.parseInt(numberField.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Number too big.", "Error", JOptionPane.ERROR_MESSAGE);
                    numberField.setText(Integer.toString(canvas.getStartNumber()));
                    return;
                }
                canvas.setStartNumber(number);
                canvas.repaint();
            }
        });
        panel.setBorder(new TitledBorder("Start number"));
        panel.add(numberField);

        JButton exportButton = new JButton("Export as PNG");
        exportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fc = new JFileChooser();
                if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    String path = selectedFile.toString();
                    if (!path.endsWith(".png")) {
                        path += ".png";
                    }
                    File file = new File(path);
                    try {
                        ImageIO.write(canvas.getBufferedImage(), "png", file);
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null,
                                "Error occured while exporting image", "IO error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "Image successfully exported.");
                }

            }
        });

        exportAndStart.add(panel);
        exportAndStart.add(exportButton);

        toolBar.add(exportAndStart);
        return toolBar;
    }

    private JPanel createColorPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));

        final ColorView artifactView = new ColorView(canvas.getArtifactColor());
        artifactView.setBorder(new TitledBorder("Number Color"));
        artifactView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Color color = JColorChooser.showDialog(null, "Choose color", artifactView.getColor());
                if (color != null) {
                    artifactView.setColor(color);
                    artifactView.repaint();
                    canvas.setArtifactColor(color);
                    canvas.repaint();
                }
            }
        });
        panel.add(artifactView);

        final ColorView backgroundView = new ColorView(canvas.getCanvasColor());
        backgroundView.setBorder(new TitledBorder("Background Color"));
        backgroundView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Color color = JColorChooser.showDialog(null, "Choose color", backgroundView.getColor());
                if (color != null) {
                    backgroundView.setColor(color);
                    backgroundView.repaint();
                    canvas.setCanvasColor(color);
                    canvas.repaint();
                }
            }
        });
        panel.add(backgroundView);

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
        widthLabel.setPreferredSize(new Dimension(20, 10));
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
        heightLabel.setPreferredSize(new Dimension(20, 10));
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

    private JPanel createAboutLabel() {
        JPanel panel = new JPanel();

        final JLabel sizeLabel = new JLabel(canvas.getCanvasWidth() + " x " + canvas.getCanvasHeight());
        canvas.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = canvas.getCanvasWidth();
                int height = canvas.getCanvasHeight();
                sizeLabel.setText(width + " x " + height);
            }
        });

        panel.add(sizeLabel);

        return panel;
    }

    /**
     * Returns PrimeArtist object
     * @return PrimeArtist object
     */
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
