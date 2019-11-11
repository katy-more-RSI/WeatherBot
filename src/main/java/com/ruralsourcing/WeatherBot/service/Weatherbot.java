package com.ruralsourcing.WeatherBot.service;

import com.ruralsourcing.WeatherBot.model.ResponseModel;

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
    public static void main(String[] args) {

        //initialize the atomic reference holding the data
        final AtomicReference<ResponseModel> ref = new AtomicReference<>(ApiTimerTask.ApiCall());

        // Initialize timer thread that will update the data using API calls one time per hour
        final Timer timer = new Timer("Timer", true);
        timer.scheduleAtFixedRate(new ApiTimerTask(ref), 0, TimeUnit.HOURS.toMillis(1));

        final Thread thread = new Thread(new SlackIntegration(ref));
        thread.setDaemon(false);
        thread.setName("Slack Thread");
        thread.start();
    }
}
