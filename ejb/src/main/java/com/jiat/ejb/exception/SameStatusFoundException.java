package com.jiat.ejb.exception;

public class SameStatusFoundException extends RuntimeException{
    public SameStatusFoundException(String message){
        super(message);
    }
}
