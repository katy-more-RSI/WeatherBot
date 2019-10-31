package com.ruralsourcing.WeatherBot.service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.model.event.MessageEvent;
import com.github.seratch.jslack.api.rtm.RTMClient;
import com.github.seratch.jslack.api.rtm.message.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 *
 */
public class SlackIntegration {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String TOKEN = System.getenv("SLACK_BOT_API_TOKEN");

    public static void rtmMessage() {

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
                            .text("Hi")
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
}
