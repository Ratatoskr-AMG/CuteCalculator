package ru.ratatoskr.cutecalculator;

import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class cuteCalculator {

    public String current = "0";

    static double calculate(LinkedList<Double> operands, LinkedList<cuteOperand> operations) {

        LinkedList<Double> operandsState = (LinkedList)operands.clone();
        LinkedList<cuteOperand> operationsState = (LinkedList)operations.clone();

        //Iterator iterator = operands.iterator();
        Log.v("CUTE", "operandsState:size:" + operandsState.size());
        Log.v("CUTE", "operationsState:size:" + operationsState.size());

        double x;
        double y;

        if (operandsState.size() == 1) {

            return operandsState.peekLast();

        }

        if (operandsState.size() > 1) {

            x = operandsState.peekFirst();
            operandsState.pollFirst();

            while (operandsState.size() > 0) {

                cuteOperand curr = operationsState.peekFirst();
                operationsState.pollFirst();

                y = operandsState.peekFirst();
                operandsState.pollFirst();

                switch (curr.getName()) {
                    case "+":
                        x = x + y;
                        break;
                    case "-":
                        x = x - y;
                        break;
                    case "*":
                        x = x * y;
                        break;
                    case "/":
                        x = x / y;
                        break;
                }


            }

            Log.v("CUTE", "operandsState:size:" + operands.size());
            Log.v("CUTE", "operationsState:size:" + operations.size());

            return x;

        }


        return 0;

    }

    static HashMap getButtons() {

        HashMap<Integer,cuteOperand> cuteButtons = new HashMap<>();

        cuteButtons.put(0, new cuteOperand("0",0));
        cuteButtons.put(1, new cuteOperand("1",1));
        cuteButtons.put(2, new cuteOperand("2",2));
        cuteButtons.put(3, new cuteOperand("3",3));
        cuteButtons.put(4, new cuteOperand("4",4));
        cuteButtons.put(5, new cuteOperand("5",5));
        cuteButtons.put(6, new cuteOperand("6",6));
        cuteButtons.put(7, new cuteOperand("7",7));
        cuteButtons.put(8, new cuteOperand("8",8));
        cuteButtons.put(9, new cuteOperand("9",9));
        cuteButtons.put(10, new cuteOperand("C"));
        cuteButtons.put(11, new cuteOperand("="));
        cuteButtons.put(12, new cuteOperand("-"));
        cuteButtons.put(13, new cuteOperand("+"));
        cuteButtons.put(14, new cuteOperand("/"));
        cuteButtons.put(15, new cuteOperand("*"));
        cuteButtons.put(16, new cuteOperand("."));

        return cuteButtons;

    }

    @Override
    public String toString() {
        return "cuteCalculator{" + "current='" + current + '}';
    }

}
