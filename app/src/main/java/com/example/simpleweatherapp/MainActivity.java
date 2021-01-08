package com.example.simpleweatherapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.simpleweatherapp.apiModel.Forecast;
import com.example.simpleweatherapp.fragments.MyMapFragment;
import com.example.simpleweatherapp.fragments.RecycleFragment;
import com.example.simpleweatherapp.fragments.ViewPagerFragment;
import com.example.simpleweatherapp.help.GetDataService;
import com.example.simpleweatherapp.help.RetrofitClientInstance;
import com.example.simpleweatherapp.myModel.DayForecast;
import com.example.simpleweatherapp.myModel.ParseApiForecast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements
        MyMapFragment.onMyMapListener,
        RecycleFragment.OnItemClickListener{
    private static final String MAP_FRAGMENT = "map_fragment";
    public static final String FORECAST_FRAGMENT = "forecast_fragment";
    private static final String RECYCLE_FRAGMENT = "recycle_fragment";
    private static final String API_KEY = "9db8ad32e88e874c9d211366f8394486";
    public static final String START_LATITUDE = "start_latitude";
    public static final String START_LONGITUDE = "start_longitude";
    public static final String FORECAST = "forecast";
    public static final String DAY_FORECAST = "day_forecast";
    private static final String DAY_RECYCLE_FRAGMENT = "day_recycle_fragment";

    public static boolean isRussianRequest = false;

    private ProgressBar progressBar;
    private LinearLayout forecastLayout,recyclerLayout;
    private SearchView searchView;
    private double mLatitude = 55.7522200;  // moscow
    private double mLongitude = 37.6155600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        forecastLayout = findViewById(R.id.forecastLayout);
        recyclerLayout = findViewById(R.id.recycleLayout);

        MyMapFragment mapFragment = new MyMapFragment();

        Bundle bundleMap = new Bundle();
        bundleMap.putDouble(START_LATITUDE, mLatitude);// отправить стартовые координаты и вывести для них погоду
        bundleMap.putDouble(START_LONGITUDE, mLongitude);

        mapFragment.setArguments(bundleMap);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mapLayout,mapFragment,MAP_FRAGMENT)
                .commit();

        getForecast(mLatitude, mLongitude);
    }

    // coordinates from MyMapFragment
    @Override
    public void getCurrentLatLng(double latitude, double longitude) {
        getForecast(latitude, longitude);
    }

    // get dayForecast from recyclerView for new info activity
    @Override
    public void onItemClick(DayForecast dayForecast) {
        Intent intent = new Intent(this,DayInfoActivity.class);
        intent.putExtra(DAY_FORECAST,dayForecast);
        startActivity(intent);
    }

    private void getForecast(double latitude, double longitude) {
        loadingOn();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Forecast> forecastCall;

        if(isRussianRequest) {
            forecastCall = service.getForecastLatLng(latitude, longitude, getString(R.string.metric), API_KEY); // degrees
        }else{
            forecastCall = service.getForecastLatLng(latitude, longitude, getString(R.string.imperial), API_KEY); // fareng
        }

        forecastCall.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                inflateFragments(response.body());
                loadingOff();
            }
            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Retrofit onFailure() error: "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void inflateFragments(Forecast forecast){
        ArrayList<DayForecast> listOfDayForecast = ParseApiForecast.toListOfDayForecast(forecast);

        FragmentManager manager = getSupportFragmentManager();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(DAY_FORECAST,listOfDayForecast);

        RecycleFragment recycleFragment = new RecycleFragment();
        recycleFragment.setArguments(bundle);
        manager.beginTransaction()
                .replace(R.id.recycleLayout,recycleFragment,RECYCLE_FRAGMENT)
                .commit();

        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        viewPagerFragment.setArguments(bundle);
        manager.beginTransaction()
                .replace(R.id.forecastLayout,viewPagerFragment,FORECAST_FRAGMENT)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main_activity,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.btnSearch).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return doSearch(query);
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(getApplicationContext(), "Typing search text"+newText, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Search click", Toast.LENGTH_SHORT).show();
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private boolean doSearch(String newText) {
        if (newText == null || newText.isEmpty()) {
            return false;
        }
        Toast.makeText(this, "Search text: " + newText, Toast.LENGTH_SHORT).show();

        //getForecastActionBarSearch(newText);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnRefresh:{
                Toast.makeText(this, "Refresh button clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.btnSettings:{
                Toast.makeText(this, "Settings button clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private void loadingOn(){
        progressBar.setVisibility(View.VISIBLE);
        forecastLayout.setVisibility(View.INVISIBLE);
        recyclerLayout.setVisibility(View.INVISIBLE);
    }
    private void loadingOff(){
        progressBar.setVisibility(View.INVISIBLE);
        forecastLayout.setVisibility(View.VISIBLE);
        recyclerLayout.setVisibility(View.VISIBLE);
    }
}