package com.example.simpleweatherapp.apiModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class City implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("population")
    @Expose
    private Integer population;
    @SerializedName("timezone")
    @Expose
    private Integer timezone;
    @SerializedName("sunrise")
    @Expose
    private Integer sunrise;
    @SerializedName("sunset")
    @Expose
    private Integer sunset;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public String getSunrise() {
        return parse(sunrise);
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return parse(sunset);
    }

    private String parse(Integer sec) {
        long millis = sec*1000;
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String result = sdf.format(date);

        return result;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }


    protected City(Parcel in) {
        id = in.readInt();
        name = in.readString();
        coord = (Coord) in.readValue(Coord.class.getClassLoader());
        country = in.readString();
        population =  in.readInt();
        timezone =  in.readInt();
        sunrise = in.readInt();
        sunset =  in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeValue(coord);
        dest.writeString(country);
        dest.writeInt(population);
        dest.writeInt(timezone);
        dest.writeInt(sunrise);
        dest.writeInt(sunset);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}