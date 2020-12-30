package AdventOfCode2020;

import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.util.Scanner;
import java.util.regex.Pattern;

public class D4P2 {
    public static void main(String[] args) {
        
        Pattern[] p = { Pattern.compile("byr:(19[2-9][0-9]|200[0-2])\s"),
                Pattern.compile("iyr:(201[0-9]|2020)\s"),
                Pattern.compile("eyr:(202[0-9]|2030)\s"),
                Pattern.compile("hgt:(1([5-8][0-9]|9[0-3])cm|(59|6[0-9]|7[0-6])in)\s"),
                Pattern.compile("hcl:#[0-9a-f]{6}\s"),
                Pattern.compile("ecl:(amb|blu|brn|gry|grn|hzl|oth)\s"),
                Pattern.compile("pid:[0-9]{9}\s")
        };
        
        StringBuilder s;
        int count=0;
        String info;
        
        try {
            FileInputStream testcase = new FileInputStream("./TestCases/D4testcase.txt");
            Scanner sc = new Scanner(testcase);
            while(sc.hasNext()){
                s = new StringBuilder();
                while (sc.hasNext()) {
                    info = sc.nextLine();
                    if(info.equals("")) break;
                    s.append(info).append(" ");
                }
                if(check(s.toString(),p)) count++;
            }
            System.out.println(count);
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
    private static boolean check(String s, Pattern[] patterns){
        
        for (Pattern p: patterns) {
            if(!p.matcher(s).find()) return false;
        }
        return true;
    }
    
}