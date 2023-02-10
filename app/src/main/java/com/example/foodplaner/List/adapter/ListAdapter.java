package com.example.foodplaner.List.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplaner.Home.screen.HomePresenter;
import com.example.foodplaner.List.screen.ListPresenter;
import com.example.foodplaner.databinding.HomeCatagoryItemBinding;
import com.example.foodplaner.databinding.SearshCatagoryItemBinding;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<String> data;
    ListPresenter presenter;

    public ListAdapter(List<String> data, ListPresenter presenter) {
        this.data = data;
        presenter = presenter;
    }




    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        String item = data.get(position);
        holder.bind(item);
    }



    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SearshCatagoryItemBinding binding = SearshCatagoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListViewHolder(binding,presenter);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ListViewHolder extends RecyclerView.ViewHolder {

        private SearshCatagoryItemBinding binding;
        private ListPresenter presenter;
        public ListViewHolder(SearshCatagoryItemBinding binding, ListPresenter presenter) {
            super(binding.getRoot());
            this.presenter = presenter;
            this.binding = binding;

        }

        public void bind(String item) {
            binding.getRoot().setOnClickListener(v -> {presenter.onListClick(item);});
        }
    }
}