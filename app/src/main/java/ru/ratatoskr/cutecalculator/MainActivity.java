package ru.ratatoskr.cutecalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    protected LayoutInflater cuteInflater = getLayoutInflater();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //View button = cuteInflater.inflate(R.layout.button, null, false);
        //ConstraintLayout cuteWrapper = (ConstraintLayout) findViewById(R.id.linLayout);
    }

    private CuteButtonFactory bFactory;

    public class CuteButtonFactory implements LayoutInflater.Factory{
        @Nullable
        @Override
        public View onCreateView(@NonNull String s, @NonNull Context context, @NonNull AttributeSet attributeSet) {
            return null;
        }
    }
}

