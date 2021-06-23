package AdventOfCode2020;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class D8 {
    public static void main(String[] args) {
        ArrayList<Instruction> bootCode = new ArrayList<>();
        ArrayList<Instruction> copy = new ArrayList<>();

        String line, operation, arg;
        int acc = 0;
        try {
            FileInputStream puzzleInput = new FileInputStream("./TestCases/D8.txt");
            Scanner myReader = new Scanner(puzzleInput);
            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                operation = line.substring(0, 3);
                arg = line.substring(4);
                bootCode.add(new Instruction(operation, arg, 0));
                copy.add(new Instruction(operation, arg, 0));
            }


            //get the acc before running an instruction for the second time
            // allow each instruction to run at most 2 times, to find all the instructions in the loop
            acc = runInstruct(bootCode, bootCode.get(0), acc, 2);
            System.out.println(acc);

            //todo: maybe find a more elegant way to fix the instruction lol

            // remove the loop by changing one instruction
//            fixInstruction(bootCode);

            // finding the instruction to fix with brute force
            // aka change every instruction in loop once
            for (int i = 0; i < bootCode.size(); i++) {
                acc = 0;
                Instruction inst = bootCode.get(i);
                if (inst.runTimes == 2 && inst.operation.compareTo("jmp") == 0) {
                    copy.get(i).operation = "nop";
                    System.out.println(runInstruct(copy, copy.get(0), acc, 1));
                    for (Instruction ugh : copy) {
                        ugh.runTimes = 0;
                    }
                    copy.get(i).operation = "jmp";
                } else if (inst.runTimes == 2 && inst.operation.compareTo("nop") == 0) {
                    copy.get(i).operation = "jmp";
                    System.out.println(runInstruct(copy, copy.get(0), acc, 1));
                    for (Instruction ugh : copy) {
                        ugh.runTimes = 0;
                    }
                    copy.get(i).operation = "nop";
                }
            }

            // run the instructions again to get the correct acc
//            acc = 0;
//            acc = runInstruct(bootCode, bootCode.get(0), acc, 1);
//
//            System.out.println(acc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void fixInstruction(ArrayList<Instruction> bootCode) {
        // change the jmp instruction in the loop with greatest index to nop
        int ans = 0;
        for (int i = 0; i < bootCode.size(); i++) {
            Instruction inst = bootCode.get(i);
//            if (inst.runTimes == 2 && inst.operation.compareTo("jmp") == 0) {
//                if (i > ans) ans = i;
//            }
            if (inst.runTimes == 2 && inst.operation.compareTo("jmp") == 0) {
                if (i > ans) ans = i;
            }
            inst.runTimes = 0;
        }
        bootCode.get(ans).operation = "nop";
    }

    static int runInstruct (ArrayList<Instruction> bootCode, Instruction inst, int acc, int option) {
        /*
        option: the max num of times an instruction is allowed to run
         */
        if (inst.runTimes == option) {
            System.out.println("Error: entered loop");
            return acc;
        }
        inst.runTimes++;

        // update accumulator if needed
        if (inst.operation.compareTo("acc") == 0) {
            int num = Integer.parseInt(inst.argument.substring(1));
            if (inst.argument.charAt(0) == '+') acc += num;
            else acc -= num;
        }

        // proceed with the instructions
        int curIndex = bootCode.indexOf(inst);

        int toIndex = curIndex;
        if (inst.operation.compareTo("jmp") == 0) {
            int num = Integer.parseInt(inst.argument.substring(1));
            if (inst.argument.charAt(0) == '+') toIndex += num;
            else toIndex -= num;
        } else {
            toIndex++;
        }
        if (toIndex >= bootCode.size()) {
            System.out.println("Success: reached the end of the instruction");
            return acc;
        }
        acc = runInstruct(bootCode, bootCode.get(toIndex), acc, option);

        return acc;
    }

    static class Instruction {
        //field
        String operation;
        String argument;
        int runTimes;

        //constructor
        Instruction(String op, String arg, int times) {
            operation = op;
            argument = arg;
            runTimes = times;
        }
    }
}
