package com.example.foodplaner.List.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplaner.Home.screen.HomePresenter;
import com.example.foodplaner.List.screen.ListPresenter;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.User;
import com.example.foodplaner.R;
import com.example.foodplaner.database.MealLocalSource;
import com.example.foodplaner.databinding.AllmealsItemBinding;
import com.example.foodplaner.databinding.HomeCatagoryItemBinding;
import com.example.foodplaner.databinding.SearshCatagoryItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private ArrayList<Meal> data;
    ListPresenter presenter;

    public ListAdapter( ListPresenter presenter) {
        data=new ArrayList<>();
        this.presenter = presenter;
    }




    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Meal item = data.get(position);
        holder.bind(item);
    }



    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AllmealsItemBinding binding = AllmealsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListViewHolder(binding,presenter);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setList(ArrayList<Meal> meals) {
        data =meals;
        notifyDataSetChanged();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        private AllmealsItemBinding binding;
        private ListPresenter presenter;
        public ListViewHolder(AllmealsItemBinding binding, ListPresenter presenter) {
            super(binding.getRoot());
            this.presenter = presenter;
            this.binding = binding;

        }

        public void bind(Meal item) {
            binding.mealarea.setText(item.strArea);
            binding.mealname.setText(item.strMeal);
            Glide.with(presenter.getContext())
                    .load(item.strMealThumb)
                    .error(R.drawable.wqurxy1511453156)
                    .into(binding.mealview);
            binding.addfav.setOnClickListener(v -> {
                MealLocalSource.getInstance(presenter.getContext()).insertMeal(new User("2",item));
            });
            binding.getRoot().setOnClickListener(v -> presenter.onListClick(item));
        }
    }
}