package ru.ratatoskr.cutecalculator;

import android.util.Log;
import android.widget.TextView;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class cuteDisplay {

    LinkedList<cuteOperand> history = new LinkedList<>();
    LinkedList<Double> operands = new LinkedList<>();
    LinkedList<cuteOperand> operations = new LinkedList<>();
    boolean lastWasDivider = false;

    public double current = 0;
    public Integer nextOperandStartNewOperand = 1;

    TextView historyTV;
    TextView inputTV;

    public cuteDisplay(TextView historyTV, TextView inputTV) {

        this.historyTV = historyTV;
        this.inputTV = inputTV;

    }

    public void add(cuteOperand input, TextView form) {

        /*берем число с экрана, конвертим в дабл*/
        CharSequence formcurrent = form.getText();
        StringBuilder sb = new StringBuilder(formcurrent.length());
        sb.append(formcurrent);


        if (input.getValue() == 0 && input.getName() != "0") {

            current = Double.parseDouble(sb.toString());

            /*операция*/
            switch (input.getName()) {
                case ".":
                    lastWasDivider=true;
                    break;
                case "C":
                    lastWasDivider=false;
                    operands.clear();
                    operations.clear();
                    current = 0;
                    nextOperandStartNewOperand = 1;
                    break;
                case "=":
                    lastWasDivider=false;
                    if(operands.size()>1) {
                        current = cuteCalculator.calculate(operands, operations);
                        nextOperandStartNewOperand = 1;
                    }
                    break;
                default:
                    operands.addLast(current);
                    operations.addLast(input);
                    current = cuteCalculator.calculate(operands, operations);
                    lastWasDivider=false;
                    nextOperandStartNewOperand = 1;
                    break;
            }


/*
            if(history.peek()!=input){
                if(history.peek().getType()=="operation"){
                    history.pollLast();
                }
                history.push(input);
            }
*/

        } else {
            lastWasDivider=false;
            /*операнд*/
            if (nextOperandStartNewOperand == 1) {
                current = input.getValue();
                nextOperandStartNewOperand = 0;
            } else {

                String curr = convertToString(current, "");
                String add = convertToString(input.getValue(), "");

                StringBuilder currentString = new StringBuilder();
                currentString.append(curr);
                if (lastWasDivider)
                    currentString.append(".");
                currentString.append(add);
                current = Double.parseDouble(currentString.toString());


                //Log.v("CUTE","current : input.getValue() : "+current+input.getValue());
                //Log.v("CUTE","curr: add : "+curr+add);
                //Log.v("CUTE","newC: "+newC);
            }

            //

        }

        Log.v("CUTE", "cuteDisplay:add:input:" + input);
    }

    public void refresh() {

        historyTV.setText(historyString());
        inputTV.setText(inputString());

    }

    @Override
    public String toString() {
        return "cuteDisplay{" + "current='" + current + '}';
    }

    /*печатаем current (то, что должно быть на экране)*/
    public String inputString() {

        if (history.size() > 0) {
            cuteOperand historylast = history.peekLast();
            String last = historylast.getName();
            return convertToString(current, last);
        }

        return convertToString(current, "");
    }

    public String convertToString(double operand, String last) {

        String resultString;

        if (operand % 1 == 0) {
            Integer result = (int) operand;
            resultString = String.valueOf(result);
        } else {
            resultString = String.valueOf(operand);
        }

        if (last == ".") {
            return resultString + ".";
        }

        return resultString;

    }

    public String historyString() {

        LinkedList<Double> hOperands =  (LinkedList)operands.clone();
        LinkedList<cuteOperand> hOperations = (LinkedList)operations.clone();

        String result;

        if (hOperands.size() == 0){

            return "";

        }
        if (hOperands.size() == 1) {
            double hOperand = hOperands.peekLast();
            String hOperation = String.valueOf(hOperations.peekFirst().getName());
            result = convertToString(hOperand,"") + hOperation;

        } else {

            StringBuilder historyBuilder = new StringBuilder();

            while (hOperands.size() > 0) {
                double x = hOperands.peekFirst();
                cuteOperand cOperation = hOperations.peekFirst();
                hOperands.pollFirst();
                hOperations.pollFirst();

                historyBuilder.append(convertToString(x,""));
                historyBuilder.append(cOperation.getName());

            }

            result = historyBuilder.toString();

        }

        return result;

        /*StringBuilder historyString = new StringBuilder();

        Iterator iterator = history.iterator();
        Log.v("CUTE", "cuteHistory:toString:history:size:" + history.size());

        while (iterator.hasNext()) {

            Object value = iterator.next();
            cuteOperand cuteOperand = (cuteOperand) value;
            String name = cuteOperand.getName();
            historyString.append(name);
            //Log.v("CUTE", "toString:cuteOperand:getName:" + name);

        }

        return historyString.toString();
        */

    }

}
