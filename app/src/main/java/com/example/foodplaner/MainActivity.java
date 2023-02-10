package com.example.foodplaner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import com.example.foodplaner.AllMeals.View.AllMealsFragment;
import com.example.foodplaner.MealMainScreen.MealMainScreenActivity;
import com.example.foodplaner.MyPlan.View.MyPlanFragment;

public class MainActivity extends AppCompatActivity {
    AllMealsFragment fragment;
    MyPlanFragment fragment2;



    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* fragment = new AllMealsFragment();
        fragment2=new MyPlanFragment();
        FragmentManager manager =getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.card,fragment2);
        transaction.commit();*/
        Intent intent=new Intent(MainActivity.this, MealMainScreenActivity.class);
        startActivity(intent);




}
}