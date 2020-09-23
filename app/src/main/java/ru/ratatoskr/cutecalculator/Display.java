package ru.ratatoskr.cutecalculator;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Display {

    protected String currentD = "0";
    protected String currentH = "";
    protected float x;
    protected float y;
    protected String currentX = "NULL";
    protected String currentY = "NULL";
    protected String currOperation = "NULL";
    protected String inputState = "WAIT";

    public void clear() {

        currentD = "0";
        currentH = "";
        x = 0;
        y = 0;
        currentX = "NULL";
        currentY = "NULL";
        currOperation = "NULL";

    }

    public void operandClick(String o) {

        if (currentD == "0" || inputState == "WAIT") {

            currentD = o;


        } else {

            if (currOperation != "NULL" && currentX != "NULL" && currentY == "NULL") {
                currentD = o;
                currentY = "NOTNULL";
            } else {

                StringBuilder currentBuilder = new StringBuilder();
                currentBuilder.append(currentD);
                currentBuilder.append(o);
                currentD = currentBuilder.toString();
            }

        }

        inputState = "HOLD";

    }

    public void operationClick(String o) {

        StringBuilder historyBuilder = new StringBuilder();
        currOperation = o;

        if (currentH != "") {
            historyBuilder.append(currentH);
            if (inputState == "WAIT") {
                historyBuilder.append(currentD);
                historyBuilder.setLength(historyBuilder.length() - 3);
            }
        }

        switch (o) {
            case "multi":
                historyBuilder.append(" * ");
                break;
            case "plus":
                historyBuilder.append(" + ");
                break;
            case "minus":
                historyBuilder.append(" - ");
                break;
            case "divide":
                historyBuilder.append(" / ");
                break;
            default:
                historyBuilder.append(" Unsupported operation " + currOperation);
                break;
        }

        if (inputState != "WAIT") {

            if (currentX != "NULL" && currentY == "NULL") {
                y = Float.parseFloat(currentD);
                currentY = "NOTNULL";
                currOperation = o;
                calculate();
                setResult();
            }

            if (currentX == "NULL" && currentY == "NULL") {
                x = Float.parseFloat(currentD);
                currentX = "NOTNULL";
            }

        }

        currentH = historyBuilder.toString();
        inputState = "WAIT";

    }

    public void divide() {
        int isFloat = currentD.indexOf(".");
        Log.v("TOHA", Integer.toString(isFloat));

        if (isFloat < 0) {
            StringBuilder historyBuilder = new StringBuilder();
            historyBuilder.append(currentD);
            historyBuilder.append(".");
            currentD = historyBuilder.toString();
        }
    }

    public Float calculate() {

        float calcValue = 0;

        switch (currOperation) {
            case "multi":
                calcValue = x * y;
                Log.v("TOHA:", x + " * " + y);
                break;
            case "plus":
                calcValue = x + y;
                Log.v("TOHA:", x + " + " + y);
                break;
            case "minus":
                calcValue = x - y;
                Log.v("TOHA:", x + " - " + y);
                break;
            case "divide":
                calcValue = x / y;
                Log.v("TOHA:", x + " / " + y);
                break;
            default:
                Log.v("TOHA:", "Unsupported operation");
                break;
        }

        x = calcValue;
        y = 0;
        currentY = "NULL";

        Log.v("TOHA:", " calcValue: " + calcValue);
        return calcValue;
    }

    public void setResult() {

        float calcValue = calculate();

        if (calcValue % 1 == 0) {
            Integer result = (int) calcValue;
            currentD = String.valueOf(result);
        }

        currentD = String.valueOf(calcValue);

    }

    public void show(TextView display, TextView history) {

        display.setText(currentD);
        history.setText(currentH);

    }

}
