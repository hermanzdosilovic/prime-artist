package com.github.hermanzdosilovic.primeartist.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.apache.commons.math3.primes.Primes;

import com.github.hermanzdosilovic.primeartist.model.Artifact;
import com.github.hermanzdosilovic.primeartist.view.Canvas;

public class Artist extends Thread {

    private static int THRESHOLDWIDTH = 240;
    private static int THRESHOLDHEIGHT = 320;

    private BufferedImage image;

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public Artist(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        image = new BufferedImage(endX - startX, endY - startY, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void run() {
        Artifact artifact = Artifact.getArtifact();
        int componentsInWidth = (endX - startX) / artifact.getWidth();
        int componentsInHeight = (endY - startY) / artifact.getHeight();

        if (componentsInWidth >= THRESHOLDWIDTH / artifact.getWidth()) {

            int leftHalf = componentsInWidth / 2;

            Artist leftCanvas = new Artist(startX, startY, startX + leftHalf * artifact.getWidth(), endY);

            Artist rightCanvas = new Artist(startX + leftHalf * artifact.getWidth(), startY, endX, endY);

            leftCanvas.start();
            rightCanvas.start();

            try {
                while (leftCanvas.isAlive()) {
                    rightCanvas.join();
                    leftCanvas.join();
                }
                while (rightCanvas.isAlive()) {
                    rightCanvas.join();
                    leftCanvas.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Graphics2D imageGraphics = image.createGraphics();

            BufferedImage leftImage = leftCanvas.getImage();
            BufferedImage rightImage = rightCanvas.getImage();

            imageGraphics.drawImage(leftImage, 0, 0, null);
            imageGraphics.drawImage(rightImage, leftImage.getWidth(), 0, null);

            return;
        }
        if (componentsInHeight >= THRESHOLDHEIGHT / artifact.getHeight()) {

            int upperHalf = componentsInHeight / 2;

            Artist upperCanvas = new Artist(startX, startY, endX, startY + upperHalf * artifact.getHeight());

            Artist lowerCanvas = new Artist(startX, startY + upperHalf * artifact.getHeight(), endX, endY);

            upperCanvas.start();
            lowerCanvas.start();

            try {
                while (upperCanvas.isAlive()) {
                    upperCanvas.join();
                    lowerCanvas.join();
                }
                while (lowerCanvas.isAlive()) {
                    upperCanvas.join();
                    lowerCanvas.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Graphics2D imageGraphics = image.createGraphics();

            BufferedImage upperImage = upperCanvas.getImage();
            BufferedImage lowerImage = lowerCanvas.getImage();

            imageGraphics.drawImage(upperImage, 0, 0, null);
            imageGraphics.drawImage(lowerImage, 0, upperImage.getHeight(), null);

            return;
        }

        int width = Canvas.getCanvas().getCanvasWidth();

        Graphics2D g = image.createGraphics();
        g.setBackground(Canvas.getCanvas().getColor());
        g.clearRect(0, 0, image.getWidth(), image.getHeight());

        for (int y = startY, rY = 0; y < endY; y += artifact.getHeight(), rY += artifact.getHeight()) {
            for (int x = startX, rX = 0; x < endX; x += artifact.getWidth(), rX += artifact.getWidth()) {
                int number = (y * width) / (artifact.getHeight() * artifact.getHeight()) + x / artifact.getWidth();
                if (Primes.isPrime(number)) {
                    g.setColor(artifact.getColor());
                    g.fillRect(rX, rY, artifact.getWidth(), artifact.getHeight());
                }
            }
        }
    }

    public BufferedImage getImage() {
        return image;
    }

}
