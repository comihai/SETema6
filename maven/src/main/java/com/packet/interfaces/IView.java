package com.packet.interfaces;

/**
 * Created by mihai on 11/22/2014.
 * <p/>
 * All the views should implement this interface in order for the cntroller to know how to interact
 */
public interface IView {

    /**
     * On message received from controller
     *
     * @param isError If is true, the message is an error , otherwise
     * @param message The string to be displayed
     */
    public void onMessage(boolean isError, String message);
}
