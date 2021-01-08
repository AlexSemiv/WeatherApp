package com.example.simpleweatherapp.apiModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Forecast implements Parcelable {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Integer message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private ArrayList<ThreeHoursForecast> threeHoursList = new ArrayList<>();
    @SerializedName("city")
    @Expose
    private City city;

    public Forecast(){

    }

    public Forecast(String cod, Integer message, Integer cnt, ArrayList<ThreeHoursForecast> threeHoursList, City city) {
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.threeHoursList = threeHoursList;
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
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

    protected Forecast(Parcel in) {
        cod = in.readString();
        message = in.readInt();
        cnt =  in.readInt();
        threeHoursList = in.readArrayList(ThreeHoursForecast.class.getClassLoader());
        city = (City) in.readValue(City.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cod);
        dest.writeInt(message);
        dest.writeInt(cnt);
        dest.writeList(threeHoursList);
        dest.writeValue(city);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Forecast> CREATOR = new Parcelable.Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel in) {
            return new Forecast(in);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };
}