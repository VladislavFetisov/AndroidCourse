package com.example.lab4_2.sub_task_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab4_2.ActivityAbout;
import com.example.lab4_2.R;

public class Activity4_2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        findViewById(R.id.bnToThird).setOnClickListener(v -> {
            Intent intent = new Intent(Activity4_2.this, Activity4_3.class);
            startActivity(intent);
        });


        findViewById(R.id.bnToFirst).setOnClickListener(v -> finish());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.go_to_about) {
            Intent intent = new Intent(Activity4_2.this, ActivityAbout.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}