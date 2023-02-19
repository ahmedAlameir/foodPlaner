package com.example.foodplaner.Favourite.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplaner.Favourite.screen.FavouritePresenter;
import com.example.foodplaner.Home.screen.HomePresenter;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.User;
import com.example.foodplaner.R;
import com.example.foodplaner.database.MealLocalSource;
import com.example.foodplaner.databinding.AllmealsItemBinding;
import com.example.foodplaner.databinding.HomeSelectionItemBinding;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {

    private List<Meal> data;
    FavouritePresenter presenter;

    public FavouriteAdapter( FavouritePresenter presenter) {
        data = new ArrayList<>();
        this.presenter =  presenter;
    }


    public void setList(ArrayList<Meal> meals) {
        data =meals;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        Meal item = data.get(position);
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

        public void bind(Meal item) {
            binding.mealarea.setText(item.strArea);
            binding.mealname.setText(item.strMeal);
            binding.addfav.setImageResource(R.drawable.trash);
            binding.addfav.setOnClickListener(v -> {
                presenter.deleteItem(item);
            });
            binding.addplan.setVisibility(View.GONE);
            binding.textView5.setVisibility(View.GONE);
            Glide.with(presenter.getContext())
                    .load(item.strMealThumb)
                    .error(R.drawable.wqurxy1511453156)
                    .into(binding.mealview);

            binding.getRoot().setOnClickListener(v -> presenter.onMealClick(item));
        }
    }
}