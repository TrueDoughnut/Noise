package com.cfs.perlin;

import java.awt.image.BufferedImage;
import java.util.Random;

class RandomNoise {

    private static BufferedImage image;
    private static Random random;

    static {
        image = new BufferedImage(MainWindow.WIDTH, MainWindow.HEIGHT, BufferedImage.TYPE_INT_RGB);
        random = new Random();
    }

    static BufferedImage getNoiseImage(){
        for(int y = 0; y < MainWindow.HEIGHT; y++){
            for(int x = 0; x < MainWindow.WIDTH; x++){
                image.setRGB(x, y, random.nextInt(0xFFFFFF));
            }
        }
        return image;
    }
}
