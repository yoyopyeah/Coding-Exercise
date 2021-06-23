//Exercise 5.3

import java.util.Scanner;

public class Fermat {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Fermat's Last Theorem says that there are no integers a, b, c, and n such that an + bn = cn, except when n is equal to or smaller than 2. Let's check if he's actually right!");
        int a, b, c, n;
        System.out.println("Please enter an integer for a.");
        a = in.nextInt();
        System.out.println("Please enter an integer for b.");
        b = in.nextInt();
        System.out.println("Please enter an integer for c.");
        c = in.nextInt();
        System.out.println("Please enter an integer for n.");
        n = in.nextInt();
        double left = Math.pow(a, n) + Math.pow(b, n);
        double right = Math.pow(c, n);

        if (left != right) {
            System.out.println("No, that doesn't work.");
        } else {
            if (n > 2) {
                System.out.println("Holy smokes, Fermat was wrong!");
            } else {
                System.out.println("The equation stands but this agrees with Fermat's Last Theorem!");
            }
        }
    }
}
