package com.ruralsourcing.WeatherBot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Holds information of each of the stations fetched from Mesonet
 */
public class Station {

    @SerializedName("NAME")
    private String name;

    @SerializedName("STID")
    private String stid;

    @SerializedName("OBSERVATIONS")
    private Observation observations;
}
