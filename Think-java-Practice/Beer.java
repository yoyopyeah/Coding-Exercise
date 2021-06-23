//Exercise 9.1
/*99 bottles of beer on the wall,
99 bottles of beer,
ya' take one down, ya' pass it around,
98 bottles of beer on the wall.
...
No bottles of beer on the wall,
no bottles of beer,
ya' can’t take one down, ya' can’t pass it around,
'cause there are no more bottles of beer on the wall!*/
import java.util.Scanner;
import java.util.Arrays;

class Beer {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Enter the number you want to start with:");
    int start = in.nextInt();
    lyrics(start);
  }

  public static void lyrics(int n) {
      for (int i = n; i >= 0; i--) {
          if (i == 0) {
              System.out.println("No bottles of beer on the wall,");
              System.out.println("no bottles of beer,");
              System.out.println("ya' can't take one down, ya' can't pass it around");
              System.out.println("'cause there are no more bottles of beer on the wall!");
          } else {
              part(i);
          }
      }
  }


  public static void part(int number) {
    System.out.println(number + " bottles of beer on the wall,");
    System.out.println(number + " bottles of beer,");
    System.out.println("ya' take one down, ya' pass it around,");
    System.out.println((number - 1) + " bottles of beer on the wall.");
    System.out.println();
  }
}
