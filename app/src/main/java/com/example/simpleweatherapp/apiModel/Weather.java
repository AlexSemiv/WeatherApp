package com.example.simpleweatherapp.apiModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.simpleweatherapp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;

    public int getIconId(){
        int iconId = R.drawable.clear_day;

        switch (icon){
            case "01d":
                iconId=R.drawable.clear_day;break;
            case "01n":
                iconId = R.drawable.clear_night;break;
            case "02d":
                iconId=R.drawable.partly_cloudy;break;
            case "02n":
            case "03n":
            case "04n":
                iconId=R.drawable.cloudy_night;break;
            case "03d":
            case "04d":
                iconId = R.drawable.cloudy;break;
            case "09d":
            case "09n":
            case "10d":
            case "10n":
            case "11d":
            case "11n":
                iconId=R.drawable.rain; break;
            case "13d":
                iconId=R.drawable.snow;break;
            case "13n":
                iconId=R.drawable.sleet;break;
            case "50d":
            case "50n":
                iconId=R.drawable.fog;break;
        }

        return iconId;
    }

    public static int getIconId(String icon) {
        int iconId = R.drawable.clear_day;

        switch (icon){
            case "01d":
                iconId=R.drawable.clear_day;break;
            case "01n":
                iconId = R.drawable.clear_night;break;
            case "02d":
                iconId=R.drawable.partly_cloudy;break;
            case "02n":
            case "03n":
            case "04n":
                iconId=R.drawable.cloudy_night;break;
            case "03d":
            case "04d":
                iconId = R.drawable.cloudy;break;
            case "09d":
            case "09n":
            case "10d":
            case "10n":
            case "11d":
            case "11n":
                iconId=R.drawable.rain; break;
            case "13d":
                iconId=R.drawable.snow;break;
            case "13n":
                iconId=R.drawable.sleet;break;
            case "50d":
            case "50n":
                iconId=R.drawable.fog;break;
        }

        return iconId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(main);
        dest.writeString(description);
        dest.writeString(icon);
    }

    public Weather(Parcel in){
        id=in.readInt();
        main = in.readString();
        description=in.readString();
        icon=in.readString();
    }

    public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>(){

        @Override
        public Weather createFromParcel(Parcel source) {
            return new Weather(source);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };
}