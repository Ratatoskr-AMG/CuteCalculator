package ru.ratatoskr.cutecalculator;

import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class cuteCalculator {

    static boolean readyState = true;
    public String current = "0";
    public double savedX = 0;
    public String savedOperation = "";
    public TextView inputTV;

    public void setOperation(String input) {
        savedOperation=input;
    }

    public cuteCalculator(TextView inputTV) {
        this.inputTV = inputTV;
    }

    public void clear(){
        readyState = true;
        current = "0";
        savedX = 0;
        savedOperation = "";
    }

    public void saveX() {
        savedX = getValueFromDisplay();
    }

    public void saveX(double value) {
        savedX = value;
    }

    public double getValueFromDisplay(){
        CharSequence currInput = inputTV.getText();
        StringBuilder inputed = new StringBuilder(currInput.length());
        inputed.append(currInput);
        return Double.parseDouble(currInput.toString());
    }

    public double calculate() {
        double result;
        double current = getValueFromDisplay();
        if(savedOperation != ""){
            switch(savedOperation){
                case "-":
                    result=savedX-current;
                    break;
                case "+":
                    result=savedX+current;
                    break;
                case "*":
                    result=savedX*current;
                    break;
                case "/":
                    result=savedX/current;
                    break;
                default:
                    result=0;
            }
        }else{
            return current;
        }
        savedOperation="";
        return result;
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

}
