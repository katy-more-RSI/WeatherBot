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
    private static final String MESONET_OKC_URI = "https://api.synopticdata.com/v2/stations/latest?token=a989714fe0724e54bce070a06138ea46&stid=KOKC&recent=60&units=temp%7CF,speed%7Cmph,precip%7Cin";
    //TODO: make parameters customizable

    /**
     * Uses a stored URL to contact an API and retrieve data from it
     *
     * @return results from http request as JSON
     * @throws IOException when http request fails
     */
    public static String request() throws IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try{
            HttpGet httpGet = new HttpGet(MESONET_OKC_URI);

            //custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>(){
                @Override
                public String handleResponse(HttpResponse response) throws IOException {

                    int status = response.getStatusLine().getStatusCode();

                    if(status >= 200 && status < 300){
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };

            return httpClient.execute(httpGet, responseHandler);
        }
        catch (IOException e){
            System.out.println(e);
            return null;
        }
        finally{
            httpClient.close();

        }
    }
}
