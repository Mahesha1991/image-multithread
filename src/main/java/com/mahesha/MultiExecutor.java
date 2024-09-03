package com.mahesha;

import java.awt.image.BufferedImage;

public class MultiExecutor implements Runnable{

    private BufferedImage sourceImage;
    private Color color;
    private BufferedImage outImage;
    private int initialHeight;
    private int endHeight;
    public MultiExecutor(BufferedImage sourceImage, BufferedImage outImage, int initialHeight, int endHeight) {
        this.sourceImage = sourceImage;
        this.outImage = outImage;
        this.initialHeight = initialHeight;
        this.endHeight = endHeight;
        this.color = new Color();
    }

    @Override
    public void run() {
        for (int x = 0; x < sourceImage.getWidth(); x++){
            for (int y = initialHeight; y < endHeight; y++){
                int rgb = sourceImage.getRGB(x,y);
//               System.out.println(color.getRed(rgb));
//               System.out.println(color.getGreen(rgb));
//               System.out.println(color.getBlue(rgb));
                int red = color.getRed(rgb);
                int green = color.getGreen(rgb);
                int blue = color.getBlue(rgb);
//               if (blue != 200){
//                   System.out.println(color.getRed(rgb));
//                   System.out.println(color.getGreen(rgb));
//                   System.out.println(color.getBlue(rgb));
//               }

                if (red > 200){
                    red = 0;
                    green = 0;
                }
                int finalRGB = color.createRGB(red, green, blue);
                color.setRGB(outImage, x, y, finalRGB);

            }
        }
    }

    public void executeImage(int initialHeight, int endHeight){
        this.initialHeight = initialHeight;
        this.endHeight = endHeight;
        run();
    }
}
