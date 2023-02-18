package com.example.foodplaner.MyPlan.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.foodplaner.AllMeals.View.OnMealClickListener;
import com.example.foodplaner.MealMainScreen.MealMainScreenActivity;
import com.example.foodplaner.Model.PlanMeal;
import com.example.foodplaner.MyPlan.Presenter.MyPlanPresenter;
import com.example.foodplaner.MyPlan.Presenter.MyPlanPresenterInterface;
import com.example.foodplaner.Network.MealClient;
import com.example.foodplaner.rebo.Repository;
import com.example.foodplaner.R;

import org.reactivestreams.Subscription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MyPlanFragment extends Fragment implements OnDeleteClickListener, MyPlanViewInterface {
    private static final String TAG="MyPlanFragment";
    MyPlanAdapter planAdapter;
    List<String> day;
    HashMap<String,List<String>> dayItem;
    ExpandableListView expandableListView;
    MyPlanPresenterInterface myPlanPresenterInterface;
    List<String> topic1;
    List<String> topic2;
    List<String> topic3;
    List<String> topic4;
    List<String> topic5;
    List<String> topic6;
    List<String> topic7;
    List<PlanMeal> allMeals;
    PlanMeal meal;







    public MyPlanFragment() {
        day=new ArrayList<String>();
        dayItem=new HashMap<String,List<String>>();
        allMeals=new ArrayList<>();

        day.add("Saturday");
        day.add("Sunday");
        day.add("Monday");
        day.add("Tuesday");
        day.add("Wednesday");
        day.add("Thurasday");
        day.add("Friday");
        topic1=new ArrayList<>();
        topic2=new ArrayList<>();
        topic3=new ArrayList<>();
        topic4=new ArrayList<>();
        topic5=new ArrayList<>();
        topic6=new ArrayList<>();
        topic7=new ArrayList<>();

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        expandableListView=view.findViewById(R.id.expandablrListView);

        myPlanPresenterInterface=new MyPlanPresenter(this, Repository.getInstance(MealClient.getINSTANCE(),getContext()));
        myPlanPresenterInterface.getMeal();
        planAdapter=new MyPlanAdapter(getActivity(),day,dayItem,this,allMeals);
        planAdapter.notifyDataSetChanged();
        expandableListView.setAdapter(planAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                final String selected= (String) planAdapter.getChild(i,i1);
                Intent intent;
                for(int j=0;j<allMeals.size();j++){
                    if(allMeals.get(j).getMeal().getStrMeal().equals(selected)){
                        intent=new Intent(view.getContext(), MealMainScreenActivity.class);
                        intent.putExtra("MEAL", (Serializable) allMeals.get(j).getMeal() );
                        startActivity(intent);
                    }
                }



                return true;
            }
        });

    }


    private void fillList(List<PlanMeal> item) {
        for(int i=0;i< item.size();i++){

            if (item.get(i).getDay().equals("Saturday")){
                topic1.add(item.get(i).getMeal().getStrMeal());

            }
            else if (item.get(i).getDay().equals("Sunday")){
                topic2.add(item.get(i).getMeal().getStrMeal());

            }
            else if (item.get(i).getDay().equals("Monday")){
                topic3.add(item.get(i).getMeal().getStrMeal());

            }
            else if (item.get(i).getDay().equals("Tuesday")){
                topic4.add(item.get(i).getMeal().getStrMeal());


            }
            else if (item.get(i).getDay().equals("Wednesday")){
                topic5.add(item.get(i).getMeal().getStrMeal());

            }
            else if (item.get(i).getDay().equals("Thurasday")){
                topic6.add(item.get(i).getMeal().getStrMeal());

            }
            else if (item.get(i).getDay().equals("Friday")){
                topic7.add(item.get(i).getMeal().getStrMeal());

            }
        }

        dayItem.put(day.get(0),topic1);
        dayItem.put(day.get(1),topic2);
        dayItem.put(day.get(2),topic3);
        dayItem.put(day.get(3),topic4);
        dayItem.put(day.get(4),topic5);
        dayItem.put(day.get(5),topic6);
        dayItem.put(day.get(6),topic7);

    }

    @Override
    public void showData(Single<List<PlanMeal>> meals) {

        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item->{allMeals=item;
                    fillList(item);
                    }
);


    }

    @Override
    public void deleteFromPlan(PlanMeal meal) {
        myPlanPresenterInterface.deleteFromPlan(meal);




    }

    @Override
    public void onDelete(String mealstr) {
        for(int i=0;i<allMeals.size();i++){
        if(allMeals.get(i).getMeal().strMeal.equals(mealstr)){
            meal=allMeals.get(i);
        }
        }
        deleteFromPlan(meal);

    }


}