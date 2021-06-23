package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class D7 {
    public static void main(String[] args) {
        String line;
        int count = 0, newFound = 0;
        boolean foundNewBag = true;
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> bags = new ArrayList<>();
        bags.add("shiny gold");
        try{
            Scanner myReader;
            while (foundNewBag) {
                myReader = new Scanner(new FileInputStream("./TestCases/D7.txt"));
                newFound = findBags(myReader, bags);
                if(newFound == 0) foundNewBag = false;
                myReader.close();
            }
            System.out.println(bags.size() - 1);
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    //todo: find the number of bags shiny gold contains
    static int findBags(Scanner myReader, ArrayList<String> bags) {
        int iniital = bags.size();
        ArrayList<String> temp = new ArrayList<>();
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String[] entry = line.split(" bags contain");
            for (String bag : bags) {
                if (entry[1].contains(bag)) {
                    temp.add(entry[0]);
                    break;
                }
            }
        }
        for (String bag : temp) {
            if (! bags.contains(bag)) bags.add(bag);
        }
        return bags.size() - iniital;
    }
}
