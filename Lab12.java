import java.io.*;
import java.util.*;

public class Lab12 {

    public static void main(String[] args) {
        wordReplacement("american-british.txt", "museum2.txt","out.txt");
    }

    public static int wordCount(String filename) {
        int wordCount = 0;

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                scanner.next();
                wordCount++;
            }
        } 
        catch (FileNotFoundException e){
            System.out.println("Error," + filename+ " not found");
            return -1;
        } 
        catch(Exception e){
            System.out.println("Error");
            e.printStackTrace();
            return -1;
        }return wordCount;
    }

    public static void wordReplacement(String inputFile, String replacementsFile, String outputFile) {
        BufferedReader inputReader = null,replacementsReader =null;
        BufferedWriter outputWriter= null;

        try{
            inputReader= new BufferedReader(new FileReader(inputFile));
            replacementsReader=new BufferedReader(new FileReader(replacementsFile));
            outputWriter =new BufferedWriter(new FileWriter(outputFile));

            String replacementsData = "";
            String replacementLine;
            while ((replacementLine = replacementsReader.readLine()) != null) {
                replacementsData += replacementLine + "\n";
            }

            String inputLine;
            while ((inputLine = inputReader.readLine()) != null) {
                String modifiedLine = inputLine;

                int start = 0;
                while (start < replacementsData.length()) {
                    int newlineIndex=replacementsData.indexOf("\n", start);
                    if (newlineIndex==-1) break;

                    String replacement=replacementsData.substring(start, newlineIndex);
                    int spaceIndex=replacement.indexOf(" ");
                    if (spaceIndex ==-1) break;

                    String original =replacement.substring(0, spaceIndex);
                    String replacementWord = replacement.substring(spaceIndex + 1);

                    modifiedLine =modifiedLine.replace(original, replacementWord);

                    start = newlineIndex+1;
                }

                outputWriter.write(modifiedLine);
                outputWriter.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (inputReader !=null)inputReader.close();
                    if (replacementsReader!= null) replacementsReader.close();
                        if (outputWriter !=null) outputWriter.close();
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }



    public static void wordLengthAnalysis(String filename) {
        String l_word = "",s_word = null;
        int Length =0,word_count = 0;


        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                int wordLength = word.length();
                Length += wordLength;
                word_count++;

                if (wordLength > l_word.length()) {
                    l_word = word;
                }

                if (s_word == null || wordLength < s_word.length()) {
                    s_word = word;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error," + filename+ " not found");
            return;
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
            return;
        }

        if (word_count > 0) {
            double average_w_l =(double)Length/ word_count;
            System.out.println(l_word);
                System.out.println(s_word);
            System.out.print(average_w_l);
        } else {
            System.out.println("Write smth");
        }
    }
}


  
