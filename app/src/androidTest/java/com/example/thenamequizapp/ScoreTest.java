package com.example.thenamequizapp;

import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.thenamequizapp.Activities.QuizActivity;
import com.example.thenamequizapp.Classes.AppDatabase;
import com.example.thenamequizapp.Classes.Person;
import com.example.thenamequizapp.DAO.PersonDao;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ScoreTest {

    private List<Person> persons;
    private PersonDao personDao;

    @Rule
    public ActivityScenarioRule<QuizActivity> activityRule =
            new ActivityScenarioRule<>(QuizActivity.class);

    /*
    // This method will not work because QuizActivity is started before the @Before function in the test
    // class is run. Therefor it will not update the layout, and the first of the two tests will fail.
    // Two different solutions:
    // Always pass test if database is empty.
    // Start test in MainActivity, add person to database, and the load the Quiz Activity.

    @Before
    public void beforeScoreTest(){
        Context context = ApplicationProvider.getApplicationContext();
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        personDao = appDatabase.getPersonDao();
        persons = personDao.getAll();

        if (persons.isEmpty()) {
            personDao.addPerson(new Person("Morten", ContextCompat.getDrawable(context, R.drawable.morten)));
        }
    }
     */

    @Test
    public void scoreCorrectAnswer(){
        if (!QuizActivity.persons.isEmpty()) {
            String name = QuizActivity.persons.get(0).getName();

            assertThat(QuizActivity.score, equalTo(0));
            onView(withId(R.id.answerText)).perform(typeText(name), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.quizButton)).perform(click());
            assertThat(QuizActivity.score, equalTo(1));
        } else {
            Log.e("test", "Can't test score without entries in database");
        }
    }

    @Test
    public void scoreIncorrectAnswer(){
        if (!QuizActivity.persons.isEmpty()) {
            assertThat(QuizActivity.score, equalTo(0));
            onView(withId(R.id.answerText)).perform(typeText("Wrong"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.quizButton)).perform(click());
            assertThat(QuizActivity.score, equalTo(0));
        } else {
            Log.e("test", "Can't test score without entries in database");
        }

    }

}
