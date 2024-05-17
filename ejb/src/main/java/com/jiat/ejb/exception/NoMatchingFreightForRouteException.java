package com.jiat.ejb.exception;

public class NoMatchingFreightForRouteException extends RuntimeException{
    public NoMatchingFreightForRouteException(String message){
        super(message);
    }
}
