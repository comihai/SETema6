package com.packet.model;

import com.packet.interfaces.IModelListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihai on 11/22/2014.
 *
 * THis class is manipulated by controller class and updates the view class
 */

public class WeatherModel {

    //Constants
    public static final double INITIAL_TEMP = 20;
    public static final double INITIAL_WIND_SPEED = 1.5;

    private double temp;
    private double windSpeed;
    private List<IModelListener> listeners;

    /**
     * Constructor without params
     */
    public WeatherModel() {
        temp = INITIAL_TEMP;
        windSpeed = INITIAL_WIND_SPEED;
    }

    /**
     * Adds the view listener to the list
     *
     * @param listener THe model event listener
     */
    public void addModelListener(IModelListener listener)
    {
        if(listeners == null)
        {
            listeners = new ArrayList<IModelListener>();
        }
        listeners.add(listener);
    }

    /**
     *
     * Notifies the views listeners of the changed values
     */
    private void notifyListeners()
    {
        if(listeners != null && listeners.isEmpty() == false)
        {
            for ( IModelListener listener : listeners)
            {
                listener.onUpdate();
            }
        }
    }

    /**
     * return the value of the temperature
     * @return
     */
    public String getTemp() {
        return String.valueOf(temp);
    }

    /**
     * set the value of the temperature
     * @param temp
     */
    public void setTemp(String temp) {
        this.temp = Double.parseDouble(temp);
    }

    /**
     * return the value of the wind speed
     * @return
     */
    public String getWindSpeed() {
        return String.valueOf(windSpeed);
    }

    /**
     * set the value of wind speed
     * @param windSpeed
     */
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = Double.parseDouble(windSpeed);
    }


}
