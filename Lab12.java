import java.io.*;
import java.util.*;

public class Lab12 {

    public static void main(String[] args) {

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
        // Step 1: Read the replacement pairs into two arrays
        String[] originals = new String[7]; // Assume a maximum of 100 pairs
        String[] replacements = new String[7];
        int pairCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(replacementsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] pair = line.split(" ", 2);
                if (pair.length == 2) {
                    originals[pairCount] = pair[0];
                    replacements[pairCount] = pair[1];
                    pairCount++;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                StringBuilder modifiedLine = new StringBuilder();
                String[] words = line.split("\\b"); // Split by word boundaries
                for (String word : words) {
                    boolean replaced = false;
                    for (int i = 0; i < pairCount; i++) {
                        if (word.equals(originals[i])) {
                            modifiedLine.append(replacements[i]);
                            replaced = true;
                            break;
                        }
                    }
                    if (!replaced) {
                        modifiedLine.append(word);
                    }
                }
                bw.write(modifiedLine.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
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
}


  
