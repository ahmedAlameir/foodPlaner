package com.example.foodplaner.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase instance = null;
    public abstract UserDAO userDAO();
    public static synchronized  UserDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user")
                    .build();}
        return instance;
    }
}