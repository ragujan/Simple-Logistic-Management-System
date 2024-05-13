package com.jiat.ejb.remote;

import com.jiat.ejb.entity.TransportationType;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface TransportationService {
    public boolean addTransportationType(String name);
    public boolean addTransportation(String name, String transportationType);

//    public List<TransportationType> getTransportationTypeByName(String name);

    public List<TransportationType> getAllTransportationTypes();



}
