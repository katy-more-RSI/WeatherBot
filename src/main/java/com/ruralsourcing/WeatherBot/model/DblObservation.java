package com.ruralsourcing.WeatherBot.model;

import com.google.gson.annotations.SerializedName;


/**
 * Class model for observations that hold double values
 */
public class DblObservation {

    @SerializedName("date_time")
    private String dateTime;

    private double value;
}

//TODO: convert from String to Instant