package com.example.thenamequizapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.thenamequizapp.Activities.DatabaseActivity;
import com.example.thenamequizapp.Classes.AppDatabase;
import com.example.thenamequizapp.Classes.Person;
import com.example.thenamequizapp.DAO.PersonDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DatabaseEntryTest {

    private AppDatabase appDatabase;
    private PersonDao personDao;
    private Context context;

    @Rule
    public ActivityScenarioRule<DatabaseActivity> activityRule =
            new ActivityScenarioRule<DatabaseActivity>(DatabaseActivity.class);

    @Before
    public void beforeEntryTest(){
        context = ApplicationProvider.getApplicationContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        personDao = appDatabase.getPersonDao();
    }

    @Test
    public void entryTest(){
        /*
        Person jon = new Person("Jon", ContextCompat.getDrawable(context, R.drawable.jon));
        personDao.addPerson(jon);
        List<Person> persons = personDao.getAll();
        assertThat(persons.get(persons.size() - 1), equalTo(jon));

         */
    }

    @After
    public void afterEntryTest(){
        appDatabase.close();
    }

}
