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
            convertToGrayscale(redArray, greenArray, blueArray);
            invertColors(redArray, greenArray, blueArray);
            applyColorFilter(redArray, greenArray, blueArray, "red"); // Example: keeps only red channel






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
                applySepiaTone(redArray, greenArray, blueArray);
                adjustBrightness(redArray, greenArray, blueArray, 69);
                applyBlur(redArray, greenArray, blueArray);



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
    public static void convertToGrayscale(int[][] red, int[][] green, int[][] blue) {
        int height = red.length;
        int width = red[0].length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int grayscale = (red[y][x] + green[y][x] + blue[y][x]) / 3;
                red[y][x] = grayscale;
                green[y][x] = grayscale;
                blue[y][x] = grayscale;
            }
        }
    }
    public static void invertColors(int[][] red, int[][] green, int[][] blue) {
        int height = red.length;
        int width = red[0].length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                red[y][x] = 255 - red[y][x];
                green[y][x] = 255 - green[y][x];
                blue[y][x] = 255 - blue[y][x];
            }
        }
    }
    public static void applySepiaTone(int[][] red, int[][] green, int[][] blue) {
        int height = red.length;
        int width = red[0].length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int originalRed = red[y][x];
                int originalGreen = green[y][x];
                int originalBlue = blue[y][x];

                int sepiaRed = (int)(0.393 * originalRed + 0.769 * originalGreen + 0.189 * originalBlue);
                int sepiaGreen = (int)(0.349 * originalRed + 0.686 * originalGreen + 0.168 * originalBlue);
                int sepiaBlue = (int)(0.272 * originalRed + 0.534 * originalGreen + 0.131 * originalBlue);

                red[y][x] = Math.min(255, sepiaRed);
                green[y][x] = Math.min(255, sepiaGreen);
                blue[y][x] = Math.min(255, sepiaBlue);
            }
        }
    }
    public static void applyColorFilter(int[][] red, int[][] green, int[][] blue, String color) {
        int height = red.length;
        int width = red[0].length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch (color.toLowerCase()) {
                    case "red":
                        green[y][x] = 0;
                        blue[y][x] = 0;
                        break;
                    case "green":
                        red[y][x] = 0;
                        blue[y][x] = 0;
                        break;
                    case "blue":
                        red[y][x] = 0;
                        green[y][x] = 0;
                        break;
                    default:
                        System.out.println("Invalid color: " + color);
                        return;
                }
            }
        }
    }
    public static void adjustBrightness(int[][] red, int[][] green, int[][] blue, int adjustment) {
        int height = red.length;
        int width = red[0].length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Adjust and clamp each color value to be within the range [0, 255]
                red[y][x] = Math.min(255, Math.max(0, red[y][x] + adjustment));
                green[y][x] = Math.min(255, Math.max(0, green[y][x] + adjustment));
                blue[y][x] = Math.min(255, Math.max(0, blue[y][x] + adjustment));
            }
        }
    }

    public static void applyBlur(int[][] red, int[][] green, int[][] blue) {
        int height = red.length;
        int width = red[0].length;

        int[][] tempRed = new int[height][width];
        int[][] tempGreen = new int[height][width];
        int[][] tempBlue = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == 0 || y == height - 1 || x == 0 || x == width - 1) {
                    tempRed[y][x] = red[y][x];
                    tempGreen[y][x] = green[y][x];
                    tempBlue[y][x] = blue[y][x];
                } else {
                    int sumRed = 0;
                    int sumGreen = 0;
                    int sumBlue = 0;

                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            sumRed += red[y + i][x + j];
                            sumGreen += green[y + i][x + j];
                            sumBlue += blue[y + i][x + j];
                        }
                    }

                    tempRed[y][x] = sumRed / 9;
                    tempGreen[y][x] = sumGreen / 9;
                    tempBlue[y][x] = sumBlue / 9;
                }
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                red[y][x] = tempRed[y][x];
                green[y][x] = tempGreen[y][x];
                blue[y][x] = tempBlue[y][x];
            }
        }
    }







}
