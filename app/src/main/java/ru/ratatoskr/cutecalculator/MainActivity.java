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

    HashMap<Integer,cuteOperand> cuteButtons = cuteCalculator.getButtons();
    public cuteDisplay cuteDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView historyTV = findViewById(R.id.historyTV);
        TextView inputTV = findViewById(R.id.inputTV);
        cuteDisplay = new cuteDisplay(historyTV,inputTV);

        /* inflate cute buttons*/
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout cuteWrapper = findViewById(R.id.cuteWrapper);
        for (Map.Entry<Integer,cuteOperand> entry : cuteButtons.entrySet()) {

            Integer cuteKey = entry.getKey();
            cuteOperand cuteOperand = entry.getValue();

            /*create cuteButton*/
            View cuteButton = inflater.inflate(R.layout.button, null, false);
            TextView cuteButtonText = cuteButton.findViewById(R.id.buttontext);
            cuteButtonText.setText(cuteOperand.getName());

            /*add cuteButton*/
            cuteButton.setTag(cuteOperand);
            cuteWrapper.addView(cuteButton);
            cuteButton.setOnClickListener(this);



        }
    }

    @Override
    public void onClick(View cuteButton) {

        TextView inputTV = findViewById(R.id.inputTV);
        cuteOperand input = (cuteOperand) cuteButton.getTag();
        cuteDisplay.add(input,inputTV);
        cuteDisplay.refresh();
    }


}

