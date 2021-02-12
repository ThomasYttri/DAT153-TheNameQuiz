package com.example.thenamequizapp;

import android.content.Context;

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

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DatabaseTest {

    private AppDatabase appDatabase;
    private PersonDao personDao;
    private Context context;
    private Person jon;

    @Rule
    public ActivityScenarioRule<DatabaseActivity> activityRule =
            new ActivityScenarioRule<DatabaseActivity>(DatabaseActivity.class);

    @Before
    public void beforeEntryTest(){
        context = ApplicationProvider.getApplicationContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        personDao = appDatabase.getPersonDao();

        // Create mister Jon
        jon = new Person("Jon", ContextCompat.getDrawable(context, R.drawable.jon));

        // Add Jon to the database
        personDao.addPerson(jon);
    }

    @After
    public void afterEntryTest(){
        appDatabase.close();
    }

    @Test
    public void entryTest() {
        // Assert that mister Jon was added to the database
        assertThat(personDao.getAll().size(), equalTo(1));
    }

    @Test
    public void removalTest() {
        // Delete Jon from the database
        personDao.deletePerson(jon);

        // Assert that Jon was removed from the database
        assertThat(personDao.getAll().size(), equalTo(1));
    }

}
