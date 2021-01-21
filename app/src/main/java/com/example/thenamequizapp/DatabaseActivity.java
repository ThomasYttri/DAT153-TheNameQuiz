package com.example.thenamequizapp;

import android.content.Context;
import android.os.Bundle;
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
    private String names[] = {"Jon", "Morten", "Bendik"};
    private Integer images[] = {R.drawable.jon, R.drawable.morten, R.drawable.bendik};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        listView = findViewById(R.id.dbList);

        // Create adapter and fill with hardcoded data
        MyAdapter myAdapter = new MyAdapter(this, names, images);
        listView.setAdapter(myAdapter);
        // Setting item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(DatabaseActivity.this, "You clicked on Jon", Toast.LENGTH_SHORT).show();
                }
                if (position == 1) {
                    Toast.makeText(DatabaseActivity.this, "You clicked on Morten", Toast.LENGTH_SHORT).show();
                }
                if (position == 2) {
                    Toast.makeText(DatabaseActivity.this, "You clicked on Bendik", Toast.LENGTH_SHORT).show();
                }

            }
        });


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

//    HashMap database = new HashMap();
//    addPerson("Jon", "jon");
    /*
    private void addPerson(String name, String img){
        int key = 1;

        if(name != null) {
            database.put(key, name);
            key++;
        } else {
            System.out.println("Invalid name");
        }
    }

     */

}