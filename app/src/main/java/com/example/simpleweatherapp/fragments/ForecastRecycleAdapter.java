package com.example.simpleweatherapp.fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleweatherapp.R;
import com.example.simpleweatherapp.databinding.ItemRecycleViewForecastBinding;
import com.example.simpleweatherapp.myModel.DayForecast;

import java.util.List;

public class ForecastRecycleAdapter extends RecyclerView.Adapter<ForecastRecycleAdapter.ViewHolder> {

    private List<DayForecast> list;
    private RecycleFragment.OnItemClickListener listener;

    public ForecastRecycleAdapter(List<DayForecast> list, RecycleFragment.OnItemClickListener listener){
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ForecastRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecycleViewForecastBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_recycle_view_forecast,
                parent,
                false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DayForecast dayForecast = list.get(position);
        holder.itemRecycleViewForecastBinding.setOneDay(dayForecast);
        holder.itemRecycleViewForecastBinding.getRoot().setOnClickListener(v -> listener.onItemClick(dayForecast));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ItemRecycleViewForecastBinding itemRecycleViewForecastBinding;

        public ViewHolder(ItemRecycleViewForecastBinding itemRecycleViewForecastBinding) {
            super(itemRecycleViewForecastBinding.getRoot());
            this.itemRecycleViewForecastBinding = itemRecycleViewForecastBinding;
        }
    }
}
