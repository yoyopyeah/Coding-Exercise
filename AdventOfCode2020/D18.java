package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D18 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream puzzleInput = new FileInputStream("./testcases/D18.txt");
        Scanner myReader = new Scanner(puzzleInput);

        long sum = 0;
        // record[result, index]
        while (myReader.hasNextLine()) {
            long[] record = {0, 0};
            String expression = myReader.nextLine();
            long temp = evaluateExpressions(expression, record)[0];
//            System.out.println(temp);
            sum += temp;

        }
        System.out.println(sum);
    }

    static long[] evaluateExpressions(String expression, long[] record) {
        // calculate a whole line of expression
        // record[answer, index]
        // index is used to skip ahead to jump out of the parentheses
        StringBuilder builder = new StringBuilder();

        for (long i = record[1]; i < expression.length(); i++) {
            char target = expression.charAt((int) i);

            if (target == '(') {
                // go into the parentheses
                record[1] = i + 1;
                long temp = evaluateExpressions(expression, record)[0];
                i = record[1];
                builder.append(temp);
            } else if (target == ')') {
                // calculate what's in the parentheses first
                long ans = calculateP2(builder.toString());
                record[0] = ans;
                record[1] = i; // continue calculation from after the parentheses
                return record;
            } else {
                builder.append(target);
            }
        }
        record[0] = calculateP2(builder.toString());
        return record;
    }

    static long calculate(String equation){
        // calculate a expression without parentheses
        // calculate from left to right, no priority
        String[] units = equation.split(" ");
        long ans = 0;
        String operator = "";
        boolean isInitial = true;
        for (String unit : units) {
            if (isInitial) {
                ans = Long.parseLong(unit);
                isInitial = false;
                continue;
            }
            if (unit.contains("+") || unit.contains("*")) {
                operator = unit;
            } else {
                if (operator.contains("+")) ans += Long.parseLong(unit);
                else ans *= Long.parseLong(unit);
            }
        }
        return ans;
    }


    static long calculateP2(String equation) {
        // addition + has priority over multiplication x
        StringBuilder builder = new StringBuilder();
        String[] units = equation.split(" ");
        long ans = 1;
        String operator = "", prevUnit = "";
        boolean isInitial = true;

        // first calculate the additions, and build up a string of only multiplications
        for (String unit : units) {
            if (isInitial) {
                prevUnit = unit;
                isInitial = false;
                continue;
            }
            if (unit.contains("+")) {
                operator = unit;
            } else if (unit.contains("*")) {
                operator = unit;
                builder.append(prevUnit).append(" * ");
            } else { // when we get a number
                if (operator.contains("+")) {
                    long tempSum = Long.parseLong(prevUnit) + Long.parseLong(unit);
                    prevUnit = String.valueOf(tempSum);
                } else {
                    prevUnit = unit;
                }
            }
        }
        builder.append(prevUnit);

        // calculate the multiplications
        String[] multiples = builder.toString().split(" \\* ");
        for (String num : multiples) {
            ans *= Long.parseLong(num);
        }

        return ans;
    }
}
