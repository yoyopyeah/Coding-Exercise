//Exercise 5.7: Write a method named areFactors that takes an integer n and an array of integers, and returns true if the numbers in the array are all factors of n (which is to say that n is divisible by all of them).
import java.util.Scanner;
import java.util.Arrays;

public class Factors {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter an integer:");
        int n = in.nextInt();
        System.out.println("Enter the no. elements you want in the array:");
        int k = in.nextInt();
        System.out.println("Enter all the elements:");
        int[] numbers = new int[k];
        for (int i = 0; i < k; i++) {
            numbers[i] = in.nextInt();
        }
        System.out.println(areFactors(n, numbers));
    }

    public static boolean areFactors(int n, int[] a) {
        boolean bool = true;
        for (int number: a) {
            if (n % number != 0) {
                bool = false;
            }
        }
        return bool;
    }
}
