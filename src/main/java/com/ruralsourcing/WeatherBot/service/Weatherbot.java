package com.ruralsourcing.WeatherBot.service;

import com.ruralsourcing.WeatherBot.model.ResponseModel;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Contains main method for WeatherBot app for Slack
 */
public class Weatherbot {

    /**
     * Uses a stored URL to contact a public API and retrieve/store information
     *
     * @param args ignored
     */
    public static void main(String[] args) throws IOException {

        //new object to hold api tokens from the properties file
        //default constructor reads API tokens from config.properties
        PropertyIO properties = new PropertyIO();

        //initialize the atomic reference holding the data
        final AtomicReference<ResponseModel> ref = new AtomicReference<>();

        // Initialize timer thread that will update the data using API calls one time per hour
        final Timer timer = new Timer("Timer", true);
        timer.scheduleAtFixedRate(new ApiTimerTask(ref, properties.getMesonetApiToken()), 0, TimeUnit.HOURS.toMillis(1));

        final Thread thread = new Thread(new SlackIntegration(ref, properties.getSlackbotApiToken()));
        thread.setDaemon(false);
        thread.setName("Slack Thread");
        thread.start();
    }
}
