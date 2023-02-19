package com.example.foodplaner.PlanDB;

import android.content.Context;

import com.example.foodplaner.Model.PlanMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class PlanConcreteLocalSource {
    private DAO dao;
    private static PlanConcreteLocalSource localSource=null;


    private PlanConcreteLocalSource(Context context){
        PlanDataBase db=PlanDataBase.getInstance(context.getApplicationContext());
        dao=db.dao();
    }
    public static PlanConcreteLocalSource getInstance(Context context){
        if(localSource==null)
        {
            localSource=new PlanConcreteLocalSource(context);
        }
        return localSource;

    }

    public Completable insert(PlanMeal meal) {
        return dao.insertMeal(meal);
    }


    public void delete(PlanMeal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.deleteMeal(meal);
            }
        }).start();

    }

    public Single<List<PlanMeal>> getAllStoredMeals() {
        return dao.getMeals();
    }

}
