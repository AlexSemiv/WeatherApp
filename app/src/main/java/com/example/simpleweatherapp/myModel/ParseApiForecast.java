package com.example.simpleweatherapp.myModel;

import com.example.simpleweatherapp.apiModel.Forecast;
import com.example.simpleweatherapp.apiModel.ThreeHoursForecast;

import java.util.ArrayList;

public class ParseApiForecast {
    public static ArrayList<DayForecast> toListOfDayForecast(Forecast forecast) {
        ArrayList<DayForecast> result = new ArrayList<>();
        ArrayList<ThreeHoursForecast> listOfThreeHoursForecast = new ArrayList<>();
        ThreeHoursForecast item;

        String date = forecast.getThreeHoursList().get(0).getLongDtTxt();
        for(ThreeHoursForecast threeHoursForecast:forecast.getThreeHoursList()){
            if(!threeHoursForecast.getDtTxt().contains(date)){
                DayForecast dayForecast = new DayForecast(forecast.getCity(),listOfThreeHoursForecast,forecast.getThreeHoursList().get(0).getDtTxt());
                result.add(dayForecast);

                listOfThreeHoursForecast = new ArrayList<>();
                date = threeHoursForecast.getLongDtTxt();
            }
            item = threeHoursForecast;
            item.setCity(forecast.getCity());
            listOfThreeHoursForecast.add(item);
        }

        return result;
    }
}
