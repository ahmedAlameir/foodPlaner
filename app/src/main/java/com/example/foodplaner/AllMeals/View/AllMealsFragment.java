package com.example.foodplaner.AllMeals.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.foodplaner.AllMeals.Presenter.AllMealPresenterInterface;
import com.example.foodplaner.AllMeals.Presenter.AllMealsPresenter;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Network.MealClient;
import com.example.foodplaner.Network.Repository;
import com.example.foodplaner.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AllMealsFragment extends Fragment implements AllMealViewInterface,OnFavouriteClickListener,OnPlanClickListener {

    AllMealsAdapter adapter;
    ArrayList<Meal> allMeals=new ArrayList<>();
    ArrayList<Meal> empty=new ArrayList<>();
    EditText search;
    String str1;
    String str2;
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
        search=view.findViewById(R.id.allmeals_search);
        RecyclerView recyclerView = view.findViewById(R.id.rv_allmeals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new AllMealsAdapter(AllMealsFragment.this,AllMealsFragment.this);
        allMealPresenterInterface =new AllMealsPresenter(this,
                Repository.getInstance( MealClient.getINSTANCE(),getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setMeals(empty);
        adapter.notifyDataSetChanged();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0)
                {
                    adapter.setMeals(empty);
                    adapter.notifyDataSetChanged();
                }
                if (charSequence.length() ==1) {
                    str1 = charSequence.toString().substring(0, 1).toUpperCase();
                    allMealPresenterInterface.getMeals(str1);
                }
                if(charSequence.length()>1){

                    str2 = charSequence.toString().substring(1);
                        List<Meal> Filteredmeals = allMeals.stream().filter(item -> item.strMeal.startsWith(str1 + str2)).collect(Collectors.toList());
                         adapter.setMeals(Filteredmeals);
                         adapter.notifyDataSetChanged();
                    }
                }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }

    @Override
    public void showData(ArrayList<Meal> meals) {
        this.allMeals=meals;
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