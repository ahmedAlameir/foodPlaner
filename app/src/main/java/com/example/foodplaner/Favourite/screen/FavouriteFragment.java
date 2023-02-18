package com.example.foodplaner.Favourite.screen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplaner.Favourite.adaptor.FavouriteAdapter;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.User;
import com.example.foodplaner.R;
import com.example.foodplaner.database.MealLocalSource;
import com.example.foodplaner.databinding.FragmentFavouriteBinding;
import com.example.foodplaner.databinding.FragmentListBinding;

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
        presenter = new FavouritePresenter(this);
        adapter =new FavouriteAdapter(presenter);
        binding.catList.setAdapter(adapter);
        getData();
        return binding.getRoot();
    }
    public void getData(){
        MealLocalSource.getInstance(getContext()).getMeals("2").observe(getViewLifecycleOwner(), users -> {
            ArrayList<Meal> meals= new ArrayList<>();
            for (User user : users){
                meals.add(user.getMeal());
            }
            adapter.setList(meals);
        });

    }
}