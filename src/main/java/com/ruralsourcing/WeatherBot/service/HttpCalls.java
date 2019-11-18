package com.ruralsourcing.WeatherBot.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * A collection of http methods for different public APIs
 */
public class HttpCalls {

    //Mesonet URI for Will Rogers Airport
    private static final String MESONET_OKC_URI = "https://api.synopticdata.com/v2/stations/latest?stid=KOKC&units=temp%7CF,speed%7Cmph,precip%7Cin&vars=weather_condition,air_temp,wind_speed,wind_cardinal_direction,precip_accum_one_hour";
    //TODO: make parameters customizable

    /**
     * Uses a stored URL to contact an API and retrieve data from it
     *
     * @return results from http request as JSON
     * @throws IOException when http request fails
     */
    public static String request(String mesonetApiToken) throws IOException{

        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            //add the API key to the URL from config.properties
            String mesonetUrlWithToken = MESONET_OKC_URI + "&token=" + mesonetApiToken;

            HttpGet httpGet = new HttpGet(mesonetUrlWithToken); //Turns the URI into an HTTP object

            //custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>(){
                @Override
                public String handleResponse(HttpResponse response) throws IOException {

                    int status = response.getStatusLine().getStatusCode();

                    if(status >= 200 && status < 300){
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {
                            String entityString = EntityUtils.toString(entity);
                            return entityString;
                        } else {
                            throw new IOException("No entity found in response");
                        }
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };

            return httpClient.execute(httpGet, responseHandler);
        }
    }
}
