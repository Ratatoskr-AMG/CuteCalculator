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


public class MainActivity extends AppCompatActivity {
    public String TAG = "TOHA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = getLayoutInflater();
        LinearLayout cuteWrapper = findViewById(R.id.wrapper);
        for (int i = 1; i < 10; i++) {

            View cuteButton = inflater.inflate(R.layout.button, cuteWrapper, false);
            TextView cuteButtonText = cuteButton.findViewById(R.id.buttontext);
            cuteButtonText.setText("asd" + i);
            cuteWrapper.addView(cuteButton);
        }

        /*
        ViewGroup.LayoutParams lp = cuteButton.getLayoutParams();
        Log.v(TAG, "Class of view: " + cuteButton.getClass().toString());
        Log.v(TAG, "LayoutParams of view is null: " + (lp == null));
        Log.v(TAG, "Text of view: " + ((TextView) cuteButton).getText());
        */
    }

}

