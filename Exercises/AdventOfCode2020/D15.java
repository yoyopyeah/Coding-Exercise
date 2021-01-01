package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class D15 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream puzzleInput = new FileInputStream("./testcases/D15.txt");
        Scanner myReader = new Scanner(puzzleInput);

        // get the starting numbers into an array
        String line = myReader.nextLine();
        String[] temp = line.split(",");
        int[] startNumbers = Arrays.stream(temp).mapToInt(Integer::parseInt).toArray();

        // Hashmap<the number, last round it's said>
        HashMap<Integer, NumRecord> gameRecord = new HashMap<>();
        for (int i = 0; i < startNumbers.length; i++) {
            gameRecord.put(startNumbers[i], new NumRecord(i + 1, true));
        }

        int ans = playGame(startNumbers, gameRecord);
        System.out.println(ans);
    }

    static int playGame(int[] startNumbers, HashMap<Integer, NumRecord> gameRecord) {
        /*
        If that was the first time the number has been spoken, the current player says 0.
        Otherwise, the number had been spoken before; the current player announces how many
          turns apart the number is from when it was previously spoken.
         */
        // play to the 2020th round
        int round = gameRecord.size() + 1;
        int prevNum = startNumbers[startNumbers.length - 1];
        int curNum = 0;

        while (round <= 30000000) {
            // find the curNum
            if (gameRecord.get(prevNum).isFirstTime) {
                // if the prevNum was said for the first time
                curNum = 0;
            } else {
                curNum = round - 1 - gameRecord.get(prevNum).prevRound;
                gameRecord.get(prevNum).prevRound = round - 1;

            }

            if (!gameRecord.containsKey(curNum)) {
                gameRecord.put(curNum, new NumRecord(round, true));
            } else {
                gameRecord.get(curNum).isFirstTime = false;
            }
            prevNum = curNum;
            round++;
        }

        return curNum;
    }

    static class NumRecord {
        int prevRound;
        boolean isFirstTime;

        NumRecord(int prevRound, boolean isFirstTime) {
            this.prevRound = prevRound;
            this.isFirstTime = isFirstTime;
        }
    }
}
