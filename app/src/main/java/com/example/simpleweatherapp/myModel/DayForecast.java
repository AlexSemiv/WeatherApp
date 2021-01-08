package com.example.simpleweatherapp.myModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.simpleweatherapp.apiModel.City;
import com.example.simpleweatherapp.apiModel.ThreeHoursForecast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DayForecast implements Parcelable {
    private String dtTxt;

    private ArrayList<ThreeHoursForecast> threeHoursList = new ArrayList<>();

    private City city;

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }

    public DayForecast(City city,ArrayList<ThreeHoursForecast> ThreeHoursForecastsList,String date) {
        this.city = city;
        this.threeHoursList = ThreeHoursForecastsList;
        this.dtTxt = date;
    }

    public String getDtTxt() {
        return parseDtTxtDayMonthYear(dtTxt);
    }


    private String parseDtTxtDayMonthYear(String dtTxt) {
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
        String result;
        try {
            Date date = oldFormat.parse(dtTxt);
            result = newFormat.format(date);
        } catch (ParseException e) {
            result ="";
        }
        return result;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public ArrayList<ThreeHoursForecast> getThreeHoursList() {
        return threeHoursList;
    }

    public void setThreeHoursList(ArrayList<ThreeHoursForecast> threeHoursList) {
        this.threeHoursList = threeHoursList;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    protected DayForecast(Parcel in) {
        threeHoursList = in.readArrayList(ThreeHoursForecast.class.getClassLoader());
        dtTxt = in.readString();
        city = (City) in.readValue(City.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(threeHoursList);
        dest.writeString(dtTxt);
        dest.writeValue(city);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DayForecast> CREATOR = new Parcelable.Creator<DayForecast>() {
        @Override
        public DayForecast createFromParcel(Parcel in) {
            return new DayForecast(in);
        }

        @Override
        public DayForecast[] newArray(int size) {
            return new DayForecast[size];
        }
    };
}