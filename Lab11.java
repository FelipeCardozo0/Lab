public class Lab11 {

    public static void main(String[] args) {
        // Test case 1: Single word
        System.out.println("Test case 1: Single word");
        countLetters("Hello");

        // Test case 2: Phrase with spaces and punctuation
        System.out.println("\nTest case 2: Phrase with spaces and punctuation");
        countLetters("Hello, World!");

        // Test cases for factorial method
        int[] testCases = {5, 10, 17, -5};

        for (int n : testCases) {
            try {
                System.out.println("Factorial of " + n + ": " + factorial(n));
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Program continues after handling exceptions.");
    }

    public static void countLetters(String word) {
        int[] counts = new int[26];
        word = word.toUpperCase();

        for (int i = 0; i < word.length(); i++) {
            try {
                counts[word.charAt(i) - 'A']++;
            } catch (ArrayIndexOutOfBoundsException e) {
                // Print a useful message along with the invalid character
                System.out.println("Not a letter: " + word.charAt(i));
            }
        }

        // Print frequencies
        System.out.println();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                System.out.println((char) (i + 'A') + ": " + counts[i]);
            }
        }
    }

    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers: " + n);
        }
        if (n > 16) {
            throw new IllegalArgumentException("Factorial is only defined for values up to 16 due to int overflow: " + n);
        }

        int fac = 1;
        for (int i = n; i > 0; i--) {
            fac *= i;
        }
        return fac;
    }
}
