package com.example.foodplaner.Home.adaptor.home_selection;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplaner.Home.screen.HomePresenter;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.R;
import com.example.foodplaner.databinding.HomeCatagoryItemBinding;
import com.example.foodplaner.databinding.HomeSelectionItemBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeSelectionAdapter extends RecyclerView.Adapter<HomeSelectionAdapter.SelectionViewHolder> {

    private List<Meal> data;
    HomePresenter presenter;

    public HomeSelectionAdapter(HomePresenter presenter) {
        this.presenter = presenter;
        data = new ArrayList<>();
    }




    @Override
    public void onBindViewHolder(@NonNull SelectionViewHolder holder, int position) {
        Meal item = data.get(position);
        holder.bind(item);
    }

    public void setList(List<Meal> meal){
        this.data = meal;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public SelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeSelectionItemBinding binding = HomeSelectionItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SelectionViewHolder(binding,presenter);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class SelectionViewHolder extends RecyclerView.ViewHolder {

        private HomeSelectionItemBinding binding;
        private HomePresenter presenter;
        public SelectionViewHolder(HomeSelectionItemBinding binding, HomePresenter presenter) {
            super(binding.getRoot());
            this.presenter = presenter;
            this.binding = binding;

        }

        public void bind(Meal item) {
            binding.areaName.setText(item.strArea);
            binding.foodName.setText(item.strMeal);
            binding.typeName.setText(item.strCategory);
            Glide.with(presenter.getContext())
                    .load(item.strMealThumb)
                    .error(R.drawable.wqurxy1511453156)
                    .into(binding.foodImage);
            binding.getRoot().setOnClickListener(v -> {presenter.onSelectionClick(item);});
        }
    }
}