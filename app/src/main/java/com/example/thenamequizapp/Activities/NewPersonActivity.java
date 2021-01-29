package com.example.thenamequizapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.thenamequizapp.R;

public class NewPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        
    }

    // When button is clicked
    public void buttonClick(View view) {
        EditText editTextName = findViewById(R.id.editTextName);
        Log.d("NAME", editTextName.getText().toString());
        if (editTextName.getText().toString() != null) {
            Intent intent = new Intent(this, DatabaseActivity.class);
            intent.putExtra("Name", editTextName.getText().toString());
            startActivity(intent);
        } else {
            editTextName.setError("Name = null");
        }

    }
}