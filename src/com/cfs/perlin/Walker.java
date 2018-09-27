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
        tendToRight();
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
        int stepX = OverrideJPanel.x, stepY = OverrideJPanel.y;
        if(info.getLocation().x < x){
            stepX *= -1;
        }
        if(info.getLocation().y < y){
            stepY *= -1;
        }
        if(x < .4){
            OverrideJPanel.x += stepX;
        } else if(x < .6) {
            OverrideJPanel.x -= stepX;
        } else if(x < .8){
            OverrideJPanel.y += stepY;
        } else {
            OverrideJPanel.y -= stepY;
        }
    }
}
