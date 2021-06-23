import java.util.Scanner;
import java.util.Arrays;

public class GradeCount {

    static double[] scores = {9, 22, 3, 4};

    //I want: a method that caculates the number of students in the given grade.
    public static void main(String args[]) {
      //setup
      Scanner in = new Scanner(System.in);
      String grade = in.nextLine();

      int students = sum(grade);
      System.out.println("The number of students with grade " + grade + " is: " + students);
    }

    public static int sum(String grade) {
        int A = 0;
        int B = 0;
        double[] numbers = Arrays.copyOf(scores, scores.length);
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 10) {
                A ++;
            } else {
                B ++;
            }
        }
        if (grade.equals("A")) {
            return A;
        } else {
            return B;
        }
    }
}
