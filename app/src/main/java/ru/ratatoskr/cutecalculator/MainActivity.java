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


public class MainActivity extends AppCompatActivity {

    public cuteDisplay cuteDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView historyTV = findViewById(R.id.historyTV);
        TextView inputTV = findViewById(R.id.inputTV);
        LinearLayout buttonsLL = findViewById(R.id.cuteWrapper);

        cuteDisplay = new cuteDisplay(historyTV,inputTV,buttonsLL,this);

    }

}

