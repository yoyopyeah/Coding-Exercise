package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D6 {
//    public static void main(String[] args) {
//        String line, ans = "";
//        int count = 0, sum = 0;
//        try {
//            FileInputStream testSet = new FileInputStream("./TestCases/D6.txt");
//            Scanner myReader = new Scanner(testSet);
//            while (myReader.hasNextLine()) {
//                line = myReader.nextLine();
//
//                if (line.length() == 0) { //if an empty line
//                    count = ans.length();
//                    sum += count;
//                    ans = ""; //reset ans string
//                    continue;
//                }
//
//                for (int i = 0; i < line.length(); i++) {
//                    char c = line.charAt(i);
//                    if (!ans.contains(""+c)) {
//                        ans = ans.concat(""+c);
//                    }
//                }
//            }
//            count = ans.length();
//            sum += count;
//            System.out.println(sum);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }


    public static void main(String[] args) {
        String line, firstLine = "";
        int count = 0, sum = 0;
        boolean isFirstLine = true;
        try {
            FileInputStream testSet = new FileInputStream("./TestCases/D6.txt");
            Scanner myReader = new Scanner(testSet);
            while (myReader.hasNextLine()) {
                line = myReader.nextLine();

                if (line.length() == 0) { //if an empty line
                    count = firstLine.length();
                    sum += count;
                    firstLine = ""; //reset firstLine string
                    isFirstLine = true;
                    continue;
                }

                if (isFirstLine) {
                    firstLine = line;
                } else {
                    for (int i = 0; i < firstLine.length(); i++) {
                        char c = firstLine.charAt(i);
                        if (! line.contains(""+c)) {
                            firstLine = removeChar(firstLine, c);
                            i--;
                        }
                    }
                }
                isFirstLine = false;
            }
            count = firstLine.length();
            sum += count;
            System.out.println(sum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String removeChar(String str, char c) {
        String result = "";
        int removeIndex = str.indexOf(c);

        if (removeIndex == 0) return str.substring(1);
        if (removeIndex == str.length() - 1) return str.substring(0, str.length()-1);

        result = str.substring(0, removeIndex);
        result = result.concat(str.substring(removeIndex + 1));
        return result;
    }
}
