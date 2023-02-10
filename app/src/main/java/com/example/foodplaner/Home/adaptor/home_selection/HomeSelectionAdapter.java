package com.example.foodplaner.Home.adaptor.home_selection;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplaner.Home.screen.HomePresenter;
import com.example.foodplaner.databinding.HomeCatagoryItemBinding;
import com.example.foodplaner.databinding.HomeSelectionItemBinding;

import java.util.List;

public class HomeSelectionAdapter extends RecyclerView.Adapter<HomeSelectionAdapter.SelectionViewHolder> {

    private List<String> data;
    HomePresenter presenter;

    public HomeSelectionAdapter(List<String> data,HomePresenter presenter) {
        this.data = data;
        presenter = presenter;

    }




    @Override
    public void onBindViewHolder(@NonNull SelectionViewHolder holder, int position) {
        String item = data.get(position);
        holder.bind(item);
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

        public void bind(String item) {
            binding.getRoot().setOnClickListener(v -> {presenter.onSelectionClick(item);});
        }
    }
}