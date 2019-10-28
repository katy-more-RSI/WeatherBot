package com.ruralsourcing.WeatherBoy.model;

import com.google.gson.annotations.SerializedName;


//DblObservation is the class model to hold the observations
//where the value is of type double
public class DblObservation {

    @SerializedName("date_time")
    private String dateTime;

    private double value;
}

//TODO: convert from String to Instant