package com.ruralsourcing.WeatherBot.model;

import com.google.gson.annotations.SerializedName;


/**
 * Class model for observations that hold double values
 */
public class DoubleObservation {

    @SerializedName("date_time")
    private String dateTime;

    private double value;

    @Override
    public String toString() {
        return Double.toString(value);
    }
}

//TODO: convert from String to Instant