package com.example.foodplaner.rebo;

import android.content.Context;


import com.example.foodplaner.Network.NetworkDelegate;
import com.example.foodplaner.Network.RemoteDataInterface;
import com.example.foodplaner.Model.PlanMeal;
import com.example.foodplaner.PlanDB.PlanConcreteLocalSource;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class Repository implements RepositoryInterface{
    Context context;
    RemoteDataInterface remote;
    PlanConcreteLocalSource planConcreteLocalSource;
    private static Repository repo=null;

    public Repository(RemoteDataInterface remotesource, Context context) {
        remote=remotesource;
        this.context=context;
        planConcreteLocalSource=PlanConcreteLocalSource.getInstance(context);
    }

    public static Repository getInstance(RemoteDataInterface remotesource,Context context){
        if (repo==null){
            repo=new Repository(remotesource,context);
        }
        return repo;

    }

    @Override
    public Completable insertMeal(PlanMeal meal) {
        return planConcreteLocalSource.insert(meal);
    }

    @Override
    public void deleteMeal(PlanMeal meal) {
        planConcreteLocalSource.delete(meal);

    }

    @Override
    public void getAllMeals(NetworkDelegate networkDelegate, String l) {
            remote.getData(networkDelegate,l);
    }

    @Override
    public Single<List<PlanMeal>> getStoredMeals() {
        return planConcreteLocalSource.getAllStoredMeals();
    }
}
