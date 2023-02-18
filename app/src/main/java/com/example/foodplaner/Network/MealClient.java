package com.example.foodplaner.Network;

import static com.example.foodplaner.Network.APIInterface.BASE_URL;

import android.util.Log;

import com.example.foodplaner.Model.Categories;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.Meals;
import com.example.foodplaner.Network.CallBack.CategoriesCallBack;
import com.example.foodplaner.Network.CallBack.ChipListCallback;
import com.example.foodplaner.Network.CallBack.FilteredCallBack;
import com.example.foodplaner.Network.CallBack.RandomMealCallBack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealClient implements RemoteDataInterface{
    private static final String TAG = "MealClient";
    private static final String LIST = "list";
    private static MealClient INSTANCE = null;
    private APIInterface apiInterface;

    public MealClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        apiInterface = retrofit.create(APIInterface.class);
    }

    public static MealClient getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MealClient();
        }
        return INSTANCE;

    }

    @Override
    public void getData(NetworkDelegate networkDelegate) {

        Single<Meals> observable = apiInterface.getMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(item->networkDelegate.getData(item.getMeals()),
                e-> Log.i("TAG","elmoshkela : "+e.getMessage()));

    }
    @Override
    public void getRandomMeal(RandomMealCallBack randomMealCallBack) {

       Observable<Meals> observable = apiInterface.getRandomMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(item->
                {
                    randomMealCallBack.callBack(item);

                },
                throwable -> {
            Log.i(TAG, "getRandomMeal: "+throwable.getMessage());
        });

    }

    @Override
    public void getCategories(CategoriesCallBack callBack) {
        Observable<Categories> observable = apiInterface.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(item->
                {
                    callBack.callBack(item);
                },
                throwable -> {
                    Log.i(TAG, "getRandomMeal: "+throwable.getMessage());
                });
    }

    @Override
    public void getCategoriesList(ChipListCallback callback) {

        Observable<Meals> observable = apiInterface.getCategoriesList(LIST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(item->
                {
                    callback.callBack(item);

                },
                throwable -> {
                    Log.i(TAG, "getRandomMeal: "+throwable.getMessage());
                });

    }

    @Override
    public void getAreaList(ChipListCallback callback) {
        Observable<Meals> observable = apiInterface.getAreaList(LIST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(item->
                {
                    callback.callBack(item);

                },
                throwable -> {
                    Log.i(TAG, "getRandomMeal: "+throwable.getMessage());
                });

    }

    @Override
    public void getIngredientList(ChipListCallback callback) {
        Observable<Meals> observable = apiInterface.getIngredientList(LIST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(item->
                {
                    callback.callBack(item);

                },
                throwable -> {
                    Log.i(TAG, "getRandomMeal: "+throwable.getMessage());
                });

    }

    @Override
    public void getCategoryFiltered(String s, FilteredCallBack callBack) {
        Observable<Meals> observable = apiInterface.getCategoriesFiltered(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(item->
                {
                    callBack.callBack(item);

                },
                throwable -> {
                    Log.i(TAG, "getRandomMeal: "+throwable.getMessage());
                });
    }

    @Override
    public void getAreaFiltered(String s, FilteredCallBack callBack) {
        Observable<Meals> observable = apiInterface.getAreaFiltered(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(item->
                {
                    Log.i(TAG, "getAreaFiltered: "+s+item.getMeals());
                    callBack.callBack(item);

                },
                throwable -> {
                    Log.i(TAG, "getRandomMeal: "+throwable.getMessage());
                });
    }

    @Override
    public void getIngredientFiltered(String s, FilteredCallBack callBack) {
        Observable<Meals> observable = apiInterface.getIngredientFiltered(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(item->
                {
                    callBack.callBack(item);

                },
                throwable -> {
                    Log.i(TAG, "getRandomMeal: "+throwable.getMessage());
                });
    }

    @Override
    public void getGetMeal(String idMeal, FilteredCallBack callBack) {
        Observable<Meals> observable = apiInterface.getMeal(idMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(item->
                {
                    callBack.callBack(item);

                },
                throwable -> {
                    Log.i(TAG, "getRandomMeal: "+throwable.getMessage());
                });
    }
}
