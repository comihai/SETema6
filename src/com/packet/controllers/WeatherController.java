package com.packet.controllers;

import com.packet.interfaces.IController;
import com.packet.interfaces.IView;
import com.packet.model.WeatherModel;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
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

    private Random randomGenerator = new Random();

    /**
     * Default constructor
     */
    public WeatherController() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(ACTION_UPDATE)) {
            try {
                  makeOperation();
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
     */
    private void makeOperation() {
        if (mModel != null) {
            // Update the model
            String temp = String.valueOf(randomGenerator.nextInt(40)+"."+randomGenerator.nextInt(9));
            String windSpeed = String.valueOf(randomGenerator.nextInt(20)+"."+randomGenerator.nextInt(9));
            mModel.setTemp(temp);
            mModel.setWindSpeed(windSpeed);

        }
    }
}
