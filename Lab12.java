import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab12 {

    public static void main(String[] args) {
        String inputFile = "input.txt";
        String replacementsFile = "replacements.txt";
        String outputFile = "output.txt";

        wordReplacement(inputFile, replacementsFile, outputFile);
        System.out.println("Word replacement complete. Output saved to " + outputFile);

        wordLengthAnalysis(inputFile);
    }

    public static int wordCount(String filename) {
        int wordCount = 0;

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                scanner.next();
                wordCount++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + filename);
            return -1;
        } catch (Exception e) {
            System.err.println("Error: An unexpected error occurred.");
            e.printStackTrace();
            return -1;
        }

        return wordCount;
    }

    public static void wordReplacement(String inputFile, String replacementsFile, String outputFile) {

    }

    public static void wordLengthAnalysis(String filename) {
        String longestWord = "";
        String shortestWord = null;
        int totalLength = 0;
        int wordCount = 0;

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                int wordLength = word.length();
                totalLength += wordLength;
                wordCount++;

                if (wordLength > longestWord.length()) {
                    longestWord = word;
                }

                if (shortestWord == null || wordLength < shortestWord.length()) {
                    shortestWord = word;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + filename);
            return;
        } catch (Exception e) {
            System.err.println("Error: An unexpected error occurred while analyzing the file.");
            e.printStackTrace();
            return;
        }

        if (wordCount > 0) {
            double averageWordLength = (double) totalLength / wordCount;
            System.out.println("Longest word: " + longestWord);
            System.out.println("Shortest word: " + shortestWord);
            System.out.printf("Average word length: %.2f\n", averageWordLength);
        } else {
            System.out.println("The file is empty or contains no words.");
        }
    }
}
  
