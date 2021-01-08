package com.example.simpleweatherapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleweatherapp.MainActivity;
import com.example.simpleweatherapp.R;
import com.example.simpleweatherapp.myModel.DayForecast;

import java.util.ArrayList;

public class RecycleFragment extends Fragment {
    public interface OnItemClickListener{
        void onItemClick(DayForecast dayForecast);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArrayList<DayForecast> list = getArguments().getParcelableArrayList(MainActivity.DAY_FORECAST);

        View view = inflater.inflate(R.layout.fragment_recycle,container,false);

        if(list!=null) {
            OnItemClickListener listener = (OnItemClickListener) getActivity();

            RecyclerView recyclerView = view.findViewById(R.id.recycleFragment);
            ForecastRecycleAdapter adapter = new ForecastRecycleAdapter(list, listener);

            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }else{
            Toast.makeText(getContext(), "RecyclerFragment Error", Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}
