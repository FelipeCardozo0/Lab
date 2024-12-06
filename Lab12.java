import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordCounter {

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
        Map<String, String> replacements = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(replacementsFile))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] pair = line.split(" ", 2);
                if (pair.length == 2) {
                    replacements.put(pair[0], pair[1]);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Replacements file not found - " + replacementsFile);
            return;
        } catch (Exception e) {
            System.err.println("Error: An unexpected error occurred while reading the replacements file.");
            e.printStackTrace();
            return;
        }

        try (Scanner scanner = new Scanner(new File(inputFile));
             PrintWriter writer = new PrintWriter(outputFile)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (Map.Entry<String, String> entry : replacements.entrySet()) {
                    line = line.replace(entry.getKey(), entry.getValue());
                }
                writer.println(line);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: Input file not found - " + inputFile);
        } catch (IOException e) {
            System.err.println("Error: Could not write to output file - " + outputFile);
        } catch (Exception e) {
            System.err.println("Error: An unexpected error occurred during processing.");
            e.printStackTrace();
        }
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

    public static void main(String[] args) {
        String inputFile = "input.txt";
        String replacementsFile = "replacements.txt";
        String outputFile = "output.txt";

        wordReplacement(inputFile, replacementsFile, outputFile);
        System.out.println("Word replacement complete. Output saved to " + outputFile);

        wordLengthAnalysis(inputFile);
    }
}
  
