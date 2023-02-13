package com.example.foodplaner.Home.adaptor.home_category;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplaner.Home.screen.HomePresenter;
import com.example.foodplaner.Model.Category;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.R;
import com.example.foodplaner.databinding.HomeCatagoryItemBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.CategoryViewHolder> {

    private List<Category> data;
    HomePresenter presenter;

    public HomeCategoryAdapter(HomePresenter presenter) {
       this.presenter = presenter;
       data = new ArrayList<>();
    }




    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category item = data.get(position);
        holder.bind(item);
    }

    public void setList(List<Category> categories){
        data =categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeCatagoryItemBinding binding = HomeCatagoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryViewHolder(binding,presenter);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private HomeCatagoryItemBinding binding;
        private HomePresenter presenter;
        public CategoryViewHolder(HomeCatagoryItemBinding binding,HomePresenter presenter) {
            super(binding.getRoot());
            this.presenter = presenter;
            this.binding = binding;

        }

        public void bind(Category item) {
            binding.textView.setText(item.getStrCategory());
            Glide.with(presenter.getContext())
                    .load(item.getStrCategoryThumb())
                    .error(R.drawable.beef)
                    .into(binding.imageView);
            binding.getRoot().setOnClickListener(v -> {presenter.onCategoryClick(item.getStrCategory());});
        }
    }
}