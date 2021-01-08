package com.example.simpleweatherapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.simpleweatherapp.MainActivity;
import com.example.simpleweatherapp.apiModel.ThreeHoursForecast;
import com.example.simpleweatherapp.databinding.FragmentCurrentForecastBinding;

public class CurrentForecastFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ThreeHoursForecast threeHoursForecast = getArguments().getParcelable(MainActivity.DAY_FORECAST);

        FragmentCurrentForecastBinding binding =  FragmentCurrentForecastBinding.inflate(inflater,container,false);

        if(threeHoursForecast!=null) {
            binding.setCurrent(threeHoursForecast);
        }else{
            Toast.makeText(getContext(), "CurrentForecastFragment error", Toast.LENGTH_SHORT).show();
        }

        return binding.getRoot();
    }
}
