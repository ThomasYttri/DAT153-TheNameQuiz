package com.example.thenamequizapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView scoreValue;
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
        scoreValue = findViewById(R.id.scoreValue);

        // Score values
        score = 0;
        maxScore = 0;

        // Bind button
        button.setOnClickListener(v -> checkAnswer());

        // Shuffle database
        ArrayList<Person> persons = ((Database) this.getApplication()).getDatabase();
        Collections.shuffle(persons);
        personIterator = persons.iterator();

        newPerson();
    }

    public void newPerson() {
        // As long as there is more people in the database, else display end information
        if (personIterator.hasNext()) {
            person = personIterator.next();
            personImage.setImageDrawable(person.getImage());
        } else {
            question.setText("Quiz finished, your results:");
            personImage.setImageDrawable(null);
            answerText.setHint("");
            answerText.setBackgroundResource(android.R.color.transparent);
            answerText.clearFocus();
            button.setText("End quiz");
            button.setOnClickListener(v -> endQuiz());

        }
        answerText.setText("");
        answerText.onEditorAction(EditorInfo.IME_ACTION_DONE);
    }

    public void checkAnswer(){
        maxScore++;
        String submittedAnswer = answerText.getText().toString();
        String correctAnswer = person.getName();

        if(submittedAnswer.toLowerCase().equals(correctAnswer.toLowerCase())) {
            score++;
            Toast.makeText(this, "Correct answer", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong answer, this persons name is: " + correctAnswer, Toast.LENGTH_SHORT).show();
        }

        // Update score in view
        scoreValue.setText(score + " / " + maxScore);

        answerText.onEditorAction(EditorInfo.IME_ACTION_DONE);
        newPerson();
    }

    // Onclick for end quiz button
    public void endQuiz(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // onClick for newQuizButton
    public void newQuiz(View view){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }
}