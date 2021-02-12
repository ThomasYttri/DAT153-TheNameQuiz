package com.example.thenamequizapp;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.thenamequizapp.Activities.QuizActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;

@RunWith(AndroidJUnit4.class)
public class ScoreTest {

    @Rule
    public ActivityScenarioRule<QuizActivity> activityRule =
            new ActivityScenarioRule<QuizActivity>(QuizActivity.class);

    @Test
    public void scoreCorrectAnswer(){
        if (!QuizActivity.persons.isEmpty()) {
            String name = QuizActivity.persons.get(0).getName();

            onView(withId(R.id.scoreValue)).check(matches(withSubstring("0/0")));
            onView(withId(R.id.answerText)).perform(typeText(name), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.quizButton)).perform(click());
            onView(withId(R.id.scoreValue)).check(matches(withSubstring("1/1")));
        }
    }

    @Test
    public void scoreIncorrectAnswer(){
        if (!QuizActivity.persons.isEmpty()) {
            onView(withId(R.id.scoreValue)).check(matches(withSubstring("0/0")));
            onView(withId(R.id.answerText)).perform(typeText("Wrong"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.quizButton)).perform(click());
            onView(withId(R.id.scoreValue)).check(matches(withSubstring("1/1")));
        }
    }



}
