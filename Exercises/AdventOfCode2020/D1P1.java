package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class D1P1{
    public static void main(String args[]) {
        int[] numbers = new int[200];
        int pos = 0;
        try{
            FileInputStream testSet = new FileInputStream("./TestCases/D1TestCase.txt");
            Scanner myReader = new Scanner(testSet);
            while(myReader.hasNextInt()){
                numbers[pos] = myReader.nextInt();
                pos++;
            }
            Arrays.sort(numbers);
        } catch (IOException ignored){
        
        }
        int answer = findAndMultiply(numbers);
        System.out.println(answer);
    }
    
    public static int findAndMultiply2(int[] ts) {
        int sum, p1=0, p2=ts.length-1;
        while (p1 < p2) {
            sum = ts[p1] + ts[p2];
//          System.out.println(sum);
            if (sum == 2020) return ts[p1]*ts[p2];
            if (sum > 2020) p2--;
            else p1++;
        }
        return 0;
    }
    
    public static int findAndMultiply(int[] ts) {
        int p3 = ts.length - 1, sum, p1, p2, diff;
        while (p3 != 2) {
            p1 = 0;
            p2 = p3 - 1;
            while (p1 < p2) {
                sum = ts[p1] + ts[p2] + ts[p3];
//          System.out.println(sum);
                if (sum == 2020) return ts[p1]*ts[p2]*ts[p3];
                if (sum > 2020) p2--;
                else p1++;
            }
            p3--;
        }
        return 0;
    }
    
}