package com.packet.exceptions;

/**
 * Created by mihai on 11/23/2014.
 *
 * THis class implemets the exception thrown when there is no internet access
 */
public class InternetException extends Exception{

    public InternetException(String message, String errorMessage) {
        super(message +" : "+errorMessage);
    }
}
