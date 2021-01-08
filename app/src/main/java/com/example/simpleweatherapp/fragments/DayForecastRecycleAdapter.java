package com.example.simpleweatherapp.fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleweatherapp.R;
import com.example.simpleweatherapp.apiModel.ThreeHoursForecast;
import com.example.simpleweatherapp.databinding.ItemRecycleViewDayforecastBinding;

import java.util.List;

public class DayForecastRecycleAdapter extends RecyclerView.Adapter<DayForecastRecycleAdapter.ViewHolder> {
    private List<ThreeHoursForecast> list;

    public DayForecastRecycleAdapter(List<ThreeHoursForecast> list){
        this.list = list;
    }

    @NonNull
    @Override
    public DayForecastRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecycleViewDayforecastBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_recycle_view_dayforecast,
                parent,
                false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThreeHoursForecast item = list.get(position);
        holder.itemRecycleViewDayforecastBinding.setThreeHours(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public ItemRecycleViewDayforecastBinding itemRecycleViewDayforecastBinding;

        public ViewHolder(ItemRecycleViewDayforecastBinding itemRecycleViewDayforecastBinding) {
            super(itemRecycleViewDayforecastBinding.getRoot());
            this.itemRecycleViewDayforecastBinding = itemRecycleViewDayforecastBinding;
        }
    }
}
