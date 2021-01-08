package com.example.simpleweatherapp.apiModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ThreeHoursForecast implements Parcelable {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private ArrayList<Weather> weather = new ArrayList<>();
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("pop")
    @Expose
    private double pop;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public double getPop() {
        return pop;
    }

    public void setPop(double pop) {
        this.pop = pop;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDtTxt(){
        return dtTxt;
    }

    public String getShortDtTxt() {
        return parseDtHoursMinSec(dtTxt);
    }

    public String getLongDtTxt(){
        return parseDtYearsMonthDays(dtTxt);
    }

    private String parseDtYearsMonthDays(String dtTxt) {
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

    private String parseDtHoursMinSec(String dtTxt) {
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm:ss");
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

    public ThreeHoursForecast(){

    }

    protected ThreeHoursForecast(Parcel in) {
        dt = in.readInt();
        main =(Main) in.readValue(Main.class.getClassLoader());
        weather = in.readArrayList(Weather.class.getClassLoader());
        clouds =(Clouds) in.readValue(Clouds.class.getClassLoader());
        wind = (Wind) in.readValue(Wind.class.getClassLoader());
        visibility = in.readInt();
        pop = in.readDouble();
        sys = (Sys) in.readValue(Sys.class.getClassLoader());
        dtTxt = in.readString();
        city = (City) in.readValue(City.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(dt);
        dest.writeValue(main);
        dest.writeList(weather);
        dest.writeValue(clouds);
        dest.writeValue(wind);
        dest.writeInt(visibility);
        dest.writeDouble(pop);
        dest.writeValue(sys);
        dest.writeString(dtTxt);
        dest.writeValue(city);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ThreeHoursForecast> CREATOR = new Parcelable.Creator<ThreeHoursForecast>() {
        @Override
        public ThreeHoursForecast createFromParcel(Parcel in) {
            return new ThreeHoursForecast(in);
        }

        @Override
        public ThreeHoursForecast[] newArray(int size) {
            return new ThreeHoursForecast[size];
        }
    };

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }
}