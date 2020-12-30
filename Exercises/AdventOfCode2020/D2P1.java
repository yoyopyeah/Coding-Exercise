package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class D2P1 {
    public static void main(String[] args) {
        int min, max, temp, count=0;
        char c;
        String pass;
        try {
            FileInputStream testSet = new FileInputStream("./TestCases/D2TestCases.txt");
//       FileInputStream testSet = new FileInputStream("test.txt");
            Scanner myReader = new Scanner(testSet);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                temp = data.indexOf('-');
                min = Integer.parseInt(data.substring(0, temp));
                max = Integer.parseInt(data.substring(temp+1, data.indexOf(' ')));
                c = data.charAt(data.indexOf(':')-1);
                pass = data.substring(data.lastIndexOf(' ')+1);
                if(checkCondition2(min, max, c, pass)) count++;
//          break;
            }
            myReader.close();
            testSet.close();
            System.out.println(count);
        } catch (IOException e) {
            System.out.println("An error occurred.");
//       e.printStackTrace();
        }
    }
    
    private static boolean checkCondition(int min, int max, char c, String pass){
//    System.out.println("min = "+min+" max = "+max+" c = "+c+" pass = "+pass);
        int i = 0, len=pass.length(), count=0;
        while(i<len){
            if(pass.charAt(i++)==c) count++;
            if(count>max) return false;
        }
        return count>=min;
    }
    
    private static boolean checkCondition2(int min, int max, char c, String pass){
//    System.out.println("min = "+min+" max = "+max+" c = "+c+" pass = "+pass);
        int count=0;
        if(pass.charAt(min-1)==c) count++;
        if(pass.charAt(max-1)==c) count++;
        
        return count==1;
    }
}
