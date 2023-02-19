package com.example.foodplaner.MealMainScreen.View;

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
import com.example.foodplaner.Model.Ingredient;
import com.example.foodplaner.R;

import java.util.ArrayList;

public class MealMainScreenAdapter extends RecyclerView.Adapter<MealMainScreenAdapter.MealViewHolder>{
    Context context;
    ArrayList<Ingredient> ingredients;
    public  MealMainScreenAdapter(){
        ingredients=new ArrayList<>();
    }
    public void setMeals(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    @NonNull
    @Override
    public MealMainScreenAdapter.MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context= parent.getContext();
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.ingredient,parent,false);
        MealMainScreenAdapter.MealViewHolder vh=new MealMainScreenAdapter.MealViewHolder(view);
        Log.i("TAG","onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MealMainScreenAdapter.MealViewHolder holder, int position) {
        Ingredient current=ingredients.get(position);
        holder.getIngredientname().setText(current.getIngredientName());
        holder.getIngredientmeasure().setText(current.getIngredientMeasure());
        Glide.with(context).load(current.getIngredientThumb()).into(holder.getThumbnail());

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
    class MealViewHolder extends RecyclerView.ViewHolder{
        TextView ingredientname;
        TextView ingredientmeasure;
        ImageView thumbnail;


        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientname=itemView.findViewById(R.id.ingredientname);
            ingredientmeasure=itemView.findViewById(R.id.ingredientmeasure);
            thumbnail=itemView.findViewById(R.id.ingredientimg);

        }


        public TextView getIngredientname() {
            return ingredientname;
        }

        public TextView getIngredientmeasure() {
            return ingredientmeasure;
        }

        public ImageView getThumbnail() {
            return thumbnail;
        }



    }
}
