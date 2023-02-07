package com.example.foodplaner.AllMeals.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.R;

import java.util.ArrayList;

public class AllMealsAdapter extends RecyclerView.Adapter<AllMealsAdapter.MyViewHolder>{
    Context context;
    ArrayList<Meal> meals;
    OnPlanClickListener onPlanClickListener;
    OnFavouriteClickListener onFavouriteClickListener;
    public  AllMealsAdapter(OnFavouriteClickListener onFavouriteClickListener,OnPlanClickListener onPlanClickListener){
        this.onFavouriteClickListener=onFavouriteClickListener;
        this.onPlanClickListener=onPlanClickListener;
        meals=new ArrayList<>();
    }
    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.allmeals_item,parent,false);
        MyViewHolder vh=new MyViewHolder(view);
        Log.i("TAG","onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meal current=meals.get(position);
        holder.getMealname().setText(current.getStrMeal());
        holder.getMealarea().setText(current.getStrArea());
        Glide.with(context).load(current.getStrMealThumb()).into(holder.getThumbnail());
        holder.getAddfav().setOnClickListener((view)->{onFavouriteClickListener.onAddFav(current);});
        holder.getAddplan().setOnClickListener(view -> {onPlanClickListener.onAddPlan(current);});


    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mealname;
        TextView mealarea;
        ImageView thumbnail;
        ImageView addfav;
        ImageView addplan;


        public TextView getMealname() {
            return mealname;
        }

        public TextView getMealarea() {
            return mealarea;
        }

        public ImageView getThumbnail() {
            return thumbnail;
        }

        public ImageView getAddfav() {
            return addfav;
        }

        public ImageView getAddplan() {
            return addplan;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealname=itemView.findViewById(R.id.mealname);
            mealarea=itemView.findViewById(R.id.mealarea);
            thumbnail=itemView.findViewById(R.id.mealview);
            addfav=itemView.findViewById(R.id.addfav);
            addplan=itemView.findViewById(R.id.addplan);



        }
    }

}
