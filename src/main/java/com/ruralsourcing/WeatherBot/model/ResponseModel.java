package com.ruralsourcing.WeatherBot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Holds the array of station data from Mesonet
 */
public class ResponseModel {

    //TODO: QC Summary object

    @SerializedName("STATION")
    private final Station[] stations;

    public ResponseModel(Station[] stations) {
        this.stations = stations;
    }


    public Station[] getStations() {
        return stations;
    }
    //TODO: Clean up call structure for Station info so it's clearer upon calling
}
