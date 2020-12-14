package com.example.gunawan_api_local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Meal.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract DataDao dataDao();
}
