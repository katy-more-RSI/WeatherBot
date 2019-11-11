package com.ruralsourcing.WeatherBot.service;

import com.google.gson.Gson;
import com.ruralsourcing.WeatherBot.model.ResponseModel;

import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

public class ApiTimerTask extends TimerTask {

    private final AtomicReference<ResponseModel> reference;

    public ApiTimerTask(final AtomicReference<ResponseModel> reference) {

        this.reference = reference;
    }

    @Override
    public void run() {
        reference.set(ApiCall());
    }

    public static ResponseModel ApiCall(){

        System.out.println("API Thread initiated: updating data from API...");

        Gson gson = new Gson();
        String httpResponse ="";

        try {
            httpResponse = HttpCalls.request();
        } catch(IOException e){
            System.out.println(e);
        }

        //currentData holds the entire parsed JSON object
        //It holds all current information as an array of Stations and their data
        return gson.fromJson(httpResponse, ResponseModel.class);
    }
}
