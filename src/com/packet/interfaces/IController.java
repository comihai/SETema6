package com.packet.interfaces;

import java.awt.event.ActionListener;

/**
 * Created by mihai on 11/22/2014.
 *
 * The interafce implemented by the controller and made public so that all views can use it
 */
public interface IController extends ActionListener{
    public static final String ACTION_UPDATE_RANDOM = "UPDATE_RANDOM";
    public static final String ACTION_UPDATE_REAL = "UPDATE_REAL";
    public static final String ACTION_UPDATE_CITY = "UPDATE_CITY";
}
