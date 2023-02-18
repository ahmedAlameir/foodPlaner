package com.example.foodplaner.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplaner.Model.User;

import java.util.List;
@Dao
public interface UserDAO {

    @Query("SELECT * From user Where userName = :userName")
    LiveData<List<User>> getAllMeals(String userName);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeals(User user);
    @Delete
    void deleteMeals(User user);
}
