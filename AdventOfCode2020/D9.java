package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class D9 {
    public static void main(String[] args) {

        try {
            FileInputStream puzzleInput = new FileInputStream("./TestCases/D9.txt");
            Scanner myReader = new Scanner(puzzleInput);

            int i = 0;
            BigInteger num = new BigInteger("0");
            ArrayList<BigInteger> preamble = new ArrayList<>();
            for (; i < 25; i++){
//                preamble.add(Integer.parseInt(myReader.nextLine()));
                preamble.add(new BigInteger(myReader.nextLine()));
            }
            while (myReader.hasNextLine()) {
                num = new BigInteger(myReader.nextLine());
                if (! checkNum(preamble, num)) break;
                preamble.remove(0);
                preamble.add(num);
            }
            System.out.println(num.toString());

            myReader = new Scanner(new FileInputStream("./TestCases/D9.txt"));
            ArrayList<BigInteger> range = findRange(myReader, num);

            // find the min and max in the list
            BigInteger min = num;
            BigInteger max = new BigInteger("0");
            for (BigInteger candidate : range) {
                if (candidate.compareTo(min) < 0) min = candidate;
                if (candidate.compareTo(max) > 0) max = candidate;
            }
            System.out.println((min.add(max)).toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<BigInteger> findRange(Scanner reader, BigInteger num) {
        ArrayList<BigInteger> range = new ArrayList<>();
        BigInteger sum = calcSum(range);
        while (sum.compareTo(num) != 0) {
            if (sum.compareTo(num) < 0) {
                range.add(new BigInteger(reader.nextLine()));
            } else {
                range.remove(0);
            }
            sum = calcSum(range);
        }
        return range;
    }

    static BigInteger calcSum(ArrayList<BigInteger> list) {
        BigInteger result = new BigInteger("0");
        for (BigInteger num : list) {
            result = result.add(num);
        }
        return result;
    }

    static boolean checkNum(ArrayList<BigInteger> numList, BigInteger num) {
        //true if num equals to the sum of two nums from the list

        ArrayList<BigInteger> copy = new ArrayList<>();
        copy.addAll(numList);
        Collections.sort(copy);
        int pt1 = 0, pt2 = numList.size() - 1;
        while (pt1 < pt2) {
            BigInteger sum = copy.get(pt1).add(copy.get(pt2));
            if (sum.equals(num)) {
                return true;
            }
            if (sum.compareTo(num) < 0) pt1++;
            else pt2--;
        }

        return false;
    }


}
