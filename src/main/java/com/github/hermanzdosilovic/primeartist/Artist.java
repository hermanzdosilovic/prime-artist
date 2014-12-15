package com.github.hermanzdosilovic.primeartist;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.RecursiveAction;

import org.apache.commons.math3.primes.Primes;

public class Artist extends RecursiveAction {

    private static final long serialVersionUID = 1L;

    private static int THRESHOLDWIDTH = 320;
    private static int THRESHOLDHEIGHT = 320;

    private BufferedImage image;

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    private Canvas canvasView;

    public Artist(int startX, int startY, int endX, int endY, Canvas canvasView) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.canvasView = canvasView;
        image = new BufferedImage(endX - startX, endY - startY, BufferedImage.TYPE_INT_ARGB);
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    protected void compute() {
        int componentsInWidth = (endX - startX) / canvasView.getArtifactWidth();
        int componentsInHeight = (endY - startY) / canvasView.getArtifactHeight();

        if (componentsInWidth >= THRESHOLDWIDTH / canvasView.getArtifactWidth()) {
            int leftHalf = componentsInWidth / 2;
            Artist leftCanvas = new Artist(startX, startY, startX + leftHalf * canvasView.getArtifactWidth(), endY, canvasView);
            Artist rightCanvas = new Artist(startX + leftHalf * canvasView.getArtifactWidth(), startY, endX, endY, canvasView);
            combine(leftCanvas, rightCanvas, 0, 0, leftCanvas.getImage().getWidth(), 0);
            return;
        }
        if (componentsInHeight >= THRESHOLDHEIGHT / canvasView.getArtifactHeight()) {
            int upperHalf = componentsInHeight / 2;
            Artist upperCanvas = new Artist(startX, startY, endX, startY + upperHalf * canvasView.getArtifactHeight(), canvasView);
            Artist lowerCanvas = new Artist(startX, startY + upperHalf * canvasView.getArtifactHeight(), endX, endY, canvasView);
            combine(upperCanvas, lowerCanvas, 0, 0, 0, upperCanvas.getImage().getHeight());
            return;
        }

        int width = canvasView.getCanvasWidth();

        Graphics2D g = image.createGraphics();
        g.setBackground(canvasView.getCanvasColor());
        g.clearRect(0, 0, image.getWidth(), image.getHeight());
        
        for (int y = startY, rY = 0; y < endY; y += canvasView.getArtifactHeight(), rY += canvasView.getArtifactHeight()) {
            for (int x = startX, rX = 0; x < endX; x += canvasView.getArtifactWidth(), rX += canvasView.getArtifactWidth()) {
                int number = (y * width) / (canvasView.getArtifactHeight() * canvasView.getArtifactHeight()) + x / canvasView.getArtifactWidth();
                if (Primes.isPrime(number)) {
                    g.setColor(canvasView.getArtifactColor());
                    g.fillRect(rX, rY, canvasView.getArtifactWidth(), canvasView.getArtifactHeight());
                    g.setColor(canvasView.getCanvasColor());
//                    g.drawString(Integer.toString(number), rX, rY + 29);
                }
            }
        }

    }

    private void combine(Artist firstCanvas, Artist secondCanvas, int firstX, int firstY, int secondX, int secondY) {
        invokeAll(firstCanvas, secondCanvas);

        Graphics2D imageGraphics = image.createGraphics();
        BufferedImage firstImage = firstCanvas.getImage();
        BufferedImage secondImage = secondCanvas.getImage();

        imageGraphics.drawImage(firstImage, firstX, firstY, null);
        imageGraphics.drawImage(secondImage, secondX, secondY, null);
    }

}
