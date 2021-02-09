package com.example.thenamequizapp.Classes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.thenamequizapp.DAO.PersonDao;

@Database(entities = {Person.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
    public static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "appDatabase").
                    fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }
}
