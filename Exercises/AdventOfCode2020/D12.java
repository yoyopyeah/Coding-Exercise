package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class D12 {
    public static void main(String[] args) {
        try {
            FileInputStream puzzleInput = new FileInputStream("./TestCases/D12.txt");
            Scanner myReader = new Scanner(puzzleInput);

            // P1: simply follow the instruction
            int[] coordinate = followNavigation(myReader);
            int manhattanDistance = Math.abs(coordinate[0]) + Math.abs(coordinate[1]);
            System.out.println(manhattanDistance);

            // P2: follow the waypoint
            myReader = new Scanner(new FileInputStream("./TestCases/D12.txt"));
            coordinate = followWaypoint(myReader);
            manhattanDistance = Math.abs(coordinate[0]) + Math.abs(coordinate[1]);
            System.out.println(manhattanDistance);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static int[] followWaypoint (Scanner myReader) {
        /*    N
           W  +  E
              S    */

        int[] coordinate = {0, 0};
        int[] waypoint = {10, 1};

        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            char action = line.charAt(0);
            int units = Integer.parseInt(line.substring(1));

            if (action == 'F') {
                coordinate[0] += waypoint[0] * units;
                coordinate[1] += waypoint[1] * units;
            } else if (action == 'L' || action == 'R') {
                updateWaypointDirection(waypoint, line);
            } else {
                move(waypoint, action, units);
            }
        }
        return coordinate;
    }

    static void updateWaypointDirection(int[] waypoint, String instruction) {
        /*    w
           W  +  E
              S    */

        if (waypoint[0] == 0 && waypoint[1] == 0) return;

        int[] copy = Arrays.copyOf(waypoint, 2);
        char turnDir = instruction.charAt(0);
        int degree = Integer.parseInt(instruction.substring(1));

        int turnTimes = degree / 90;
        if (turnDir == 'L') turnTimes = -turnTimes;
        turnTimes = turnTimes % 4;

        switch (turnTimes) {
            case 0: return;
            case 1:
            case -3:
                waypoint[0] = copy[1];
                waypoint[1] = -copy[0];
                break;
            case 2:
            case -2:
                waypoint[0] = -copy[0];
                waypoint[1] = -copy[1];
                break;
            case 3:
            case -1:
                waypoint[0] = -copy[1];
                waypoint[1] = copy[0];
                break;
            default:
                break;
        }
    }

    static int[] followNavigation (Scanner myReader) {
        int[] coordinate = {0, 0};
        char direction = 'E'; //the ship starts with facing East

        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            char action = line.charAt(0);
            int units = Integer.parseInt(line.substring(1));

            if (action == 'L' || action == 'R') {
                direction = updateDirection (direction, line);
            } else if (action == 'F') {
                move(coordinate, direction, units);
            } else {
                move(coordinate, action, units);
            }
        }

        return coordinate;
    }

    static void move(int[] coordinate, char direction, int units) {
        switch (direction) {
            case 'N' -> coordinate[1] += units;
            case 'S' -> coordinate[1] -= units;
            case 'W' -> coordinate[0] -= units;
            case 'E' -> coordinate[0] += units;
        }
    }

    static char updateDirection(char oldDirection, String instruction) {
        /*    N
           W  +  E
              S    */

        char turnDir = instruction.charAt(0);
        int degree = Integer.parseInt(instruction.substring(1));
        int turnTimes = degree / 90;
        if (turnDir == 'L') turnTimes = -turnTimes;

        char[] compass = {'E', 'S', 'W', 'N'};
        int index = switch (oldDirection) {
            case 'E' -> 0;
            case 'S' -> 1;
            case 'W' -> 2;
            case 'N' -> 3;
            default -> 0;
        };

        index = (index + turnTimes) % 4;
        if (index < 0) index = 4 + index;
        return compass[(index) % 4];
    }
}
