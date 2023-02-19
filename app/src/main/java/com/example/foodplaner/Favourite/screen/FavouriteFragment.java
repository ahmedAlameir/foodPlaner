package com.example.foodplaner.Favourite.screen;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplaner.Favourite.adaptor.FavouriteAdapter;
import com.example.foodplaner.MealMainScreen.View.MealMainScreenActivity;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.User;
import com.example.foodplaner.R;
import com.example.foodplaner.database.MealLocalSource;
import com.example.foodplaner.databinding.FragmentFavouriteBinding;
import com.example.foodplaner.databinding.FragmentListBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment implements FavouriteInterface{


    FragmentFavouriteBinding binding;
    FavouritePresenter presenter;
    FavouriteAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentFavouriteBinding.inflate(inflater,container,false);
        presenter = new FavouritePresenter(this,getContext());
        adapter =new FavouriteAdapter(presenter);
        binding.catList.setAdapter(adapter);
        presenter.setOnCategoryClickListener(meal -> {
            Intent i = new Intent(getContext(), MealMainScreenActivity.class);
            i.putExtra("MEAL",meal);
            startActivity(i);
        });
        getData();
        return binding.getRoot();
    }
    public void getData(){
        MealLocalSource.getInstance(getContext()).getMeals(FirebaseAuth.getInstance().getUid()).observe(getViewLifecycleOwner(), users -> {
            ArrayList<Meal> meals= new ArrayList<>();
            for (User user : users){
                meals.add(user.getMeal());
            }
            adapter.setList(meals);
        });

    }
}