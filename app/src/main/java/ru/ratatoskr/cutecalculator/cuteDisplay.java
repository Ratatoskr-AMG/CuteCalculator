package ru.ratatoskr.cutecalculator;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class cuteDisplay implements View.OnClickListener {

    TextView historyTV;
    TextView inputTV;
    LinearLayout buttonsLL;
    double current = 0;
    boolean operandInputStartsNewOperand = true;
    boolean operationReceivedAtLast=false;
    LinkedList<cuteOperand> inputs = new LinkedList<>();
    LinkedList<Double> operands = new LinkedList<>();
    LinkedList<cuteOperand> operations = new LinkedList<>();

    public cuteDisplay(TextView historyTV, TextView inputTV, LinearLayout buttonsLL, Context Context) {

        this.historyTV = historyTV;
        this.inputTV = inputTV;
        this.buttonsLL = buttonsLL;

        /*construct keyboard*/
        HashMap<Integer, cuteOperand> cuteButtons = cuteCalculator.getButtons();
        LayoutInflater inflater = (LayoutInflater) Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (Map.Entry<Integer, cuteOperand> entry : cuteButtons.entrySet()) {

            Integer cuteKey = entry.getKey();
            cuteOperand cuteOperand = entry.getValue();

            /*create cuteButton*/
            View cuteButton = inflater.inflate(R.layout.button, null, false);
            TextView cuteButtonText = cuteButton.findViewById(R.id.buttontext);
            cuteButtonText.setText(cuteOperand.getName());

            /*add cuteButton*/
            cuteButton.setTag(cuteOperand);
            this.buttonsLL.addView(cuteButton);
            cuteButton.setOnClickListener(this);

        }

        refresh();

    }

    public void add(cuteOperand input) {

        /*введена не цифра и не точка*/
        if (input.getValue() == 0 && input.getName() != "0" && input.getName()!=".") {
            /*операция*/
            operationReceivedAtLast=false;
            switch (input.getName()) {
                case "C":
                    operands.clear();
                    operations.clear();
                    current = 0;
                    break;
                case "=":
                    operands.addLast(current);
                    current = cuteCalculator.calculate(operands, operations);
                    break;
                default:
                    operandInputStartsNewOperand = true;
                    if(operationReceivedAtLast){
                        operations.removeLast();
                        operations.addLast(input);
                    }else{
                        operands.addLast(current);
                        operations.addLast(input);
                        current = cuteCalculator.calculate(operands, operations);
                        operands.addLast(current);
                        operationReceivedAtLast = true;
                    }
                    break;
            }

        }
        /*введена цифра*/
        else {
            operationReceivedAtLast = false;
            CharSequence currInput = inputTV.getText();
            StringBuilder sb = new StringBuilder(currInput.length());
            sb.append(currInput);
            sb.append(input.getName());
            if (operandInputStartsNewOperand == true) {
                current = input.getValue();
                operandInputStartsNewOperand = false;
            } else {

                String curr = convertDigitToString(current);
                String add = convertDigitToString(input.getValue());

                StringBuilder currentString = new StringBuilder();
                currentString.append(curr);
                currentString.append(add);
                current = Double.parseDouble(currentString.toString());

            }

            //

        }

    }

    @Override
    public String toString() {
        return "cuteDisplay{" + "current='" + current + '}';
    }

    /*Убираем точку и нули, если число - целое*/
    public String convertDigitToString(double operand) {
        String resultString;
        if (operand % 1 == 0) {
            Integer result = (int) operand;
            resultString = String.valueOf(result);
        } else {
            resultString = String.valueOf(operand);
        }
        return resultString;
    }

    //Собираем историю операции + операнды
    public String historyToString() {
        LinkedList<Double> operands_c = (LinkedList) operands.clone();
        LinkedList<cuteOperand> operations_c = (LinkedList) operations.clone();

        Log.v("CUTE","operands_c.size()="+operands_c.size());
        Log.v("CUTE","operations_c.size()="+operations_c.size());

        if (operands_c.size() == 0) {
            return "";
        }else{
            StringBuilder history = new StringBuilder();
            if(operations_c.size() == 0){
                double x = operands_c.peekFirst();
                Log.v("CUTE","history.append: "+x);
                history.append(x);
            }else{
                for(int i = 0; i < operands_c.size(); i++) {
                    double operand = operands_c.peekFirst();
                    cuteOperand operation = operations_c.peekFirst();
                    operands_c.pollFirst();
                    operations_c.pollFirst();
                    history.append(convertDigitToString(operand));
                    history.append(operation.getName());
                    Log.v("CUTE"," history.append(convertDigitToString(operand)): "+ convertDigitToString(operand));
                    Log.v("CUTE","h history.append(operation.getName()):  "+operation.getName());
                }
            }
            String result = history.toString();
            return result;
        }

        /*
        if (operands_c.size() == 1) {
            double hOperand = operands_c.peekLast();
            String hOperation = String.valueOf(operations_c.peekFirst().getName());
            result = convertDigitToString(hOperand) + hOperation;

        } else {

            StringBuilder historyBuilder = new StringBuilder();

            while (operands_c.size() > 0) {

                cuteOperand cOperation = operations_c.peekFirst();
                operands_c.pollFirst();
                operations_c.pollFirst();

                historyBuilder.append(convertDigitToString(x));
                historyBuilder.append(cOperation.getName());

            }

            result = historyBuilder.toString();
            }
          */
        /*StringBuilder historyString = new StringBuilder();

        Iterator iterator = history.iterator();

        while (iterator.hasNext()) {

            Object value = iterator.next();
            cuteOperand cuteOperand = (cuteOperand) value;
            String name = cuteOperand.getName();
            historyString.append(name);

        }

        return historyString.toString();
        */

    }

    @Override
    public void onClick(View cuteButton) {
        cuteOperand input = (cuteOperand) cuteButton.getTag();
        add(input);
        refresh();
    }

    public void refresh() {

        historyTV.setText(historyToString());
        inputTV.setText(convertDigitToString(current));

    }
}
