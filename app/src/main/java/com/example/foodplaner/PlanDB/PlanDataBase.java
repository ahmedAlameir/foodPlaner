package com.example.foodplaner.PlanDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplaner.Model.PlanMeal;

@Database(entities = {PlanMeal.class},version = 3)
public abstract class PlanDataBase extends RoomDatabase {
    private static PlanDataBase instance = null;
    public abstract DAO dao();

    public static synchronized PlanDataBase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), PlanDataBase.class, "PlanDataBaseVersion3").build();
        return instance;
    }



}
