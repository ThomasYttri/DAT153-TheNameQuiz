package com.example.thenamequizapp;

import android.content.Context;

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

import org.junit.BeforeClass;
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

    private QuizActivity quizActivity;

    @Rule
    public ActivityScenarioRule<QuizActivity> activityRule =
            new ActivityScenarioRule<>(QuizActivity.class);

    @BeforeClass
    public static void beforeScoreTest(){
        List<Person> persons;
        PersonDao personDao;

        Context context = ApplicationProvider.getApplicationContext();
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        personDao = appDatabase.getPersonDao();
        persons = personDao.getAll();

        if (persons.isEmpty()) {
            personDao.addPerson(new Person("Morten", ContextCompat.getDrawable(context, R.drawable.morten)));
        }
    }

    @Test
    public void scoreCorrectAnswer(){

        activityRule.getScenario().onActivity(x -> quizActivity = x);
        assertFalse(quizActivity.persons.isEmpty());
        String name = quizActivity.persons.get(0).getName();

        assertThat(quizActivity.score, equalTo(0));
        onView(withId(R.id.answerText)).perform(typeText(name), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.quizButton)).perform(click());
        assertThat(quizActivity.score, equalTo(1));
    }

    @Test
    public void scoreIncorrectAnswer(){
        activityRule.getScenario().onActivity(x -> quizActivity = x);
        assertFalse(quizActivity.persons.isEmpty());
        assertThat(quizActivity.score, equalTo(0));
        onView(withId(R.id.answerText)).perform(typeText("Wrong"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.quizButton)).perform(click());
        assertThat(quizActivity.score, equalTo(0));
    }

}
