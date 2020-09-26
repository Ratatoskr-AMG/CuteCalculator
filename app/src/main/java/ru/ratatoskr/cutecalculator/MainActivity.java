package ru.ratatoskr.cutecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public cuteDisplay cuteDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView inputTV = findViewById(R.id.inputTV);
        LinearLayout buttonsLL = findViewById(R.id.buttonsLL);
        cuteDisplay = new cuteDisplay(inputTV,buttonsLL,this);
    }
}

