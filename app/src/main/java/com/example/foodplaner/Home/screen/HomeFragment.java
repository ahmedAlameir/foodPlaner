package com.example.foodplaner.Home.screen;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplaner.Home.adaptor.home_category.HomeCategoryAdapter;
import com.example.foodplaner.Home.adaptor.home_selection.HomeSelectionAdapter;
import com.example.foodplaner.MealMainScreen.View.MealMainScreenActivity;
import com.example.foodplaner.Model.Category;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Network.MealClient;
import com.example.foodplaner.databinding.FragmentHomeBinding;
import com.example.foodplaner.rebo.Repository;

import java.util.List;


public class HomeFragment extends Fragment implements HomeInterface {
    private HomePresenter presenter;
    private FragmentHomeBinding binding;
    private HomeSelectionAdapter selectionAdapter;
    private HomeCategoryAdapter categoryAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentHomeBinding.inflate(inflater,container,false);
        presenter = new HomePresenter(this,
                Repository.getInstance( MealClient.getINSTANCE(),getContext())
        ,getContext());
        categoryAdapter = new HomeCategoryAdapter(presenter);
        selectionAdapter = new HomeSelectionAdapter(presenter);
        binding.homeCatList.setNestedScrollingEnabled(false);
        binding.homeSelectionList.setNestedScrollingEnabled(false);
        binding.homeCatList.setAdapter(categoryAdapter);
        binding.homeSelectionList.setAdapter(selectionAdapter);
        presenter.fetchMeals();
        presenter.fetchCategory();
        presenter.setOnSelectionClickListener(meal -> {
            Intent i = new Intent(getContext(), MealMainScreenActivity.class);
            i.putExtra("MEAL",meal);
            startActivity(i);
        });
        return binding.getRoot();
    }



    @Override
    public void setDataInSelectionList(List<Meal> meals) {
        selectionAdapter.setList(meals);
    }

    @Override
    public void setCategoriesInSelectionList(List<Category> categories) {
        categoryAdapter.setList(categories);
    }
}