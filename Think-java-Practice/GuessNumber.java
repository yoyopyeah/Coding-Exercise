import java.util.Scanner;
import java.util.Random;

public class GuessNumber {

    public static void extra(int off_number) {
        //extra comparison
        if (off_number == 0) {
            System.out.println("Your guess is correct!");
        } else {
            if (off_number < 0) {
                System.out.println("Your guess was too high");
            } else {
                System.out.println("Your guess was too low");
            }
            System.out.println("Type another number and guess again: ");
            newGuess();
        }
    }

    public static void main(String[] args) {
        //setup
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        int number = random.nextInt(100) + 1;
        System.out.println("I'm thinking of a number between 1 and 100 (including both). Can you guess what it is?");
        System.out.println("Type a number: ");

        int Guess = newGuess();
        while (Guess != number) {
            int off_number = number - Guess;
            System.out.println("You were off by: " + Math.abs(off_number));
            extra(off_number);
        }
    }

    public static int newGuess() {
        Scanner in = new Scanner(System.in);
        int urGuess = in.nextInt();
        System.out.println("Your guess is: " + urGuess);
        return urGuess;
    }
}
