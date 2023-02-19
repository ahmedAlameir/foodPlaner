package com.example.foodplaner.List.screen;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplaner.AllMeals.View.DayChoiceDialog;
import com.example.foodplaner.List.adapter.ListAdapter;
import com.example.foodplaner.MealMainScreen.View.MealMainScreenActivity;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.PlanMeal;
import com.example.foodplaner.Network.MealClient;
import com.example.foodplaner.R;
import com.example.foodplaner.databinding.FragmentListBinding;
import com.example.foodplaner.rebo.Repository;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ListFragment extends Fragment implements ListInterface, DayChoiceDialog.DayChoiceListener {

    private ListPresenter presenter;
    private FragmentListBinding binding;
    private PlanMeal planMeal;
    private String day;

    ListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  FragmentListBinding.inflate(inflater,container,false);
        presenter = new ListPresenter(this,
                Repository.getInstance( MealClient.getINSTANCE(),getContext())
                ,getContext());
        planMeal = new PlanMeal();
        binding.button1.setOnClickListener(v -> presenter.fetchCategory());
        binding.button2.setOnClickListener(v -> presenter.fetchIngredient());
        binding.button3.setOnClickListener(v -> presenter.fetchArea() );
        adapter = new ListAdapter(presenter);
        binding.catList.setAdapter(adapter);
        presenter.setOnListClickListener(meal -> {
            Intent i = new Intent(getContext(), MealMainScreenActivity.class);
            i.putExtra("MEAL",meal);
            startActivity(i);
        });

        return binding.getRoot();
    }
   @Override
    public void setChipData(ArrayList<String> chipNames){
       binding.chipGroupMain.removeAllViews();
        for (String s:chipNames){
            Chip mChip = (Chip) getLayoutInflater().inflate(R.layout.item_chip_category, null, false);
            mChip.setText(s);
            mChip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                presenter.fetchData(s);
                Log.i("TAG", "setChipData: "+s);
            });
            mChip.setCheckable(true);
            binding.chipGroupMain.addView(mChip);
        }

    }



    @Override
    public void setMealData(ArrayList<Meal> meals) {
        adapter.setList(meals);
    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        Log.i("TAG",list[position]+" is selected");
        day=list[position];
        planMeal.setDay(day);
        Completable completable = addToPlan(planMeal);
        completable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
                    Log.i("TAG", "onPositiveButtonClicked: "+"add To plan");
                });
    }
    public Completable addToPlan(PlanMeal meal) {
        return presenter.addToPlan(meal);
    }
    @Override
    public void onAddPlan(Meal meal) {

        DialogFragment dayChoice=new DayChoiceDialog();
        dayChoice.setCancelable(false);
        dayChoice.show(getParentFragmentManager(),"Day Choice");
        dayChoice.setTargetFragment(ListFragment.this,1);
        planMeal.setIdMeal(meal.getIdMeal());
        planMeal.setMeal(meal);

    }

    @Override
    public void onNegativeButtonClicked() {

    }
}