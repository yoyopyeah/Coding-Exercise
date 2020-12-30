package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class D4P1 {
    public static void main(String[] args) {
        String passport = "";
        int count = 0;
        try {
            FileInputStream testSet = new FileInputStream("./TestCases/D4testcase.txt");
//       FileInputStream testSet = new FileInputStream("test.txt");
            Scanner myReader = new Scanner(testSet);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (!data.equals("")) {  // if data starts with \n
                    passport = passport + " " + data;
                } else {
                    if (checkValid(passport)) count++;
                    passport = "";
                }
            }
            if (checkValid(passport)) count++;
            myReader.close();
            testSet.close();
            System.out.println(count);
        } catch (IOException e) {
            System.out.println("An error occurred.");
//       e.printStackTrace();
        }
    }
    
    public static boolean checkValid(String str) {
        return (str.contains("ecl") && str.contains("eyr") && str.contains("hcl") && str.contains("byr") && str.contains("iyr") && str.contains("hgt") && str.contains("pid"));
//      ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm
        //it's ok to miss cid only
    }
}
