package com.example.thenamequizapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thenamequizapp.Classes.AppDatabase;
import com.example.thenamequizapp.R;

import java.util.List;

import com.example.thenamequizapp.Classes.Person;

public class DatabaseAdapter extends ArrayAdapter<Person> {

    private final Context context;
    private final int resources;
    private final AppDatabase appDatabase;

    public DatabaseAdapter (@NonNull Context context, int resources, @NonNull List<Person> persons) {
        super(context, resources, persons);
        this.context = context;
        this.resources = resources;
        this.appDatabase = AppDatabase.getInstance(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(resources, parent, false);

        //Image
        ImageView imageView = view.findViewById(R.id.imageViewPerson);
        imageView.setImageDrawable(getItem(position).getImage());

        //Name
        TextView textView = view.findViewById(R.id.textViewName);
        textView.setText(getItem(position).getName());

        //Delete button
        Button deleteButton = view.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(v -> deletePerson(position));

        return view;
    }

    public void deletePerson(int pos){
        Person person = getItem(pos);
        remove(person);
        appDatabase.personDao().deletePerson(person);
        Toast.makeText(context, "Successfully removed " + person.getName(), Toast.LENGTH_SHORT).show();
    }
}
