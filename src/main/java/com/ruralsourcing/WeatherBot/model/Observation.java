package com.ruralsourcing.WeatherBot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Each observation from each of the stations
 */
public class Observation {

    @SerializedName("wind_cardinal_direction_value_1d")
    private StringObservation windDirection;

    @SerializedName("wind_speed_value_1")
    private DoubleObservation windSpeed;

    @SerializedName("precip_accum_one_hour_value_1")
    private DoubleObservation precipAccum1Hour;

    @SerializedName("air_temp_value_1")
    private DoubleObservation airTemperature;

    @SerializedName("weather_condition_value_1d")
    private StringObservation weatherCondition;

    public StringObservation getWindDirection() {
        return windDirection;
    }

    public DoubleObservation getWindSpeed() {
        return windSpeed;
    }

    public DoubleObservation getPrecipAccum1Hour() {
        return precipAccum1Hour;
    }

    public DoubleObservation getAirTemperature() {
        return airTemperature;
    }

    public StringObservation getWeatherCondition() {
        return weatherCondition;
    }
}
