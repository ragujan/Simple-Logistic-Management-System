package com.jiat.ejb.exception;

public class ResultNotFoundException extends RuntimeException{
    public ResultNotFoundException(String message){
        super(message);
    }
}
