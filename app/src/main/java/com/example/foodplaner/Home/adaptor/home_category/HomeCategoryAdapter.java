package com.example.foodplaner.Home.adaptor.home_category;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplaner.Home.screen.HomePresenter;
import com.example.foodplaner.databinding.HomeCatagoryItemBinding;

import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.CategoryViewHolder> {

    private List<String> data;
    HomePresenter presenter;

    public HomeCategoryAdapter(List<String> data,HomePresenter presenter) {
        this.data = data;
    }




    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        String item = data.get(position);
        holder.bind(item);
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

        public void bind(String item) {
            binding.textView.setText(item);
            binding.getRoot().setOnClickListener(v -> {presenter.onCategoryClick(item);});
        }
    }
}