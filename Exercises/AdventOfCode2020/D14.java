package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class D14 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream puzzleInput = new FileInputStream("./testcases/D14.txt");
        Scanner myReader = new Scanner(puzzleInput);

        HashMap<Long, Bitmask> program = new HashMap<>();
        String mask = "";
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            if (line.contains("mask")) {
                mask = line.substring(7);
            } else {
                int intValue = Integer.parseInt(line.split("= ")[1]);
//                String value = Integer.toBinaryString(intValue);
                int address = Integer.parseInt(line.split("[\\[\\]]")[1]);

                ArrayList<Long> addresses = getAddresses(address, mask);
                for (Long ad : addresses) {
                    program.put(ad, new Bitmask(intValue));
                }
            }
        }
//        initializeProgram(program);
        long sumValues = getValueSumP2(program);
        System.out.println(sumValues);
    }

    static long getValueSumP2 (HashMap<Long, Bitmask> program) {
        // the sum of all values left in memory after the initialization program completes
        // value is not masked, and is already int
        long sum = 0;

        for(Map.Entry<Long, Bitmask> entry : program.entrySet()) {
            sum += entry.getValue().intValue;
        }

        return sum;
    }


    static ArrayList<Long> getAddresses (int address, String mask) {
        ArrayList<Long> addresses = new ArrayList<>();
        String binaryAddress = Integer.toBinaryString(address);
        String result = "000000000000000000000000000000000000";

        for (int i = 35; i >= 0; i--) {
            char addressChar = '0';
            if (i > 35 - binaryAddress.length()) addressChar = binaryAddress.charAt(binaryAddress.length() - 36 + i);
            char maskChar = mask.charAt(i);

            if (maskChar == '0') {
                result = replaceChar(result, i, addressChar);
            } else {
                result = replaceChar(result, i, maskChar);
            }
        }

        // have a binary string of countX 1's
        // calc the decimal value of the binary string
        // subtract the value by one everytime

        int countX = 0;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == 'X') countX++;
        }

        long importantNum = (long) (Math.pow(2, countX) - 1);

        while (importantNum >=0) {
            String copy = result;
            String binaryX = Long.toBinaryString(importantNum);;
            int index = binaryX.length() - 1;

            for (int i = copy.length() - 1; i >= 0; i--) {
                if (copy.charAt(i) == 'X') {
                    if (index < 0) {
                        copy = replaceChar(copy, i, '0');
                    } else {
                        copy = replaceChar(copy, i, binaryX.charAt(index));
                    }
                    index--;
                }
            }
            addresses.add(binaryToDecimal(copy));
            importantNum--;
        }

        return addresses;
    }

    static long getValueSum (HashMap<Integer, Bitmask> program) {
        // the sum of all values left in memory after the initialization program completes
        // value is in binary
        long sum = 0;

        for(Map.Entry<Integer, Bitmask> entry : program.entrySet()) {
            String binary = entry.getValue().result;
            long decimal = binaryToDecimal(binary);
            sum += decimal;
        }

        return sum;
    }

    static long binaryToDecimal(String binary) {
        long decimal = 0;
        for (int i = binary.length() - 1; i >= 0; i--){
            char digit = binary.charAt(i);
            if(digit != '0'){
                decimal += Math.pow(2, binary.length() - 1 - i);
            }
        }
        return decimal;
    }

    static void initializeProgram(HashMap<Integer, Bitmask> program) {
        // encode the value through the mask
        for(Map.Entry<Integer, Bitmask> entry : program.entrySet()) {
            String value = entry.getValue().value;
            String mask = entry.getValue().mask;
            String result = entry.getValue().result;

            for (int i = 35; i >= 0; i--) {
                char valueChar = '0';
                if (i > 35 - value.length()) valueChar = value.charAt(value.length() - 36 + i);
                char maskChar = mask.charAt(i);

                if (maskChar != 'X') {
                    result = replaceChar(result, i, maskChar);
                } else {
                    result = replaceChar(result, i, valueChar);
                }
            }
            entry.getValue().result = result;
        }
    }

    static String replaceChar(String str, int index, char replace) {
        return str.substring(0, index) + replace + str.substring(index + 1);
    }

    static class Bitmask {
        // binary string -> decimal int: Integer.parseInt(String, 2)
        // decimal int -> binary string: Integer.toBinaryString(int i)

        String value;
        String mask;
        String result;
        int intValue;

        // constructor
        Bitmask(String value, String mask) {
            this.value = value;
            this.mask = mask;
            this.result = "000000000000000000000000000000000000";
        }

        Bitmask(int value) {
            this.intValue = value;
        }
    }
}
