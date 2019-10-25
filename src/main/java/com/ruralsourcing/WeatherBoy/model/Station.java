package com.ruralsourcing.WeatherBoy.model;

import com.google.gson.annotations.SerializedName;

public class Station {

    @SerializedName("NAME")
    private String name;

    @SerializedName("STID")
    private String stid;

    @SerializedName("OBSERVATIONS")
    private Observation observations;
}
