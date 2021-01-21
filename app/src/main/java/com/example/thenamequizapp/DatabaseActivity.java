package com.example.thenamequizapp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class DatabaseActivity extends AppCompatActivity {

    private ListView listView;
    private String names[] = {"Jon", "Morten", "Bendik"};
    private Integer images[] = {R.drawable.};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);




    }

//    HashMap database = new HashMap();
//    addPerson("Jon", "jon");
    /*
    private void addPerson(String name, String img){
        int key = 1;

        if(name != null) {
            database.put(key, name);
            key++;
        } else {
            System.out.println("Invalid name");
        }
    }

     */

}