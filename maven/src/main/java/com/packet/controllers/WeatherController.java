package com.packet.controllers;

import com.packet.exceptions.InternetException;
import com.packet.interfaces.IController;
import com.packet.interfaces.IView;
import com.packet.model.WeatherModel;
import com.packet.utils.JSONWeatherParser;
import com.packet.utils.WeatherHttpClient;
import org.json.JSONException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by mihai on 11/22/2014.
 * <p/>
 * This class is used by user and manipulates the Model
 */
public class WeatherController implements IController {
    /**
     * The controller needs to interact with the Model
     */

    private WeatherModel mModel;

    /**
     * THe list of views that listen for updates
     */

    private List<IView> mViews;

    /**
     * Local variabiles used for obtain values
     */
    private Random randomGenerator = new Random();
    private String cityName = "Bucharest";

    /**
     * return city name
     *
     * @return
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * set the name of the city used in api url to get real time for state of weather
     *
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Default constructor
     */
    public WeatherController() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getActionCommand().equals(ACTION_UPDATE_RANDOM)) {
            try {
                makeOperation("random", null, null);
            } catch (ClassCastException ec) {
                notifyViews(true, ec.getMessage());
            }
        } else if (e.getActionCommand().equals(ACTION_UPDATE_REAL)) {
            try {
                String location = "?q=" + cityName + ",ro";
                String data = (new WeatherHttpClient()).getWeatherData(location);

                HashMap<String, Object> hashMap = new HashMap<String, Object>((new JSONWeatherParser()).getWeather(data));
                String temp = String.valueOf(hashMap.get("temperature"));
                String windSpeed = String.valueOf(hashMap.get("windSpeed"));
                ;
                makeOperation("real", temp, windSpeed);
            } catch (ClassCastException ec) {
                notifyViews(true, ec.getMessage());
            } catch (JSONException jsonEx) {
                notifyViews(true, jsonEx.getMessage());
            }
            catch (InternetException iE){
                notifyViews(true, iE.getMessage());
            }
        } else if (e.getActionCommand().equals(ACTION_UPDATE_CITY)) {
            try {
                JComboBox cb = (JComboBox) e.getSource();
                String cityName = (String) cb.getSelectedItem();
                System.out.println("Numele orasului este : " + cityName + ".\n");
                setCityName(cityName);
            } catch (ClassCastException ec) {
                notifyViews(true, ec.getMessage());
            }
        }
    }

    /**
     * Adds a view referance in order to interact with it
     *
     * @param view The view from the controller will receive events and send messages
     */
    public void addView(IView view) {
        if (mViews == null) {
            mViews = new ArrayList<IView>();
        }
        mViews.add(view);
    }

    /**
     * Adds a reference to the model, so it can update it
     *
     * @param model The data model reference
     */
    public void addModel(WeatherModel model) {
        mModel = model;
    }

    /**
     * Notifies the views when an message must be displayed
     *
     * @param isError If is true, the message is an error , otherwise
     * @param message The string to be displayed
     */
    private void notifyViews(boolean isError, String message) {
        if (mViews != null && mViews.isEmpty() == false) {
            for (IView view : mViews) {
                view.onMessage(isError, message);
            }
        }
    }

    /**
     * Update the current value for temperature and wind speed
     *
     * @param s If value is "random" will set random values, otherwise real values
     */
    private void makeOperation(String s, String t, String w) {
        if (mModel != null) {

            if (s.equalsIgnoreCase("random") == true) {
                String temp = String.valueOf(randomGenerator.nextInt(40) + "." + randomGenerator.nextInt(9));
                String windSpeed = String.valueOf(randomGenerator.nextInt(20) + "." + randomGenerator.nextInt(9));
                mModel.setTemp(temp);
                mModel.setWindSpeed(windSpeed);
            } else if (s.equalsIgnoreCase("real") == true) {
                mModel.setTempReal(t);
                mModel.setWindSpeedReal(w);
            }

        }
    }
}
