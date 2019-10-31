package com.ruralsourcing.WeatherBot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Each observation from each of the stations
 */
public class Observation {

    @SerializedName("wind_cardinal_direction_value_1d")
    private StrObservation windDirection;

    @SerializedName("wind_speed_value_1")
    private DblObservation windSpeed;

    @SerializedName("precip_accum_one_hour_value_1")
    private DblObservation precipAccum1Hour;

    @SerializedName("air_temp_value_1")
    private DblObservation airTemperature;

    @SerializedName("weather_condition_value_1d")
    private StrObservation weatherCondition;


    public StrObservation getWindDirection() {
        return windDirection;
    }

    public DblObservation getWindSpeed() {
        return windSpeed;
    }

    public DblObservation getPrecipAccum1Hour() {
        return precipAccum1Hour;
    }

    public DblObservation getAirTemperature() {
        return airTemperature;
    }

    public StrObservation getWeatherCondition() {
        return weatherCondition;
    }
}
