package com.cfs.perlin;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private Timer timer = new Timer(100, e -> repaint());
    MainPanel(){
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //g.drawImage(RandomNoise.getNoiseImage(), 0, 0, this);
        g.drawImage(PerlinNoise.getNoiseImage(), 0, 0, this);
    }
}
