package com.ruralsourcing.WeatherBot.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyIO {

    private String mesonetApiToken;
    private String slackbotApiToken;
    private InputStream inputStream;


    public PropertyIO() throws IOException{
        try{
            Properties properties = new Properties();
            String propertiesFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);

            if(inputStream != null){
                properties.load(inputStream);
            }else{
                throw new FileNotFoundException("property file '" + propertiesFileName + "' not found.");
            }

            mesonetApiToken = properties.getProperty("MESONET_API_TOKEN");
            slackbotApiToken = properties.getProperty("SLACK_BOT_API_TOKEN");
        }catch(Exception e){
            System.out.println(e);
            //TODO: change this to log
        }finally{
            inputStream.close();
        }
    }

    //getters and setters
    public String getMesonetApiToken() {
        return mesonetApiToken;
    }

    public String getSlackbotApiToken() {
        return slackbotApiToken;
    }
}
