package com.ruralsourcing.WeatherBoy.model;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {
    //TODO: QC Summary object
    @SerializedName("STATION")
    private Station[] stations;
    //TODO: Summary object
}
