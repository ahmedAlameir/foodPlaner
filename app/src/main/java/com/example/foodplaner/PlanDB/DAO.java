package com.example.foodplaner.PlanDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.foodplaner.Model.PlanMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DAO {
    @Query("Select * from PlanMeals")
    Single<List<PlanMeal>> getMeals();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMeal(PlanMeal meal);

    @Delete
    void deleteMeal(PlanMeal meal);

}
