package com.example.foodplaner.MyPlan.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.foodplaner.R;

import java.util.HashMap;
import java.util.List;

public class MyPlanAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> day;
    private HashMap<String,List<String>> dayItem;

    public MyPlanAdapter(Context context, List<String> day, HashMap<String, List<String>> dayItem) {
        this.context = context;
        this.day = day;
        this.dayItem = dayItem;
    }

    @Override
    public int getGroupCount() {
        return this.day.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.dayItem.get(this.day.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.day.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.dayItem.get(this.day.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String dayTitle=(String) getGroup(i);

        if(view==null){
            LayoutInflater inflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.day,null);
        }
        TextView daytv=view.findViewById(R.id.daytv);
        daytv.setText(dayTitle);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String dayItemTitle=(String) getChild(i,i1);

        if(view==null){
            LayoutInflater inflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.day_item,null);
        }
        TextView dayitemtv=view.findViewById(R.id.dayitemtv);
        dayitemtv.setText(dayItemTitle);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
