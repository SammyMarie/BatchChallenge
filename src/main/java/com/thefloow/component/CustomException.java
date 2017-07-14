package com.thefloow.component;

/**
 * Created by samif on 13/07/2017.
 */
public class CustomException extends Exception {

    public CustomException(){
        super();
    }

    public CustomException(String message){
        super(message);
    }
}