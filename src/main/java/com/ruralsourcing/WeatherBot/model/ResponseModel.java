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


    public Station[] getStations() {
        return stations;
    }

    //TODO: Clean up call structure for Station info so it's clearer upon calling
}
