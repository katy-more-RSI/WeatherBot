package com.ruralsourcing.WeatherBoy.service;

import com.google.gson.Gson;
import com.ruralsourcing.WeatherBoy.model.ResponseModel;

import java.io.IOException;

public class Weatherboy {

    public static void main(String[] args){

        Gson gson = new Gson();

        String httpResponse ="";

        try {
            httpResponse = HttpCalls.request();
        } catch(IOException e){
            System.out.println(e);
        }

        //currentData holds the entire parsed JSON object
        //It holds all current information as an array of Stations and their data
        ResponseModel currentData = gson.fromJson(httpResponse, ResponseModel.class);

        System.out.println("Done");
    }
}
