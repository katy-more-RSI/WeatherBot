package com.ruralsourcing.WeatherBot.service;

import com.google.gson.Gson;
import com.ruralsourcing.WeatherBot.model.ResponseModel;

import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

public class ApiTimerTask extends TimerTask {

    private final AtomicReference<ResponseModel> reference;
    private final String apiToken;

    public ApiTimerTask(final AtomicReference<ResponseModel> reference, final String apiToken) {

        this.reference = reference;
        this.apiToken = apiToken;
    }

    @Override
    public void run() {
        System.out.println("API Thread initiated: updating data from API...");

        Gson gson = new Gson();
        String httpResponse ="";

        try {
            httpResponse = HttpCalls.request(apiToken);
        } catch(IOException e){
            System.out.println(e);
        }

        //currentData holds the entire parsed JSON object
        //It holds all current information as an array of Stations and their data
        reference.set(gson.fromJson(httpResponse, ResponseModel.class));
    }
}
