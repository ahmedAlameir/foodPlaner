package com.example.foodplaner.MyPlan.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.foodplaner.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyPlanFragment extends Fragment {
    MyPlanAdapter planAdapter;
    List<String> day;
    HashMap<String,List<String>> dayItem;
    ExpandableListView expandableListView;

    public MyPlanFragment() {

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
        showList();
        planAdapter=new MyPlanAdapter(getContext(),day,dayItem);
        expandableListView.setAdapter(planAdapter);

    }


    private void showList() {
        day=new ArrayList<String>();
        dayItem=new HashMap<String,List<String>>();

        day.add("Saturday");
        day.add("Sunday");
        day.add("Monday");
        day.add("Tuesday");
        day.add("Wednesday");
        day.add("Thurasday");
        day.add("Friday");

        List<String> topic1=new ArrayList<>();
        topic1.add("Meal 1");
        topic1.add("Meal 2");
        List<String> topic2=new ArrayList<>();
        topic2.add("Meal 1");
        topic2.add("Meal 2");
        List<String> topic3=new ArrayList<>();
        topic3.add("Meal 1");
        topic3.add("Meal 2");
        List<String> topic4=new ArrayList<>();
        topic4.add("Meal 1");
        topic4.add("Meal 2");
        List<String> topic5=new ArrayList<>();
        topic5.add("Meal 1");
        topic5.add("Meal 2");
        List<String> topic6=new ArrayList<>();
        topic6.add("Meal 1");
        topic6.add("Meal 2");
        List<String> topic7=new ArrayList<>();
        topic7.add("Meal 1");
        topic7.add("Meal 2");


        dayItem.put(day.get(0),topic1);
        dayItem.put(day.get(1),topic2);
        dayItem.put(day.get(2),topic3);
        dayItem.put(day.get(3),topic4);
        dayItem.put(day.get(4),topic5);
        dayItem.put(day.get(5),topic6);
        dayItem.put(day.get(6),topic7);




    }
}