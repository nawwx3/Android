package com.weltek.funshine.model;

import android.util.Log;

/**
 * Created by Nathan Welch on 6/23/2017.
 */

public class DailyWeatherReport {
    private String cityName;
    private String country;
    private int currentTemp;
    private int maxTemp;
    private int mintTenp;
    private String weather;
    private String formattedDate;

    public static final String  WEATHER_TYPE_CLOUDS = "Clouds";
    public static final String  WEATHER_TYPE_CLEAR = "Clear";
    public static final String  WEATHER_TYPE_RAIN = "Rain";
    public static final String  WEATHER_TYPE_WIND = "Wind";
    public static final String  WEATHER_TYPE_SNOW = "Snow";

    public DailyWeatherReport(String cityName, String country, int currentTemp, int maxTemp, int mintTenp, String weather, String rawDate) {
        this.cityName = cityName;
        this.country = country;
        this.currentTemp = currentTemp;
        this.maxTemp = maxTemp;
        this.mintTenp = mintTenp;
        this.weather = weather;
        this.formattedDate = rawDateToPretty(rawDate);
    }

    public String getCityName() { return cityName; }
    public String getCountry() { return country; }
    public int getCurrentTemp() { return currentTemp; }
    public int getMaxTemp() { return maxTemp; }
    public int getMintTenp() { return mintTenp; }
    public String getWeather() { return weather; }
    public String getFormattedDate() { return formattedDate; }

    public String rawDateToPretty(String rawDate) {
        //convert rawDate to formattedDate

        String month = "";
        String day = "";
        String[] monthDay = rawDate.split(" ");
        Log.v("DATE", "month: " + monthDay[0] + " day: " + monthDay[1]);

        day = monthDay[1];
        switch(monthDay[0]) {
            case "01":
                month = "Jan";
                break;
            case "02":
                month = "Feb";
                break;
            case "03":
                month = "Mar";
                break;
            case "04":
                month = "Apr";
                break;
            case "05":
                month = "May";
                break;
            case "06":
                month = "Jun";
                break;
            case "07":
                month = "Jul";
                break;
            case "08":
                month = "Aug";
                break;
            case "09":
                month = "Sep";
                break;
            case "10":
                month = "Oct";
                break;
            case "11":
                month = "Nov";
                break;
            default :
                month = "Dec";

        }

        return month + " " + day;
    }
}
