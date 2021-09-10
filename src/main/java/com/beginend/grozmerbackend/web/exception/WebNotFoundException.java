package com.beginend.grozmerbackend.web.exception;

public class WebNotFoundException extends RuntimeException{

    public WebNotFoundException(String msg){
        super(msg);
    }
}
