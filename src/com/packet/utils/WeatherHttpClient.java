package com.packet.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mihai on 11/23/2014.
 * <p/>
 * This class is used for receiving real time data from openweathermap.org
 */
public class WeatherHttpClient {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    /**
     * This method return the neccesary data in string format
     *
     * @param location This indicate the city and the country (ex.format : "?q=Bucharest,ro")
     * @return
     */
    public String getWeatherData(String location) {
        HttpURLConnection con = null;
        InputStream is = null;

        try {
            con = (HttpURLConnection) (new URL(BASE_URL + location)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader rBuffer = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = rBuffer.readLine()) != null) {
                buffer.append(line + "\r\n");
            }
            is.close();
            con.disconnect();
            return buffer.toString();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {

            }
            try {
                con.disconnect();
            } catch (Throwable t) {

            }
        }
        return null;
    }
}
