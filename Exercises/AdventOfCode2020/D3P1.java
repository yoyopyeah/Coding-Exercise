package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class D3P1 {
    //P1
//    public static void main(String[] args) {
//        String line;
//        int xpos = 0, treeCount = 0;
//        try{
//            FileInputStream testSet = new FileInputStream("./TestCases/D3.txt");
//            Scanner myReader = new Scanner(testSet);
//            myReader.nextLine(); //skips the first line
//            while(myReader.hasNextLine()){
//                xpos += 3;
//                line = myReader.nextLine();
//                if (line.charAt(xpos % line.length()) == '#') treeCount++;
//            }
//            System.out.println(treeCount);
//        } catch (IOException ignored){
//
//        }
//    }

    //P2
    //1, 3, 5, 7, 1/2
    public static void main(String[] args) {
        String line;
        int lineNum=0;
        int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0;
        //R1D1, R3D1, R5D1, R7D1, R1D2
        try{
            FileInputStream testSet = new FileInputStream("./TestCases/D3.txt");
            Scanner myReader = new Scanner(testSet);
            myReader.nextLine(); //skips the first line
            while(myReader.hasNextLine()){
                lineNum++;
                line = myReader.nextLine();
                if (line.charAt(lineNum % line.length()) == '#') count1++;
                if (line.charAt((lineNum + 2 * lineNum) % line.length()) == '#') count2++;
                if (line.charAt((lineNum + 4 * lineNum) % line.length()) == '#') count3++;
                if (line.charAt((lineNum + 6 * lineNum) % line.length()) == '#') count4++;
                if (lineNum % 2 == 0) {
                    if (line.charAt((lineNum / 2) % line.length()) == '#') count5++;
                }
            }
            System.out.println(count1 + ", " + count2 + ", " + count3 + ", " + count4 + ", " + count5);
            System.out.println(count1 * count2 * count3 * count4 * count5);
        } catch (IOException ignored){

        }
    }
}
