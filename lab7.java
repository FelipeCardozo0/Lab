import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Lab7 {
    public static void main(String[] args) {
        // load image
        try {
            BufferedImage image = readImage("beach.jpeg"); // change this to a different image file
            int[][][] colorArrays = convertToColorArrays(image); // array of arrays of arrays!
            int[][] redArray = colorArrays[0];
            int[][] greenArray = colorArrays[1];
            int[][] blueArray = colorArrays[2];
            System.out.println("Image Dimensions: " + colorArrays[0].length + " x " + colorArrays[0][0].length);


            // add function calls to your methods here



            // save image
            BufferedImage outputImage = combineColorArrays(redArray, greenArray, blueArray);
            saveImage(outputImage, "output.png");
            System.out.println("Image processed and saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // read image from filepath and return as BufferedImage object
    public static BufferedImage readImage(String filePath) throws IOException {
        return ImageIO.read(new File(filePath));
    }

    // convert BufferedImage to 2D arrays for RGB components
    public static int[][][] convertToColorArrays(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Create 3 2D arrays for red, green, and blue components
        int[][] redArray = new int[height][width];
        int[][] greenArray = new int[height][width];
        int[][] blueArray = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                redArray[y][x] = color.getRed();
                greenArray[y][x] = color.getGreen();
                blueArray[y][x] = color.getBlue();
            }
        }

        return new int[][][]{redArray, greenArray, blueArray};
    }

    // combine red, green, and blue arrays into RGB Color format
    public static BufferedImage combineColorArrays(int[][] redArray, int[][] greenArray, int[][] blueArray) {
        int height = redArray.length;
        int width = redArray[0].length;
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int red = redArray[y][x];
                int green = greenArray[y][x];
                int blue = blueArray[y][x];
                Color color = new Color(red, green, blue);
                outputImage.setRGB(x, y, color.getRGB());
            }
        }
        return outputImage;
    }

    // save resulting image
    public static void saveImage(BufferedImage image, String outputPath) throws IOException {
        ImageIO.write(image, "png", new File(outputPath));
    }

    // add your image manipulation methods here


}
