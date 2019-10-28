package com.ruralsourcing.WeatherBoy.model;

import com.google.gson.annotations.SerializedName;


//StrObservation is the class model to hold the observations
//where the value is of type String
public class StrObservation {
    @SerializedName("date_time")
    private String dateTime;

    private String value;


}


//TODO: convert from String to Instant