import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("🎮 Welcome to the Number Guessing Game!");
        System.out.print("Enter your name: ");
        String playerName = sc.nextLine().trim();

        boolean playAgain = true;

        while (playAgain) {
            int level = 0;
            while (true) {
                System.out.println("\nSelect Difficulty Level:");
                System.out.println("1. Easy   (1 - 50, 10 attempts)");
                System.out.println("2. Medium (1 - 100, 7 attempts)");
                System.out.println("3. Hard   (1 - 200, 5 attempts)");
                System.out.print("Enter level (1-3): ");

                String line = sc.nextLine().trim();
                try {
                    level = Integer.parseInt(line);
                    if (level >= 1 && level <= 3) break;
                    System.out.println("Please enter 1, 2 or 3.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number (1-3).");
                }
            }
            int maxRange, maxAttempts, penalty;
            String levelName;
            if (level == 1) {
                maxRange = 50;
                maxAttempts = 10;
                penalty = 5;
                levelName = "Easy";
            } else if (level == 2) {
                maxRange = 100;
                maxAttempts = 7;
                penalty = 10;
                levelName = "Medium";
            } else {
                maxRange = 200;
                maxAttempts = 5;
                penalty = 20;
                levelName = "Hard";
            }

            int secretNumber = random.nextInt(maxRange) + 1;
            System.out.println("\n🎯 Level: " + levelName);
            System.out.println("I have picked a number between 1 and " + maxRange + ".");
            System.out.println("You have " + maxAttempts + " attempts. Good luck!\n");

            int attempts = 0;
            boolean guessed = false;
            int score = 0;

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                String input = sc.nextLine().trim();

                int guess;
                try {
                    guess = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid integer.");
                    continue;
                }

                attempts++;

                if (guess == secretNumber) {
                    score = Math.max(0, 100 - (attempts - 1) * penalty);
                    System.out.println("\n🎉 Correct! " + playerName + ", you guessed it in " + attempts + " attempts.");
                    System.out.println("Your score: " + score);
                    guessed = true;
                    break;
                }

                int diff = Math.abs(secretNumber - guess);
                if (guess < secretNumber) {
                    if (diff <= 5) System.out.println("🔥 Very close! But a bit too low.");
                    else System.out.println("Too low! Try again.");
                } else {
                    if (diff <= 5) System.out.println("🔥 Very close! But a bit too high.");
                    else System.out.println("Too high! Try again.");
                }

                System.out.println("Attempts left: " + (maxAttempts - attempts) + "\n");
            }

            if (!guessed) {
                System.out.println("\n❌ Out of attempts! The correct number was " + secretNumber + ".");
                System.out.println("Better luck next time!");
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            String again = sc.nextLine().trim().toLowerCase();
            playAgain = again.equals("yes");
        }

        System.out.println("\nThanks for playing! 👋");
        sc.close();
    }
}
