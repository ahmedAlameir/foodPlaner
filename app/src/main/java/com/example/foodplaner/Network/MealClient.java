package com.example.foodplaner.Network;

import static com.example.foodplaner.Network.APIInterface.BASE_URL;

import android.util.Log;

import com.example.foodplaner.Model.Meals;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealClient implements RemoteDataInterface{
    private static final String TAG = "MealClient";
    private static MealClient INSTANCE = null;
    private APIInterface apiInterface;

    public MealClient(){}

    public static MealClient getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MealClient();
        }
        return INSTANCE;

    }

    @Override
    public void getData(NetworkDelegate networkDelegate) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        apiInterface = retrofit.create(APIInterface.class);
        Single<Meals> observable = apiInterface.getMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(item->networkDelegate.getData(item.getMeals()),
                e-> Log.i("TAG","elmoshkela : "+e.getMessage()));

    }
}
