package com.cfs.perlin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Walker extends JFrame implements ActionListener {

    private int x, y;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private final Timer timer = new Timer(80, this);
    private OverrideJPanel jPanel;

    Walker(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        x = WIDTH / 2;
        y = HEIGHT / 2;
        jPanel = new OverrideJPanel(x, y);
        this.add(jPanel);
        jPanel.getGraphics().setColor(Color.GREEN);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.revalidate();
        this.repaint();
    }
}

class OverrideJPanel extends JPanel {

    private static int x, y;
    private static final int stepPixels = 10;

    OverrideJPanel(int x, int y){
        OverrideJPanel.x = x;
        OverrideJPanel.y = y;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawOval(x, y, 5, 5);
        moveMonteCarlo();
    }

    private void randomStepFourDirections(){
        Random random = new Random();
        int choice = random.nextInt(4);
        switch(choice){
            case 0:
                x += 10;
                break;
            case 1:
                x -= 10;
                break;
            case 2:
                y += 10;
                break;
            case 3:
                y -= 10;
                break;
            default:
                break;
        }
    }

    private void randomStepNineDirections(){
        Random random = new Random();
        int stepx = random.nextInt(3) - 1;
        int stepy = random.nextInt(3) - 1;

        OverrideJPanel.x += stepx * stepPixels;
        OverrideJPanel.y += stepy * stepPixels;
    }

    private void tendToRight(){
        Random random = new Random();
        float x = random.nextFloat();
        if(x < .4){
            OverrideJPanel.x += stepPixels;
        } else if(x < .6) {
            OverrideJPanel.x -= stepPixels;
        } else if(x < .8){
            OverrideJPanel.y += stepPixels;
        } else {
            OverrideJPanel.y -= stepPixels;
        }
    }

    private void tendTowardsMouse(){
        Random random = new Random();
        float x = random.nextFloat();

        PointerInfo info = MouseInfo.getPointerInfo();
        Point point = info.getLocation();

        if (x < .5) {
            if (point.x > OverrideJPanel.x) {
                OverrideJPanel.x += OverrideJPanel.stepPixels;
            } else {
                OverrideJPanel.x -= OverrideJPanel.stepPixels;
            }
            if (point.y > OverrideJPanel.y) {
                OverrideJPanel.y += OverrideJPanel.stepPixels;
            } else {
                OverrideJPanel.y -= OverrideJPanel.stepPixels;
            }
        } else {
            int moveX =  random.nextInt(3) - 1,
                    moveY = random.nextInt(3) - 1;
            OverrideJPanel.x += moveX * OverrideJPanel.stepPixels;
            OverrideJPanel.y += moveY * OverrideJPanel.stepPixels;
        }
    }

    private void moveMonteCarlo(){
        OverrideJPanel.x += map(monteCarlo(), -1.0f, 1.0f, -25.0f, 25.0f);
        OverrideJPanel.y += map(monteCarlo(), -1.0f, 1.0f, -25.0f, 25.0f);
    }

    private int map(float x, float min1, float max1, float min2, float max2){
        return Math.round(min2 + (max2 - min2) * ((x - min1) / (max1 - min1)));
    }

    private float monteCarlo(){
        Random random = new Random();
        while(true){
            float x = random.nextFloat();
            float y = random.nextFloat();

            if(y < x){
                return x;
            }
        }
    }

}