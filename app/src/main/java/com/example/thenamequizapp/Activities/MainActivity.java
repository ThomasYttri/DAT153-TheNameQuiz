package com.example.thenamequizapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.thenamequizapp.Classes.Database;
import com.example.thenamequizapp.Classes.Person;
import com.example.thenamequizapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    protected Map<String, Object> actions = new HashMap<>();

    Database persons = new Database();

    void prepareMenu() {
        addMenuItem("Quiz", QuizActivity.class);
        addMenuItem("Database", DatabaseActivity.class);
        addMenuItem("Add new person", NewPersonActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareMenu();
        String[] keys = actions.keySet().toArray(new String[actions.keySet().size()]);

        ListView av = (ListView) findViewById(R.id.menu_list);
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, keys);

        av.setAdapter(adapter);
        av.setOnItemClickListener((parent, view, position, id) -> {
            String key = (String) parent.getItemAtPosition(position);
            MainActivity.this.startActivity((Intent) actions.get(key));
        });
    }

    public void addMenuItem(String label, Class<?> cls) {
        actions.put(label, new Intent(this, cls));
    }

    public Database getDatabase() {
        return persons;
    }
}