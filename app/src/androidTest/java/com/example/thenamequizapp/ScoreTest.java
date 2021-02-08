package com.example.thenamequizapp;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.thenamequizapp.Activities.QuizActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ScoreTest {

    @Rule
    public ActivityScenarioRule<QuizActivity> activityRule =
            new ActivityScenarioRule<QuizActivity>(QuizActivity.class);


    @Test
    public void ScoreCountTest(){

    }

}
