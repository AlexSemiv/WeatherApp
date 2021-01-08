package com.example.simpleweatherapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleweatherapp.fragments.DayForecastRecycleAdapter;
import com.example.simpleweatherapp.myModel.DayForecast;

public class DayInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_info);

        DayForecast dayForecast = getIntent().getExtras().getParcelable(MainActivity.DAY_FORECAST);

        String title = dayForecast.getCity().getName()+"  "+dayForecast.getThreeHoursList().get(0).getLongDtTxt();
        getSupportActionBar().setTitle(title);

        if(dayForecast != null){
            RecyclerView recyclerDayForecast = findViewById(R.id.dayForecastRecycle);
            DayForecastRecycleAdapter adapter = new DayForecastRecycleAdapter(dayForecast.getThreeHoursList());

            recyclerDayForecast.setHasFixedSize(true);
            recyclerDayForecast.setLayoutManager(new LinearLayoutManager(this));
            recyclerDayForecast.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            recyclerDayForecast.setAdapter(adapter);
        }
    }
}