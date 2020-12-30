package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class D5P2 {
    
    public static void main(String[] args) {
        String s;
        boolean[] set = new boolean[1023];
        try {
            FileInputStream fis = new FileInputStream("./TestCases/D5.txt");
            Scanner sc = new Scanner(fis);
            while(sc.hasNext()){
                set[calcSeatID(sc.nextLine())]=true;
            }
            int i=2;
            System.out.println(Arrays.toString(set));
            while(!set[++i]);
            while(set[++i]);
            System.out.println(i);
        } catch (IOException ignored){}
    }
    
    static int calcSeatID(String s){
        int i=-1, id=0;
        while(++i<7){
            if(s.charAt(i)=='B') id+=(int) Math.pow(2,6-i);
        }
        id*=8;
        i=6;
        while(++i<10){
            if(s.charAt(i)=='R') id+=(int) Math.pow(2,9-i);
        }
        return id;
    }
}
