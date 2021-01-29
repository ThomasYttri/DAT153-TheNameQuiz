package com.example.thenamequizapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class DatabaseActivity extends AppCompatActivity {

    private ListView listView;
    public String names[] = {"Jon", "Morten", "Bendik"};
    public Integer images[] = {R.drawable.jon, R.drawable.morten, R.drawable.bendik};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        Bundle extras = getIntent().getExtras();
        checkForNewPerson(extras);

        listView = findViewById(R.id.dbList);

        // Create adapter and fill with hardcoded data
        MyAdapter myAdapter = new MyAdapter(this, names, images);
        listView.setAdapter(myAdapter);

    }

    // Checks for new person in extras and calls addPerson if present
    public void checkForNewPerson(Bundle extras) {

        if (extras == null) {
            return;
        } else {
            String name = extras.getString("name");
            String image = extras.getString("image");
            if (name != null && image != null) {
                addPerson(name, image);
                extras.clear();
            }
        }
    }
    public boolean addPerson(String name, String image) {
        if (name != null && getResources().getIdentifier(image, "drawable", getPackageName()) != 0) {
            int i = names.length +1;
            names[i] = name;
            images[i] = getResources().getIdentifier(image, "drawable", getPackageName());
            Log.i("ADDPERSON", "addPerson succeeded");
            return true;

        } else {
            Log.d("ADDPERSON", "addPerson failed");
            return false;
        }
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String names[];
        Integer img[];

        MyAdapter (Context context, String names[], Integer img[]) {
            super(context, R.layout.row_item, R.id.textViewName, names);
            this.context = context;
            this.names = names;
            this.img = img;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row_item, parent, false);
            ImageView imageView = row.findViewById(R.id.imageViewPerson);
            TextView textView = row.findViewById(R.id.textViewName);

            //Set resources on view
            imageView.setImageResource(img[position]);
            textView.setText(names[position]);

            return row;
        }
    }

}