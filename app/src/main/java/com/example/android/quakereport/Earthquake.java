package com.example.android.quakereport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {

    private double mag;
    private String place;
    private String date; //format - month date, year
    private String time; //format - hour:min AM/PM
    private String url;

    public Earthquake(double mag, String place, long timeInMilliseconds) {
        this.mag = mag;
        this.place = place;

        Date dateObject = new Date(timeInMilliseconds);

        SimpleDateFormat dateFormatter = (SimpleDateFormat) SimpleDateFormat.getDateInstance(DateFormat.MEDIUM);
        date = dateFormatter.format(dateObject);

        SimpleDateFormat timeFormatter = (SimpleDateFormat) SimpleDateFormat.getTimeInstance(DateFormat.SHORT);
        time = timeFormatter.format(dateObject);
    }

    public double getMag() {
        return mag;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;

    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
