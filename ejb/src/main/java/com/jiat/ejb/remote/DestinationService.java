package com.jiat.ejb.remote;

import com.jiat.ejb.entity.Destination;
import com.jiat.ejb.entity.Transportation;
import com.jiat.ejb.entity.TransportationType;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface DestinationService {
    public boolean addDestination(String name);


}
