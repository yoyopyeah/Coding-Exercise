//Exercise 7.4
//Write a method called sieve that takes an integer parameter, n, and returns a boolean array that indicates, for each number from 0 to n - 1, whether the number is prime.
import java.util.Arrays;
import java.util.Scanner;

public class Sieve {

    public static boolean checkPrime(int n) {
        boolean bool = true;
        if (n < 2) {
            bool = false;
        } else if (n == 2) {
            bool = true;
        } else {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                //the square root is some kind of math principle/property
                if (n % i == 0) {
                    bool = false;
                    break;
                }
            }
        }
        return bool;
    }

    public static boolean[] sieve(int n) {
        boolean[] judge = new boolean[n];
        //just creating the array to be outputted.
        for (int i = 0; i < n; i++) {
            judge[i] = checkPrime(i);
        }
        return judge;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Type in a parameter:");
        int parameter = in.nextInt();
        System.out.println(Arrays.toString(sieve(parameter)));

    }
}
