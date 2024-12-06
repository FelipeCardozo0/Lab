public class Lab11 {
    public static void main(String[] args) {
        countLetters("Botafogo");
        countLetters("Libertadoress!!!");

        int[] testCases = {5, 10, 16, 20, -5};
        for (int n : testCases) {
            try {
                System.out.println("Factorial of " + n + ": " + factorial(n));
            } catch (IllegalArgumentException e) {
                System.out.println("Exception for input " + n + ": " + e.getMessage());
            }
        }
        System.out.println("Program continues after handling exceptions.");
    }

    public static void countLetters(String word) {
        int[] counts = new int[26];
        // Convert to all upper case
        word = word.toUpperCase();
        // Count frequency of each letter in the string
        for (int i = 0; i < word.length(); i++) {
            try {
                counts[word.charAt(i) - 'A']++;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Not a letter: " + word.charAt(i));
            }
        }
        // Print frequencies
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                System.out.println((char) (i + 'A') + ": " + counts[i]);
            }
        }
    }

    public static int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Factorial is not defined for negative numbers: " + n);
        if (n > 16) throw new IllegalArgumentException("Factorial is not defined for numbers greater than 16: " + n);

        int fac = 1;
        for (int i = n; i > 0; i--) {
            fac *= i;
        }
        return fac;
    }
}
