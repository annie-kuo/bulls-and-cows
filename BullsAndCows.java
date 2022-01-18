package presentations;

import java.util.Scanner;

public class BullsAndCows {

    public static void main(String[] args) {
        playGame();
    }


    public static int[] checkGuess(String guess, String secretNum) {
        // Input validation
        boolean correctLength = (guess.length() == 4) && (secretNum.length() == 4);

        boolean isDigits = true;
        // check for guess
        for (int i = 0; i < guess.length(); i++) {
            if (!(48 <= guess.charAt(i) && guess.charAt(i) <= 57)) {
                isDigits = false;
                break;
            }
        }
        // check for secretNum
        for (int i = 0; i < secretNum.length(); i++) {
            if (!(48 <= secretNum.charAt(i) && secretNum.charAt(i) <= 57)) {
                isDigits = false;
                break;
            }
        }

        // Initialize variable to be returned
        int[] result = new int[2];

        if (!correctLength || !isDigits) {
            throw new IllegalArgumentException();

        } else {
            // Checks for bulls
            int numBulls = 0;
            int[] secretArr = new int[10];
            int[] guessArr = new int[10];

            for (int i = 0; i < 4; i++) {
                if (guess.charAt(i) == secretNum.charAt(i)) {
                    numBulls++;
                } else {
                    secretArr[secretNum.charAt(i) - 48]++;
                    guessArr[guess.charAt(i) - 48]++;
                }
            }
            // Compute the number of cows
            int numCows = 0;
            for (int i = 0; i < 10; i++) {
                numCows += Math.min(secretArr[i], guessArr[i]);
            }
            result[0] = numBulls;
            result[1] = numCows;
        }
        return result;
    }


    public static void playGame() {
        // Generate a random secret number
        java.util.Random randomGenerator = new java.util.Random();
        int randomNum = randomGenerator.nextInt(9999);
        String secretNum = "" + randomNum;
        // Check that the secret number contains 4 digits
        while (secretNum.length() < 4) {
            secretNum = "0" + secretNum;
        }
        System.out.println("(Secret Number: " + secretNum + ")");

        // Create scanner to retrieve input from the user
        Scanner s = new Scanner(System.in);

        // Retrieve guess from user and compute num of bulls and cows
        // until the guess is correct
        boolean gameOver = false;
        while (!gameOver) {
            System.out.print("Enter you guess here: ");
            String guess = s.nextLine();

            if (guess.equals(secretNum)) {
                gameOver = true;
                System.out.println("You have guessed the secret number!");
                break;
            }

            int[] result = checkGuess(guess, secretNum);
            System.out.println("You have " + result[0] + " bull(s) and " + result[1] + " cow(s).");

        }
    }
}
