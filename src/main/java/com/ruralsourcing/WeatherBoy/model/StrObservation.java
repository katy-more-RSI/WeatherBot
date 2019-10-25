package com.ruralsourcing.WeatherBoy.model;

import com.google.gson.annotations.SerializedName;

public class StrObservation {
    @SerializedName("date_time")
    private String dateTime;

    private String value;


}


//TODO: convert from String to Instant