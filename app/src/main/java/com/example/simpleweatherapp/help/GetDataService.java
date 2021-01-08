package com.example.simpleweatherapp.help;

import com.example.simpleweatherapp.apiModel.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("/data/2.5/forecast")
    Call<Forecast> getForecastLatLng(@Query("lat") double latitude,
                                     @Query("lon") double longitude,
                                     @Query("units") String units,
                                     @Query("appid") String key);
    @GET("/data/2.5/forecast")
    Call<Forecast> getForecastSearch(@Query("q") String city,
                                     @Query("units") String units,
                                     @Query("appid") String key);
}
