package com.ruralsourcing.WeatherBot.model;

import com.google.gson.annotations.SerializedName;


/**
 * Class model for observations that hold String values
 */
public class StringObservation {
    @SerializedName("date_time")
    private String dateTime;

    private String value;


    @Override
    public String toString() {
        return value;
    }
}


//TODO: convert from String to Instant