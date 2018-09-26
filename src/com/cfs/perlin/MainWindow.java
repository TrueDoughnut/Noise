package com.cfs.perlin;

import javax.swing.*;

class MainWindow extends JFrame {
    static final int WIDTH = 640;
    static final int HEIGHT = 480;
    private MainPanel panel = new MainPanel();
    MainWindow(){
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }
}
