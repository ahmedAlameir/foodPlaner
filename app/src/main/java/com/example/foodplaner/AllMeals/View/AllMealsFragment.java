package com.example.foodplaner.AllMeals.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplaner.AllMeals.Presenter.AllMealPresenterInterface;
import com.example.foodplaner.AllMeals.Presenter.AllMealsPresenter;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Network.MealClient;
import com.example.foodplaner.Network.Repository;
import com.example.foodplaner.R;

import java.util.ArrayList;


public class AllMealsFragment extends Fragment implements AllMealViewInterface,OnFavouriteClickListener,OnPlanClickListener {

    AllMealsAdapter adapter;
    ArrayList<Meal> meals=new ArrayList<>();
    AllMealPresenterInterface allMealPresenterInterface;



    public AllMealsFragment() {


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_meals, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rv_allmeals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new AllMealsAdapter(AllMealsFragment.this,AllMealsFragment.this);
        allMealPresenterInterface =new AllMealsPresenter(this,
                Repository.getInstance( MealClient.getINSTANCE(),getContext()));
        recyclerView.setAdapter(adapter);
        allMealPresenterInterface.getMeals();


    }

    @Override
    public void showData(ArrayList<Meal> meals) {
        adapter.setMeals(meals);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void addToFav(Meal meal) {

    }

    @Override
    public void addToPlan(Meal meal) {

    }

    @Override
    public void onAddFav(Meal meal) {

    }

    @Override
    public void onAddPlan(Meal meal) {

    }
}