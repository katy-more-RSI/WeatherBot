package com.ruralsourcing.WeatherBot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Holds the array of station data from Mesonet
 */
public class ResponseModel {
    //TODO: QC Summary object
    @SerializedName("STATION")
    private Station[] stations;
    //TODO: Summary object
}
