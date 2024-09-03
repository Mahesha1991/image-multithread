package com.mahesha;

import java.awt.image.BufferedImage;

public class Color {

    public int getRed(int rgb){
        return (rgb & 0x00FF0000) >> 16;
    }

    public int getGreen(int rgb){
        return (rgb & 0x0000FF00) >> 8;
    }

    public int getBlue(int rgb){
        return (rgb & 0x000000FF);
    }

    public int createRGB(int red, int green, int blue){
        int rgb = 0;

        rgb = rgb | blue;
        rgb = rgb | (green << 8);
        rgb = rgb | (red << 16);
        rgb = rgb | 0xFF000000;

        return rgb;
    }

    public void setRGB(BufferedImage image, int x, int y, int newRGB){
        image.getRaster().setDataElements(x, y, image.getColorModel().getDataElements(newRGB, null));
    }
}
