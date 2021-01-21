package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);
    }

    // When button is clicked
    public void buttonClick(View view) {
        //Verify data
        EditText editTextName = findViewById(R.id.editTextTextName);
        EditText editTextImage = findViewById(R.id.editTextImage);

        String name = editTextName.getText().toString();
        String image = editTextImage.getText().toString();

        if (name != null && image != null) {
            Intent i = new Intent(this, DatabaseActivity.class);
            i.putExtra("name", name);
            i.putExtra("imageString", image);

            startActivity(i);

        } else if (name != null){
            editTextName.setError("Name is equal to null");
        } else {
            editTextImage.setError("Image is equal to null");
        }

    }
}