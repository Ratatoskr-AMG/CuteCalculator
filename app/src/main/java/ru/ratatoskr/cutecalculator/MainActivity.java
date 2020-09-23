package ru.ratatoskr.cutecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public String TAG = "TOHA";
    HashMap<String, String> cuteButtons = getButtons();
    public Display cuteDisplayDir = new Display();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = getLayoutInflater();
        LinearLayout cuteWrapper = findViewById(R.id.cuteWrapper);
        TextView cuteDisplay = findViewById(R.id.cuteDisplay);
        TextView cuteHistory = findViewById(R.id.cuteHistory);

        /* inflate cute buttons*/
        for (Map.Entry<String, String> entry : cuteButtons.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            /*create cuteButton*/
            View cuteButton = inflater.inflate(R.layout.button, null, false);
            TextView cuteButtonText = cuteButton.findViewById(R.id.buttontext);
            cuteButtonText.setText(key);

            /*add cuteButton*/
            cuteButton.setTag(value);
            cuteWrapper.addView(cuteButton);
            cuteButton.setOnClickListener(this);

            /*Log*/
            //Log.v(TAG, "key = " + key);
            //Log.v(TAG, "value = " + value);


        }

        cuteDisplayDir.show(cuteDisplay, cuteHistory);
    }
    /* returns buttons HasMap*/

    public HashMap getButtons() {

        HashMap<String, String> cuteButtons = new HashMap<>();

        cuteButtons.put("7", "operand");
        cuteButtons.put("8", "operand");
        cuteButtons.put("9", "operand");
        cuteButtons.put("4", "operand");
        cuteButtons.put("5", "operand");
        cuteButtons.put("6", "operand");
        cuteButtons.put("1", "operand");
        cuteButtons.put("2", "operand");
        cuteButtons.put("3", "operand");
        cuteButtons.put("0", "operand");
        cuteButtons.put("=", "result");
        cuteButtons.put("-", "minus");
        cuteButtons.put("+", "plus");
        cuteButtons.put("/", "divide");
        cuteButtons.put("*", "multi");
        cuteButtons.put(",", "divider");
        cuteButtons.put("C", "clear");

        return cuteButtons;

    }

    /*button click*/
    @Override
    public void onClick(View cuteButton) {

        /*current*/
        TextView cuteDisplay = findViewById(R.id.cuteDisplay);
        TextView cuteHistory = findViewById(R.id.cuteHistory);
        String current = cuteDisplay.getText().toString();


        /* input */
        String input = (String) cuteButton.getTag();

        switch (input) {

            case "result":
                cuteDisplayDir.calculate();
                cuteDisplayDir.setResult();
                break;

            case "clear":
                cuteDisplayDir.clear();
                break;

            case "multi":
                cuteDisplayDir.operationClick(input);
                break;

            case "plus":
                cuteDisplayDir.operationClick(input);
                break;

            case "minus":
                cuteDisplayDir.operationClick(input);
                break;

            case "divide":
                cuteDisplayDir.operationClick(input);
                break;

            case "divider":
                cuteDisplayDir.divide();
                break;

            case "operand":
                TextView cuteButtonText = cuteButton.findViewById(R.id.buttontext);
                String operand = cuteButtonText.getText().toString();
                cuteDisplayDir.operandClick(operand);
                break;
        }

        cuteDisplayDir.show(cuteDisplay, cuteHistory);

        /*Log*/
        Log.v(TAG, "input:" + input);

    }


}

