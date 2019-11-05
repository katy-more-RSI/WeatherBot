package com.ruralsourcing.WeatherBot.service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.model.event.MessageEvent;
import com.github.seratch.jslack.api.rtm.RTMClient;
import com.github.seratch.jslack.api.rtm.message.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ruralsourcing.WeatherBot.model.ResponseModel;


/**
 *
 */
public class SlackIntegration {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String TOKEN = System.getenv("SLACK_BOT_API_TOKEN");

    public static void rtmMessage(ResponseModel currentData) {

        final Slack slack = Slack.getInstance();

        try (RTMClient rtm = new Slack().rtm(TOKEN)) {
            rtm.addMessageHandler((message) -> {
                JsonObject json = JsonParser.parseString(message).getAsJsonObject();

                if (json.get("type") == null) {
                    return;
                }

                final String type = json.get("type").getAsString();

                if(!"message".equals(type)){
                    return;
                }

                final MessageEvent event = gson.fromJson(json, MessageEvent.class);

                //do stuff in response:
                System.out.println(event);

                if(event.getText().contains("@UPXMYNW93") || event.getChannel().startsWith("D")){
                    System.out.println("Pinged! Responding...");
                    rtm.sendMessage(Message.builder()
                            .id(System.currentTimeMillis())
                            .channel(event.getChannel())
                            .text(currentDataString(currentData))
                            .build().toJSONString());
                } else {
                    System.out.println("Not pinged");
                }

            });


            rtm.connect();

            while(true){
                Thread.sleep(100);
            }

        } catch (Exception e) {
            System.out.println("Issue with the SlackIntegration class: \n" + e);
        }
    }

    public static String currentDataString(ResponseModel currentData){
        String response = "";

        //Note: There is an issue right now where the weather condition (e.g. Snowy, Sunny, Overcast, etc) has not been
        //      updated since October 31, so it is being omitted from the weather response for now

        response += "Hello! Here are the current observations from " + currentData.getStations()[0].getName() + "\n";
        //response += "The weather outside is "
        //        + currentData.getStations()[0].getObservations().getWeatherCondition() + "\n";
        response += "*Air temperature: * " + currentData.getStations()[0].getObservations().getAirTemperature()
                + " degrees F" + "\n";
        response += "*Wind speed: * " + currentData.getStations()[0].getObservations().getWindSpeed() + " mph" + "\n";
        response += "*Wind direction: * " + currentData.getStations()[0].getObservations().getWindDirection() + "\n";
        response += "*Precipitation over the last hour: * " + currentData.getStations()[0].getObservations()
                .getPrecipAccum1Hour() + " inches" + "\n";

        System.out.println(response);

        return response;
    }
}
