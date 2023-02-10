package com.example.foodplaner.Favourite.adaptor;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplaner.Favourite.screen.FavouritePresenter;
import com.example.foodplaner.Home.screen.HomePresenter;
import com.example.foodplaner.databinding.AllmealsItemBinding;
import com.example.foodplaner.databinding.HomeSelectionItemBinding;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {

    private List<String> data;
    FavouritePresenter presenter;

    public FavouriteAdapter(List<String> data, FavouritePresenter presenter) {
        this.data = data;
        this.presenter =  presenter;
    }




    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        String item = data.get(position);
        holder.bind(item);
    }



    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         AllmealsItemBinding binding = AllmealsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FavouriteViewHolder(binding,presenter);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class FavouriteViewHolder extends RecyclerView.ViewHolder {

        private AllmealsItemBinding binding;
        private FavouritePresenter presenter;
        public FavouriteViewHolder(AllmealsItemBinding binding, FavouritePresenter presenter) {
            super(binding.getRoot());
            this.presenter = presenter;
            this.binding = binding;

        }

        public void bind(String item) {
            binding.getRoot().setOnClickListener(v -> {presenter.onMealClick(item);});
        }
    }
}