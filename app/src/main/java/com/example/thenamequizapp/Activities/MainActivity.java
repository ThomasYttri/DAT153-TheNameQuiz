package com.example.thenamequizapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.thenamequizapp.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void quiz(View view) {
        Intent i = new Intent(this, QuizActivity.class);
        startActivity(i);
    }

    public void database(View view) {
        Intent i = new Intent(this, DatabaseActivity.class);
        startActivity(i);
    }

    public void newPerson(View view) {
        Intent i = new Intent(this, QuizActivity.class);
        startActivity(i);
    }

}