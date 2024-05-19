package com.jiat.ejb.exception;

public class NoMerchantFoundForRouteException extends RuntimeException{
    public NoMerchantFoundForRouteException(String message){
        super(message);
    }
}
