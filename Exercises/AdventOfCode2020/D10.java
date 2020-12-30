package AdventOfCode2020;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class D10 {
    public static void main(String[] args) {
        try {
            FileInputStream input = new FileInputStream("./TestCases/D10.txt");
            Scanner myReader = new Scanner(input);

            int[] adapters = new int[102];
            int i = 0;
            while (myReader.hasNextLine()) {
                adapters[i] = myReader.nextInt();
                i++;
            }

            Arrays.sort(adapters);
            int ans = calcJolts(adapters);
            System.out.println(ans);

            //todo: find the number of arrangements of the adapters
            BigInteger arrangements = countArrangements(adapters, -1);
            System.out.println(arrangements.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static BigInteger countArrangements(int[] adapters, int startIndex){
        int cur;
        BigInteger count = BigInteger.ZERO;
        if (startIndex == -1) cur = 0;
        else if (startIndex == adapters.length - 1) return BigInteger.ONE;
        else cur = adapters[startIndex];

        for (int i = 1; i <= 3; i++) {
            if (startIndex + i <= adapters.length - 1) {
                if (adapters[startIndex + i] - cur <= 3) {
                    count = count.add(countArrangements(adapters, startIndex + i));
                } else break;
            } else break;
        }
        if (startIndex == 10) {
            System.out.println(count.toString());
        }
        return count;
    }

//    static int countArrangements(int[] adapters, int startIndex){
//        // recursive method, count ++ every time the method is called.
//        // if there's only one option then the number of arrangement stays the same;
//        // when there's 1 + x options, then x more arrangements
//
//        int cur, count = 0;
//        if (startIndex == -1) cur = 0;
//        else if (startIndex == adapters.length) return count;
//        else cur = adapters[startIndex];
//
//        for (int i = 1; i <= 3; i++) {
//            if (startIndex + i >= adapters.length - 1) break;
//            if (adapters[startIndex + i] - cur <= 3) {
//                count++;
//            } else break;
//        }
//        count += countArrangements(adapters, startIndex + 1);
//
//        return count;
//    }


//    static int countArrangements(int[] adapters, int startIndex){
//        // recursive method, count ++ every time the method is called.
////        if (startIndex >= adapters.length - 1) return 1;
//
//        int cur, count = 0;
//        if (startIndex == -1) cur = 0;
//        else cur = adapters[startIndex];
//
//        for (int i = 1; i <= 3; i++) {
//            if (startIndex + i == adapters.length - 1) return 1;
//            if (adapters[startIndex + i] - cur <= 3) {
//                count += countArrangements(adapters, startIndex + i);
//            } else break;
//        }
//
//        return count;
//    }

    static int calcJolts(int[] adapters){
        // finds the number of 1-jolt differences multiplied by the number of 3-jolt differences
        int jolt1 = 0, jolt3 = 1;
        int prev = 0;
        int cur;

        for (int adapter : adapters) {
            cur = adapter;
            if (cur - prev == 1) jolt1++;
            else if (cur - prev == 3) jolt3++;
            prev = cur;
        }

        return jolt1 * jolt3;
    }
}
