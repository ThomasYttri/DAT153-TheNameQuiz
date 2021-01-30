package com.example.thenamequizapp.Classes;

import android.app.Application;

import com.example.thenamequizapp.R;

import java.util.ArrayList;

public class Database extends Application {
    private ArrayList<Person> database;

    @Override
    public void onCreate() {
        super.onCreate();
        database.add(new Person("Bendik", getDrawable(R.drawable.bendik)));
        database.add(new Person("Jon", getDrawable(R.drawable.jon)));
        database.add(new Person("Morten", getDrawable(R.drawable.morten)));
    }

    public Database (){
        database = new ArrayList<>();
    }
    public ArrayList<Person> getDatabase() {
        return database;
    }

    public void addPerson(Person person) {
        this.database.add(person);
    }
}
