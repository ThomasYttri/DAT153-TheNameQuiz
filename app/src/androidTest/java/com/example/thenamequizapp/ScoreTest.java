package com.example.thenamequizapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.thenamequizapp.Activities.QuizActivity;
import com.example.thenamequizapp.Classes.AppDatabase;
import com.example.thenamequizapp.Classes.Person;
import com.example.thenamequizapp.DAO.PersonDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class ScoreTest {
    private AppDatabase appDatabase;

    @Rule
    public ActivityScenarioRule<QuizActivity> activityRule =
            new ActivityScenarioRule<QuizActivity>(QuizActivity.class);

    @Before
    public void beforeQuizTest(){
        Context context = ApplicationProvider.getApplicationContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();

        appDatabase.personDao().addPerson(new Person("Jon", ContextCompat.getDrawable(context, R.drawable.jon)));
        appDatabase.personDao().addPerson(new Person("Bendik", ContextCompat.getDrawable(context, R.drawable.bendik)));
        appDatabase.personDao().addPerson(new Person("Morten", ContextCompat.getDrawable(context, R.drawable.morten)));
    }


    @Test
    public void ScoreCountTest(){

    }

    @After
    public void closeDatabase() throws IOException {
        appDatabase.close();
    }

}
