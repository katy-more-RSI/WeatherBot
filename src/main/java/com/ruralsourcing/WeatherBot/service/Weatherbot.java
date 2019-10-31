package com.ruralsourcing.WeatherBot.service;

import com.google.gson.Gson;
import com.ruralsourcing.WeatherBot.model.ResponseModel;

import java.io.IOException;


/**
 * Contains main method for WeatherBot app for Slack
 */
public class Weatherbot {

    /**
     * Uses a stored URL to contact a public API and retrieve/store information
     *
     * @param args ignored
     */
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


        //System.out.println("Printing..." + System.getenv("SLACK_BOT_API_TOKEN"));
        SlackIntegration.rtmMessage(currentData);


        System.out.println("Done");
    }
}
