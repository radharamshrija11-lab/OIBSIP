
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int round = 0;
        int totalAttempts = 0;
        String playAgain;

        System.out.println("================================");
        System.out.println("     NUMBER GUESSING GAME");
        System.out.println("================================");

        do {
            round++;

            int min = 1;
            int max = 100;
            int maxAttempts = 7;

            System.out.println("\nChoose Difficulty:");
            System.out.println("1. Easy   (1-50, 10 attempts)");
            System.out.println("2. Medium (1-100, 7 attempts)");
            System.out.println("3. Hard   (1-200, 5 attempts)");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    max = 50;
                    maxAttempts = 10;
                    break;
                case 2:
                    max = 100;
                    maxAttempts = 7;
                    break;
                case 3:
                    max = 200;
                    maxAttempts = 5;
                    break;
                default:
                    System.out.println("Invalid choice! Medium selected.");
            }

            int number = random.nextInt(max) + 1;
            int attempts = 0;
            boolean guessed = false;

            System.out.println("\nRound " + round);
            System.out.println("Guess a number between 1 and " + max);
            System.out.println("You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) {
                System.out.print("\nEnter your guess: ");
                int guess = sc.nextInt();
                attempts++;

                System.out.println("Attempts used: " + attempts
                        + "/" + maxAttempts);

                if (guess < 1 || guess > max) {
                    System.out.println("Enter a number within the range!");
                    attempts--;
                    continue;
                }

                if (guess > number) {
                    System.out.println("Too High!");
                } else if (guess < number) {
                    System.out.println("Too Low!");
                } else {
                    System.out.println("Correct!");
                    System.out.println("You guessed it in "
                            + attempts + " attempts!");
                    guessed = true;
                    totalAttempts += attempts;
                    break;
                }
            }

            if (!guessed) {
                System.out.println("\nYou Lost!");
                System.out.println("The correct number was: " + number);
            }

            System.out.println("\nRound " + round + " Summary:");
            if (guessed) {
                System.out.println("Guessed in " + attempts + " attempts.");
            } else {
                System.out.println("Failed after " + maxAttempts
                        + " attempts.");
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            playAgain = sc.next();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("\n========== FINAL SCORE ==========");
        System.out.println("Total rounds played: " + round);
        System.out.println("Total attempts in won rounds: " + totalAttempts);
        System.out.println("Thanks for playing!");

        sc.close();
    }
}
