package com.jiat.ejb.remote;

import jakarta.ejb.Remote;

@Remote
public interface TransportationService {
    public boolean addTransportationType(String name);
    public boolean addTransportation(String name, String transportationType);
}
