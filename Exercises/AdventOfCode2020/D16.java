package AdventOfCode2020;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class D16 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream puzzleInput = new FileInputStream("./testcases/D16.txt");
        Scanner myReader = new Scanner(puzzleInput);

        HashMap<String, ArrayList<Integer>> officialValues = new HashMap<>();

        // get the value ranges from the fields
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            if (line.length() == 0) break;
            String field = line.split(": ")[0];
            String[] ranges = line.split(": ")[1].split(" or ");
            getOfficialValues(officialValues, field, ranges);
        }

        // get "Your tickets"
        myReader.nextLine();
        String myLine = myReader.nextLine();
        String[] myTicket = myLine.split(",");
        myReader.nextLine();
        myReader.nextLine();

        // get the nearby tickets and find invalid tickets
        ArrayList<int[]> validTickets = new ArrayList<>();
        int errorRate = 0;
        while (myReader.hasNextLine()){
            String line = myReader.nextLine();
            String[] nearbyValues = line.split(",");
            errorRate += getErrorRate(officialValues, nearbyValues, validTickets);
        }
        System.out.println(errorRate);

        HashMap<Integer, ArrayList<String>> fieldOrders = findFieldOrders(officialValues, validTickets);
        long ans = getDeparturesMultiplied(fieldOrders, myTicket);

        System.out.println(ans);
    }


    static long getDeparturesMultiplied(HashMap<Integer, ArrayList<String>> fieldOrders, String[] myTicket) {
        long ans = 1;
        int count = 0;
        for (int i = 0; i < myTicket.length; i++) {
            if (fieldOrders.get(i).get(0).contains("departure")) {
                ans *= Integer.parseInt(myTicket[i]);
                count++;
            }
        }
        System.out.println(count);
        return ans;
    }


    static HashMap<Integer, ArrayList<String>> findFieldOrders(HashMap<String, ArrayList<Integer>> officialValues, ArrayList<int[]> validTickets) {
        // find the order of the fields
        // first go though all the tickets
        // then deduct the non-unique ones with the help of determined orders

        HashMap<Integer, ArrayList<String>> fieldOrders = new HashMap<>();
        ArrayList<String> determined = new ArrayList<>(); // AL of the fields that have their orders determined
        boolean isFirst = true;

        // first go through all the tickets
        for (int[] ticket : validTickets) {
            for (int i = 0; i < ticket.length; i++) {
                int value = ticket[i];
                ArrayList<String> foundFields = determineFields(officialValues, value);
                if (isFirst) {
                    fieldOrders.put(i, foundFields);
                } else {
                    ArrayList<String> curList = fieldOrders.get(i);
                    curList.removeIf(existingField -> !foundFields.contains(existingField));
                    if (curList.size() == 1) {
                        String uniqueField = curList.get(0);
                        if (! determined.contains(uniqueField)) determined.add(uniqueField);
                    }
                }
            }
            isFirst = false;
        }

        // then go through the Hashmap repeatedly until all index has one unique field
        boolean allUnique = false;
        while (! allUnique) {
            allUnique = true;
            for (Map.Entry<Integer, ArrayList<String>> entry : fieldOrders.entrySet()) {
                ArrayList<String> fields = entry.getValue();
                if (fields.size() != 1) {
                    allUnique = false;
                    fields.removeAll(determined);
                    if (fields.size() == 1) {
                        determined.addAll(fields);
                    }
                }
            }
        }

        return fieldOrders;
    }


    static ArrayList<String> determineFields (HashMap<String, ArrayList<Integer>> officialValues, int value) {
        // return the unique field of the value if exists
        // else return empty string ""

        ArrayList<String> fields = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Integer>> entry : officialValues.entrySet()) {
            ArrayList<Integer> numbers = entry.getValue();
            if (numbers.contains(value)) {
                fields.add(entry.getKey());
            }
        }

        return fields;
    }

    static int getErrorRate(HashMap<String, ArrayList<Integer>> officialValues, String[] nearbyValues, ArrayList<int[]> validTickets) {
        // while getting the error rate, also build up the list of valid tickets
        int sum = 0;
        boolean isValid = true;
        int[] temp = new int[nearbyValues.length];
        boolean isError;

        for (int i = 0; i < nearbyValues.length; i++) {
            isError = true;
            String value = nearbyValues[i];
            int number = Integer.parseInt(value);
            temp[i] = number;

            // go into the AL to see if the value is valid
            for (Map.Entry<String, ArrayList<Integer>> entry : officialValues.entrySet()) {
                ArrayList<Integer> values = entry.getValue();
                if (values.contains(number)) {
                    isError = false;
                    break;
                }
            }

            if (isError) {
                sum += number;
                isValid = false;
            }
        }

        if (isValid) validTickets.add(temp);
        return sum;
    }

    static void getOfficialValues(HashMap<String, ArrayList<Integer>> officialValues, String field, String[] ranges) {
        ArrayList<Integer> values = new ArrayList<>();

        for (String range: ranges) {
            String[] split = range.split("-");
            int low = Integer.parseInt(split[0]);
            int high = Integer.parseInt(split[1]);
            for (int i = low; i <= high; i++) {
                values.add(i);
            }
        }

        officialValues.put(field, values);
    }
}
