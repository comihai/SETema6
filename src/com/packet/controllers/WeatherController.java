package com.packet.controllers;

import com.packet.interfaces.IController;
import com.packet.interfaces.IView;
import com.packet.model.WeatherModel;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

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
     * Default constructor
     */
    public WeatherController() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //check what action should be taken
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
     * @param temp      Value for temp
     * @param windSpeed Value for wind speed
     */
    private void makeOperation(String temp, String windSpeed) {
        if (mModel != null) {
            // Update the model
            mModel.setTemp((temp));
            mModel.setWindSpeed((windSpeed));

        }
    }
}
