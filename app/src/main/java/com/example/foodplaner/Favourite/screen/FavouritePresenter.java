package com.example.foodplaner.Favourite.screen;

import android.content.Context;

import com.example.foodplaner.Favourite.adaptor.FavouriteClickListener;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.User;
import com.example.foodplaner.database.MealLocalSource;
import com.google.firebase.auth.FirebaseAuth;

public class FavouritePresenter {

    private FavouriteClickListener listener;
    MealLocalSource localSource;
    private Context context;

    public FavouritePresenter(FavouriteInterface favouriteInterface, Context context){
        this.context = context;
        localSource = MealLocalSource.getInstance(context);
    }

    public Context getContext() {
        return context;
    }

    public void setOnCategoryClickListener(FavouriteClickListener listener) {
        this.listener = listener;
    }

    public void onMealClick(Meal meal) {
        if (listener != null) {
            listener.onItemClick(meal);
        }
    }

    public void deleteItem(Meal meal) {
        localSource.deleteMeal(new User(FirebaseAuth.getInstance().getUid(),meal));
    }
}
