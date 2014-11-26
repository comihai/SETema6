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

    private double mTemp;
    private double mWindSpeed;
    private double mTempReal;
    private double mWindSpeedReal;
    private List<IModelListener> listeners;

    /**
     * Constructor without params
     */
    public WeatherModel() {
        mTemp = INITIAL_TEMP;
        mWindSpeed = INITIAL_WIND_SPEED;
        mTempReal = 0;
        mWindSpeedReal = 0;
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
        return String.valueOf(mTemp);
    }

    /**
     * set the value of the temperature
     * @param temp
     */
    public void setTemp(String temp) {
        this.mTemp = Double.parseDouble(temp);
        notifyListeners();
    }

    /**
     * return the value of the wind speed
     * @return
     */
    public String getWindSpeed() {
        return String.valueOf(mWindSpeed);
    }

    /**
     * set the value of wind speed
     * @param windSpeed
     */
    public void setWindSpeed(String windSpeed) {
        this.mWindSpeed = Double.parseDouble(windSpeed);
        notifyListeners();
    }

    /**
     * return the value of the real wind speed
     * @return
     */
    public String getTempReal() {
        return String.valueOf(mTempReal);
    }

    /**
     * set the value of temperature
     * @param mTempReal
     */
    public void setTempReal(String mTempReal) {
        this.mTempReal = Double.parseDouble(mTempReal);
        notifyListeners();
    }

    /**
     * return the value of the real temperature
     * @return
     */
    public String getWindSpeedReal() {
        return String.valueOf(mWindSpeedReal);
    }

    /**
     * set the value of wind speed
     * @param mWindSpeedReal
     */
    public void setWindSpeedReal(String mWindSpeedReal) {
        this.mWindSpeedReal = Double.parseDouble(mWindSpeedReal);
        notifyListeners();
    }
}
