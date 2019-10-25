package com.ruralsourcing.WeatherBoy.model;

import com.google.gson.annotations.SerializedName;

public class DblObservation {

    @SerializedName("date_time")
    private String dateTime;

    private double value;
}

//TODO: convert from String to Instant