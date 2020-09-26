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

    TextView inputTV;
    LinearLayout buttonsLL;
    String currentStr = "0";
    String currentHistory = "";
    boolean dot = false;
    boolean isWaitinforInput = true;
    cuteCalculator myCalculator;

    public cuteDisplay(TextView inputTV, LinearLayout buttonsLL, Context Context) {

        this.inputTV = inputTV;
        this.buttonsLL = buttonsLL;

        myCalculator = new cuteCalculator(inputTV);
        HashMap<Integer, cuteOperand> cuteButtons = cuteCalculator.getButtons();
        LayoutInflater inflater = (LayoutInflater) Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (Map.Entry<Integer, cuteOperand> entry : cuteButtons.entrySet()) {
            cuteOperand cuteOperand = entry.getValue();
            View cuteButton = inflater.inflate(R.layout.button, null, false);
            TextView cuteButtonText = cuteButton.findViewById(R.id.buttontext);
            cuteButtonText.setText(cuteOperand.getName());
            cuteButton.setTag(cuteOperand);
            this.buttonsLL.addView(cuteButton);
            cuteButton.setOnClickListener(this);
        }
        refresh();
    }

    public void receive(cuteOperand input, cuteCalculator calculator) {
        if (input.getValue() == 0 && input.getName() != "0" && input.getName() != ".") {
            String operationName = input.getName();
            switch (operationName) {
                case "C":
                    currentStr = convertDigitToString(0);
                    myCalculator.clear();
                    break;
                case "=":
                    currentStr = convertDigitToString(myCalculator.calculate());
                    myCalculator.readyState = true;
                    break;
                default:
                    if(myCalculator.savedOperation=="") {
                        myCalculator.setOperation(operationName);
                        myCalculator.saveX();
                    }else{
                        if(myCalculator.savedX!=0){
                            double currResult=myCalculator.calculate();
                            currentStr = convertDigitToString(currResult);
                            myCalculator.setOperation(operationName);
                            myCalculator.saveX(currResult);
                        }
                    }
                    myCalculator.readyState = true;
                    break;
            }
        } else {
            if (input.getName() == ".") {
                dot = true;
            } else {
                dot = false;
            }
            if (myCalculator.readyState) {
                if (input.getName() == ".") {
                    currentStr = "0.";
                } else {
                    currentStr = convertDigitToString(Double.parseDouble(input.getName()));
                }
                myCalculator.readyState = false;
            } else {
                CharSequence currInput = inputTV.getText();
                StringBuilder sb = new StringBuilder(currInput.length());
                sb.append(currInput);
                sb.append(input.getName());
                currentStr = convertDigitToString(Double.parseDouble(sb.toString()));
            }
            isWaitinforInput = false;
        }
    }

    @Override
    public void onClick(View cuteButton) {
        cuteOperand input = (cuteOperand) cuteButton.getTag();
        receive(input, myCalculator);
        refresh();
    }

    public String convertDigitToString(double operand) {
        String resultString;
        if (operand % 1 == 0) {
            Integer result = (int) operand;
            resultString = String.valueOf(result);
        } else {
            resultString = String.valueOf(operand);
        }
        if (dot)
            return resultString + ".";
        else
            return resultString;
    }

    public void refresh() {
        inputTV.setText(currentStr);
    }
}
