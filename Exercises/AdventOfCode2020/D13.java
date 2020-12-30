package AdventOfCode2020;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class D13 {
    public static void main(String[] args) {
        try {
            FileInputStream puzzleInput = new FileInputStream("./testcases/d13.txt");
            Scanner myReader = new Scanner(puzzleInput);

            // first line: timestamp
            String line = myReader.nextLine();
            int timestamp = Integer.parseInt(line);

            //second line: buses
            line = myReader.nextLine();
            String[] buses = line.split(",");

            // P1: multiplication of the shortest waiting time and the bus ID
            int ans = getIDxTime(timestamp, buses);
            System.out.println(ans);

            // P2: find the earliest timestamp such that the first bus ID departs at that time and each subsequent
            // listed bus ID departs at that subsequent minute.
            System.out.println(findEarliestTime(buses).toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static BigInteger findEarliestTime(String[] buses) {
        //todo: the answer passes 1 billion and the processing speed is too low rn

        boolean passedMillion = false;
        boolean passedBillion = false;
        boolean passedTillion = false;
        BigInteger maxID = BigInteger.ZERO;
        int maxBusIndex = 0;

        ArrayList<BigInteger> busIDs = new ArrayList<>();
        for (String bus : buses) {
            if (bus.equals("x")) busIDs.add(null);
            else {
                BigInteger id = new BigInteger(bus);
                busIDs.add(id);
                if (id.compareTo(maxID) > 0) {
                    maxID = id;
                    maxBusIndex = busIDs.indexOf(id);
                }
            }
        }

        maxID = busIDs.get(0);
        BigInteger timestamp = BigInteger.ZERO.subtract(maxID);

        boolean isFound = false;
        while (! isFound) {
            timestamp = timestamp.add(maxID);
            isFound = true;

//            if (maxBusIndex != maxID.subtract(timestamp.mod(maxID)).intValue()) {
//                isFound = false;
//                continue;
//            }

            for (int i = 1; i < busIDs.size(); i++) {
                // indexOf(ID) = ID - (timestamp % ID)
                BigInteger ID = busIDs.get(i);
                if (ID != null) {
                    BigInteger temp = ID.subtract(timestamp.mod(ID));
                    if (i != temp.intValue()) {
                        isFound = false;
                        break;
                    }
                }
            }
            if (timestamp.compareTo(new BigInteger("100000000")) > 0 && !passedMillion){
                System.out.println("timestamp has passed 100 million");
                passedMillion = true;
            }
            if (timestamp.compareTo(new BigInteger("1000000000")) > 0 && !passedBillion){
                System.out.println("timestamp has passed 1 billion");
                passedBillion = true;
            }
            if (timestamp.compareTo(new BigInteger("1000000000000")) > 0 && !passedTillion){
                System.out.println("timestamp has passed 1 trillion");
                passedTillion = true;
            }
            if (timestamp.compareTo(new BigInteger("100000000000000")) > 0){
                System.out.println("timestamp has passed 100 trillion");
            }

        }

        return timestamp;
    }

    static int getIDxTime(int timestamp, String[] buses) {
        int busID = 0, waitTime = timestamp;

        for (String bus : buses) {
            if (bus.equals("x")) continue;

            int ID = Integer.parseInt(bus);
            int temp = ID - (timestamp % ID);
            if (temp < waitTime) {
                waitTime = temp;
                busID = ID;
            }
        }

        return busID * waitTime;
    }
}
