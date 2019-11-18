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

import java.util.concurrent.atomic.AtomicReference;

/**
 * This class contains methods needed to interact with the Slack API.
 */
public class SlackIntegration implements Runnable {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final AtomicReference<ResponseModel> reference;
    private final String slackbotApiToken;

    /**
     * Constructor that will be used almost exclusively
     *
     * @param reference  the atomic reference for the current weather data object
     * @param slackbotApiToken  API token needed to send/receive messages with the Slack API
     */
    public SlackIntegration(AtomicReference<ResponseModel> reference, final String slackbotApiToken) {
        this.reference = reference;
        this.slackbotApiToken = slackbotApiToken;
    }

    @Override
    public void run() {
        rtmMessage();
    }

    private void rtmMessage() {
        final Slack slack = Slack.getInstance();

        try (final RTMClient rtm = slack.rtm(slackbotApiToken)) {


            //Message handler
            rtm.addMessageHandler(message -> {
                final ResponseModel currentData = reference.get();

                final String slackUserHandle = rtm.getConnectedBotUser().getId();

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


                //If the message came from a DM or WeatherBot was pinged using @, respond
                if(event.getText().contains("@" + slackUserHandle) || event.getChannel().startsWith("D")){
                    rtm.sendMessage(Message.builder()
                            .id(System.currentTimeMillis())
                            .channel(event.getChannel())
                            .text(currentDataString(currentData))
                            .build().toJSONString());
                }
            });

            rtm.connect();

            while (true) {
                try {
                    Thread.sleep(500);
                }catch(final InterruptedException e){
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Issue with the SlackIntegration class: ");
            e.printStackTrace();
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

        //System.out.println(response);

        return response;
    }
}
