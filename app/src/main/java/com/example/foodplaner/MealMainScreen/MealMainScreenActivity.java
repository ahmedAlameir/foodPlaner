package com.example.foodplaner.MealMainScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.foodplaner.AllMeals.View.AllMealsAdapter;
import com.example.foodplaner.AllMeals.View.AllMealsFragment;
import com.example.foodplaner.Model.Ingredient;
import com.example.foodplaner.R;

import java.util.ArrayList;

public class MealMainScreenActivity extends AppCompatActivity {
    MealMainScreenAdapter adapter;
    ArrayList<Ingredient> ingredients=new ArrayList<>();
    Ingredient ingredient=new Ingredient("potatoes","3 large","https://www.themealdb.com/images/ingredients/Potatoes.png");
    Ingredient ingredient2=new Ingredient("potatoes","3 large","https://www.themealdb.com/images/ingredients/Potatoes.png");
    Ingredient ingredient3=new Ingredient("potatoes","3 large","https://www.themealdb.com/images/ingredients/Potatoes.png");
    Ingredient ingredient4=new Ingredient("potatoes","3 large","https://www.themealdb.com/images/ingredients/Potatoes.png");
    Ingredient ingredient5=new Ingredient("potatoes","3 large","https://www.themealdb.com/images/ingredients/Potatoes.png");
    Ingredient ingredient6=new Ingredient("potatoes","3 large","https://www.themealdb.com/images/ingredients/Potatoes.png");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_main_screen);
        RecyclerView recyclerView = findViewById(R.id.rv_ingredient);
        ingredients.add(ingredient);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);
        ingredients.add(ingredient4);
        ingredients.add(ingredient5);
        ingredients.add(ingredient6);




        recyclerView.setLayoutManager(new LinearLayoutManager(MealMainScreenActivity.this));
        adapter=new MealMainScreenAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setMeals(ingredients);
        adapter.notifyDataSetChanged();

    }
}