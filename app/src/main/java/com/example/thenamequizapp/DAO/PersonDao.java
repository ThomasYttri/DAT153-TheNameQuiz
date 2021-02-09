package com.example.thenamequizapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.thenamequizapp.Classes.Person;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM person")
    List<Person> getAll();

    @Query("SELECT * FROM person WHERE uid IN (:personIds)")
    List<Person> loadAllByIds(int[] personIds);

    @Insert
    void addPerson(Person person);

    @Delete
    void deletePerson(Person person);
}
