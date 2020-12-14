package com.example.gunawan_api_local;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DataDao {
    @Query("SELECT * FROM Meal")
    List<Meal> getAll();

    @Insert
    void insertAll(Meal meal);

    @Update
    void update (Meal meal);

    @Delete
    void delete (Meal meal);

}
