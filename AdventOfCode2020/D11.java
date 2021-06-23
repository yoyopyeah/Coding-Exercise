package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class D11 {
    public static void main(String[] args) {
        try {
            FileInputStream puzzleInput = new FileInputStream("./TestCases/D11.txt");
            Scanner myReader = new Scanner(puzzleInput);

//            System.out.println(myReader.nextLine().length());

            char[][] seats = new char[98][95];
//            char[][] seats = new char[10][10];

            int lineNum = 0;
            while (myReader.hasNextLine()){
                String line = myReader.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    seats[lineNum][i] = line.charAt(i);
                }
                lineNum++;
            }

            int ans = countFinalOccupied(seats);
            System.out.println(ans);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static char[][] copyMatrix(char[][] original) {
        char[][] copy = new char[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[0].length);
        }
        return copy;
    }

    static int countFinalOccupied(char[][] seats) {
        int count = 0;
        char[][] prev = copyMatrix(seats);
        seats = updateSeats(seats);

        // update seats until no more changes happen
        while (! Arrays.deepEquals(prev, seats)) {
            prev = copyMatrix(seats);
            seats = updateSeats(seats);
        }

        // count the number of seats occupied
        for (char[] seat : seats) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seat[j] == '#') count++;
            }
        }

        return count;
    }

    static char[][] updateSeats(char[][] seats) {
        char[][] copy = copyMatrix(seats);
        boolean becomeOccupied = true;
        int countOccupied = 0;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++){
                char checkSeat = seats[i][j];
                if (checkSeat == '.') continue;
                char[] adjacentSeats = getAdjacentSeatsP2(seats, i, j);

                //If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
                if (checkSeat == 'L') {
                    for (char seat : adjacentSeats) {
                        if (seat == '#') {
                            becomeOccupied = false;
                            break;
                        }
                    }
                    if (becomeOccupied) copy[i][j] = '#';
                    becomeOccupied = true;
                }

                // If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
                if (checkSeat == '#') {
                    for (char seat : adjacentSeats) {
                        if (seat == '#') countOccupied++;
                    }

                    // P1: >= 4
                    // P2: >= 5
                    if (countOccupied >= 5) copy[i][j] = 'L';
                    countOccupied = 0;
                }
            }
        }
        return copy;
    }

    static char[] getAdjacentSeatsP2(char[][] seats, int xValue, int yValue) {
        char[] adjacentSeats = new char[8];
        int flag = 0, i = 0, x, y;

        while (flag < 8) {
            i++;

            // check top left
            if (adjacentSeats[0] == '\0') {
                x = xValue - i;
                y = yValue - i;

                if (x < 0 || x >= seats.length || y < 0 || y >= seats[0].length) {
                    adjacentSeats[0] = '.';
                    flag++;
                } else if (seats[x][y] != '.') {
                    adjacentSeats[0] = seats[x][y];
                    flag++;
                }
            }

            // check top
            if (adjacentSeats[1] == '\0') {
                x = xValue;
                y = yValue - i;

                if (x < 0 || x >= seats.length || y < 0 || y >= seats[0].length) {
                    adjacentSeats[1] = '.';
                    flag++;
                } else if (seats[x][y] != '.') {
                    adjacentSeats[1] = seats[x][y];
                    flag++;
                }
            }

            // check top right
            if (adjacentSeats[2] == '\0') {
                x = xValue + i;
                y = yValue - i;

                if (x < 0 || x >= seats.length || y < 0 || y >= seats[0].length) {
                    adjacentSeats[2] = '.';
                    flag++;
                } else if (seats[x][y] != '.') {
                    adjacentSeats[2] = seats[x][y];
                    flag++;
                }
            }

            // check left
            if (adjacentSeats[3] == '\0') {
                x = xValue - i;
                y = yValue;

                if (x < 0 || x >= seats.length || y < 0 || y >= seats[0].length) {
                    adjacentSeats[3] = '.';
                    flag++;
                } else if (seats[x][y] != '.') {
                    adjacentSeats[3] = seats[x][y];
                    flag++;
                }
            }
            // check right
            if (adjacentSeats[4] == '\0') {
                x = xValue + i;
                y = yValue;

                if (x < 0 || x >= seats.length || y < 0 || y >= seats[0].length) {
                    adjacentSeats[4] = '.';
                    flag++;
                } else if (seats[x][y] != '.') {
                    adjacentSeats[4] = seats[x][y];
                    flag++;
                }
            }
            // check bottom left
            if (adjacentSeats[5] == '\0') {
                x = xValue - i;
                y = yValue + i;

                if (x < 0 || x >= seats.length || y < 0 || y >= seats[0].length) {
                    adjacentSeats[5] = '.';
                    flag++;
                } else if (seats[x][y] != '.') {
                    adjacentSeats[5] = seats[x][y];
                    flag++;
                }
            }
            // check bottom
            if (adjacentSeats[6] == '\0') {
                x = xValue;
                y = yValue + i;

                if (x < 0 || x >= seats.length || y < 0 || y >= seats[0].length) {
                    adjacentSeats[6] = '.';
                    flag++;
                } else if (seats[x][y] != '.') {
                    adjacentSeats[6] = seats[x][y];
                    flag++;
                }
            }
            // check bottom right
            if (adjacentSeats[7] == '\0') {
                x = xValue + i;
                y = yValue + i;

                if (x < 0 || x >= seats.length || y < 0 || y >= seats[0].length) {
                    adjacentSeats[7] = '.';
                    flag++;
                } else if (seats[x][y] != '.') {
                    adjacentSeats[7] = seats[x][y];
                    flag++;
                }
            }
        }

        return adjacentSeats;
    }


    static char[] getAdjacentSeats(char[][] seats, int xValue, int yValue) {
        char[] adjacentSeats = new char[8];
        int index = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;

                int x = xValue + i;
                int y = yValue + j;

                if (x >= 0 && x < seats.length && y >= 0 && y < seats[0].length) {
                    adjacentSeats[index] = seats[x][y];
                    index++;
                }
            }
        }

        return adjacentSeats;
    }
}
