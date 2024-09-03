package com.mahesha;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final String source = "src/main/resources/keyboard.jpg";
    public static final String destination = "src/main/resources/edited_keyboard.jpg";
    public static void main(String[] args) throws IOException {

        Color color = new Color();
        int numOfThreads = 3;

        BufferedImage sourceImage = ImageIO.read(new File(source));
        BufferedImage outImage = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        long startTime = System.currentTimeMillis();
        List<Thread> colorThreads = getThreads(sourceImage, numOfThreads, outImage);

        for (Thread t : colorThreads){
            t.start();
        }
        for (Thread t : colorThreads){
            try{
                t.join();
            }catch (InterruptedException e){

            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time Take for " + numOfThreads + " Thread(s) : " + (endTime - startTime));
//        int rgb = sourceImage.getRGB(100,100);
//        System.out.println(color.getRed(rgb));
//        System.out.println(color.getGreen(rgb));
//        System.out.println(color.getBlue(rgb));
//
//        int red = color.getRed(rgb);
//        int green = color.getGreen(rgb);
//        int blue = color.getBlue(rgb);

//        for (int x = 0; x < sourceImage.getWidth(); x++){
//           for (int y = 0; y < sourceImage.getHeight(); y++){
//               int rgb = sourceImage.getRGB(x,y);
////               System.out.println(color.getRed(rgb));
////               System.out.println(color.getGreen(rgb));
////               System.out.println(color.getBlue(rgb));
//               int red = color.getRed(rgb);
//               int green = color.getGreen(rgb);
//               int blue = color.getBlue(rgb);
////               if (blue != 200){
////                   System.out.println(color.getRed(rgb));
////                   System.out.println(color.getGreen(rgb));
////                   System.out.println(color.getBlue(rgb));
////               }
//
//               if (red > 200){
//                   red = 0;
//               }
//               int finalRGB = color.createRGB(red, green, blue);
//               color.setRGB(outImage, x, y, finalRGB);
//
//           }
//        }

        File outputFile = new File(destination);
        ImageIO.write(outImage, "jpg", outputFile);


    }

    private static List<Thread> getThreads(BufferedImage sourceImage, int numOfThreads, BufferedImage outImage) {
        List<Thread> colorThreads = new ArrayList<>();
        int splitHeight = sourceImage.getHeight() / numOfThreads;
        for (int i = 0; i < numOfThreads; i++){
            Thread t = new Thread(new MultiExecutor(sourceImage, outImage, i * splitHeight, (i+1) * splitHeight));
            colorThreads.add(t);
        }
        if (sourceImage.getHeight() % numOfThreads != 0){
            Thread t = new Thread(new MultiExecutor(sourceImage, outImage, numOfThreads * splitHeight, (numOfThreads * splitHeight) + (sourceImage.getHeight() % numOfThreads)));
            colorThreads.add(t);
        }
        return colorThreads;
    }
}