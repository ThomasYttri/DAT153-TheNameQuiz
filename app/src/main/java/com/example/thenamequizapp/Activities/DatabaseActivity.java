package com.example.thenamequizapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thenamequizapp.Adapters.DatabaseAdapter;
import com.example.thenamequizapp.R;

import java.util.ArrayList;

import com.example.thenamequizapp.Classes.Database;
import com.example.thenamequizapp.Classes.Person;

public class DatabaseActivity extends AppCompatActivity {

    private ListView listView;

    private Database database = new Database();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        listView = findViewById(R.id.dbList);

        ArrayList<Person> persons = database.getDatabase();

        DatabaseAdapter databaseAdapter = new DatabaseAdapter(this, R.layout.row_item, persons);

        listView.setAdapter(databaseAdapter);

    }

    public void addNewPerson(View view) {
        Intent i = new Intent(this, NewPersonActivity.class);
        startActivity(i);
    }
}