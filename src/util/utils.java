package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class utils {

    public static void split(String u, int rows, int columns) throws IOException {
        URL url = new URL(u);
        InputStream is = url.openStream();
        BufferedImage image = ImageIO.read(is);

        // initializing array to hold subimages
        BufferedImage[] imgs = new BufferedImage[rows * columns];

        // Equally dividing original image into subimages
        int subimage_Width = image.getWidth() / columns;
        int subimage_Height = image.getHeight() / rows;

        int current_img = 0;

        // iterating over rows and columns for each sub-image
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                // Creating sub image
                imgs[current_img] = new BufferedImage(subimage_Width, subimage_Height, image.getType());
                Graphics2D img_creator = imgs[current_img].createGraphics();

                // coordinates of source image
                int src_first_x = subimage_Width * j;
                int src_first_y = subimage_Height * i;

                // coordinates of sub-image
                int dst_corner_x = subimage_Width * j + subimage_Width;
                int dst_corner_y = subimage_Height * i + subimage_Height;

                img_creator.drawImage(image, 0, 0, subimage_Width, subimage_Height, src_first_x, src_first_y, dst_corner_x, dst_corner_y, null);
                current_img++;
            }
        }

        //writing sub-images into image files
        for (int i = 0; i < 16; i++)
        {
            File outputFile = new File("img" + i + ".jpg");
            ImageIO.write(imgs[i], "jpg", outputFile);
        }

    }

    public static int[] generate(int N){
        ArrayList<Integer> list = new ArrayList<Integer>(N*N);
        int[] result = new int[N*N];
        for(int i = 0; i < N*N; i++) {
            list.add(i);
        }

        Random rand = new Random();
        int i = 0;
        while(list.size() > 0) {
            int index = rand.nextInt(list.size());
            result[i] = list.remove(index);
            i++;
        }

        return result;
    }

    public static boolean isDuplicate(int[] arr, int x){
        boolean flag = false;
        for(int e: arr){
            if(e == x){
                flag = true;
            }
        }
        return flag;
    }

    public static JLabel[][] getRandomPuzzle(int N) throws IOException{
        JLabel[][] puzzle = new JLabel[N][N];
        int[] rands = generate(N);
        int index = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if (i == N - 1 && j == N - 1) {
                    BufferedImage img = ImageIO.read(new File("src/media/puzzle1/" + N + "x" + N + "/black.png"));
                    ImageIcon icon = new ImageIcon(img);
                    JLabel b = new JLabel(icon);
                    puzzle[i][j] = b;
                } else {
                    BufferedImage img = ImageIO.read(new File("src/media/puzzle1/" + N + "x" + N + "/img" + rands[index] + ".jpg"));
                    ImageIcon icon = new ImageIcon(img);
                    JLabel b = new JLabel(icon);
                    puzzle[i][j] = b;
                }
                index++;
            }
        }
        return puzzle;
    }

}
