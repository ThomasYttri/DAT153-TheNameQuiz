package com.example.thenamequizapp.Activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.example.thenamequizapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Classes.Person;

public class DatabaseActivity extends AppCompatActivity {

    private ListView listView;
    private List<Person> persons = new ArrayList<Person>();
    Person jon = new Person("Jon", R.drawable.jon);
    Person morten = new Person("Morten", R.drawable.morten);
    Person bendik = new Person("Bendik", R.drawable.bendik);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        persons.add(jon);
        persons.add(morten);
        persons.add(bendik);

        checkExtras();

        listView = findViewById(R.id.dbList);

        String names[] = namesToList(persons);
        Integer images[] = imagesToList(persons);

        MyAdapter myAdapter = new MyAdapter(this, names, images);
        listView.setAdapter(myAdapter);

    }

    public List<Person> getPersons() {
        return persons;
    }

    public String[] namesToList(List<Person> persons){
        String names[] = new String[persons.size()];
        int tall = 0;
        for(Person person : persons) {
            names[tall] = person.getName();
            tall++;
        }
        return names;
    }

    public Integer[] imagesToList(List<Person> persons) {
        Integer images[] = new Integer[persons.size()];
        int i = 0;
        for(Person person : persons) {
            images[i] = person.getImage();
            i++;
        }
        return images;
    }

    public void checkExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Person thomas = new Person(extras.getString("Name").toString(), R.drawable.thomas);
            persons.add(thomas);
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