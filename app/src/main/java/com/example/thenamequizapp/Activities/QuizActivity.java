package com.example.thenamequizapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thenamequizapp.Classes.Database;
import com.example.thenamequizapp.Classes.Person;
import com.example.thenamequizapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class QuizActivity extends AppCompatActivity {

    TextView question;
    ImageView personImage;
    EditText answerText;
    Button button;
    TextView scoreText;
    private Person  person;
    private Integer score;
    private Integer maxScore;
    private Iterator<Person> personIterator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Find view by id
        question = findViewById(R.id.questionText);
        personImage = findViewById(R.id.personImage);
        answerText = findViewById(R.id.answerText);
        button = findViewById(R.id.quizButton);
        scoreText = findViewById(R.id.scoreText);

        // Score values
        score = 0;
        maxScore = 0;

        // Shuffle database
        ArrayList<Person> persons = ((Database) this.getApplication()).getDatabase();
        Collections.shuffle(persons);
        personIterator = persons.iterator();
    }
}