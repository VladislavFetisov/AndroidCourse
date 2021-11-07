package com.example.lab4_1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private boolean buttonWasClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.changeTextButton).setOnClickListener(v -> {
            if (!buttonWasClicked) {
                Button b = findViewById(R.id.changeTextButton);
                b.setText(R.string.new_text);
                buttonWasClicked = true;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}