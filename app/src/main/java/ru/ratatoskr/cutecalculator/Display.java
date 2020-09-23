package ru.ratatoskr.cutecalculator;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Display {

    protected String current = "0";
    protected float x = 0;
    protected float y = 0;
    protected String currOperation = "multi";

    public void operandClick(String o) {

        if (current == "0") {

            current = o;
            y=1;

        } else {

            StringBuilder builder = new StringBuilder();
            builder.append(current);
            builder.append(o);
            current = builder.toString();

        }

    }

    public void operationClick(String operation){

        currOperation = operation;
        getResult();
    }

    public void getResult(){

        float number = 0;

        switch(currOperation){
            case "multi":
                number = x*y;
                break;
            case "plus":
                number = x+y;
                break;
            case "minus":
                number = x-y;
                break;
            case "divide":
                number = x/y;
                break;
        }

        current=String.valueOf(number);

    }


    public void show(TextView display) {

        display.setText(current);
        Log.v("TOHA", "setted");

    }

    ;
}
