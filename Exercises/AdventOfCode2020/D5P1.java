package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class D5P1 {
    static String highest="FFFFFFFLLL";
    
    public static void main(String[] args) {
        String s;
        try {
            FileInputStream fis = new FileInputStream("./TestCases/D5.txt");
            Scanner sc = new Scanner(fis);
            while(sc.hasNext()){
                highest(sc.nextLine());
            }
            System.out.println(calcSeatID(highest));
        } catch (IOException ignored){}
        
    }
    
    public static void highest(String s){
        int i=-1;
        while (++i<7){
            if(highest.charAt(i)!=s.charAt(i)){
                if(s.charAt(i)=='B'){
                    highest=s;
                    return;
                } else {
                    return;
                }
            }
        }
        i=6;
        while (++i<10){
            if(highest.charAt(i)!=s.charAt(i)){
                if(s.charAt(i)=='R'){
                    highest=s;
                    return;
                } else {
                    return;
                }
            }
        }
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
