package com.example.thenamequizapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.thenamequizapp.R;

import java.util.List;

import Classes.Person;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        /*

        Hent fram lista med personer

        Velg tilfeldig person fra lista

        Hent bilde fra person

        onclick sjekk edittext om navn er lik navn i person objekt (hugs unngå case senitivitet)
        Legg person til i ny liste over personer som har blitt valgt

        Gi tilbakemelding Rett / feil
        Legg til counter på antall rett

        Hent fram nytt bilde, sjekk mot personer som allerede er valgt

        repeat

         */
    }
}