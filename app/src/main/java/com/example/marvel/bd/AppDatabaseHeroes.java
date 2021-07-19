package com.example.marvel.bd;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.marvel.data.marvel.Result;

@Database(entities = {Result.class}, version = 1, exportSchema = false)
public abstract class AppDatabaseHeroes extends RoomDatabase {
    public abstract ResultDao resultDao();
}
